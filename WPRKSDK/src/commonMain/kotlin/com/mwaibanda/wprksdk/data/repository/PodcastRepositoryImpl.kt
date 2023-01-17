package com.mwaibanda.wprksdk.data.repository

import com.mwaibanda.wprksdk.data.episodeDTO.EpisodesDTO
import com.mwaibanda.wprksdk.data.podcastDTO.PodcastsDTO
import com.mwaibanda.wprksdk.main.model.Episode
import com.mwaibanda.wprksdk.main.model.Podcast
import com.mwaibanda.wprksdk.main.repository.PodcastRepository
import com.mwaibanda.wprksdk.main.usecase.cache.GetAllItemsUseCase
import com.mwaibanda.wprksdk.main.usecase.cache.GetItemUseCase
import com.mwaibanda.wprksdk.main.usecase.cache.SetItemUseCase
import com.mwaibanda.wprksdk.util.Constants
import com.mwaibanda.wprksdk.util.EpisodeResponse
import com.mwaibanda.wprksdk.util.PodcastResponse
import com.mwaibanda.wprksdk.util.Resource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*


class PodcastRepositoryImpl(
    private val httpClient: HttpClient,
    private val getPodcastsUseCase: GetItemUseCase<PodcastResponse>,
    private val setPodcastUseCase: SetItemUseCase<PodcastResponse>,
    private val getEpisodesUseCase: GetItemUseCase<EpisodeResponse>,
    private val setEpisodeUseCase: SetItemUseCase<EpisodeResponse>,
    private val getAllEpisodesUseCase: GetAllItemsUseCase<EpisodeResponse>
): PodcastRepository {

    override suspend fun getPodcasts(): Resource<PodcastResponse> {
        val cachedPodcasts = getPodcastsUseCase(Constants.PODCASTS_KEY).orEmpty()
        if (cachedPodcasts.isNotEmpty()) return Resource.Success(cachedPodcasts)
        try {
            val remotePodcasts: PodcastResponse = httpClient.get {
                url("${Constants.TRANSISTOR_BASE_URL}/shows")
                headers {
                    append("x-api-key", Constants.TRANSISTOR_KEY)
                }
            }.body<PodcastsDTO>().collection.map { it.toPodcast() }
            setPodcastUseCase(Constants.PODCASTS_KEY, remotePodcasts)
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        val  newlyCachedPodcasts = getPodcastsUseCase(Constants.PODCASTS_KEY).orEmpty()
        return Resource.Success(newlyCachedPodcasts)
    }

    override suspend fun getEpisodes(showID: String, pageNumber: Int): Resource<EpisodeResponse> {
         getEpisodesUseCase(key = "${Constants.EPISODE_PREFIX}$showID$pageNumber")?.let {
             val (_, _, cachedEpisode) = it
             if (cachedEpisode.isNotEmpty()) {
                 val allCachedEpisodeResponses: List<EpisodeResponse> = getAllEpisodesUseCase(Constants.EPISODE_PREFIX)
                 return Resource.Success (
                     Triple(
                         pageNumber < allCachedEpisodeResponses.maxByOrNull { it.second }?.second ?: 1,
                         allCachedEpisodeResponses.maxByOrNull { it.second }?.second ?: 1,
                         allCachedEpisodeResponses.flatMap { it.third }
                     )
                 )
             }
         }

        try {
            val episodesDTO: EpisodesDTO = httpClient.get {
                url("${Constants.TRANSISTOR_BASE_URL}/episodes")
                headers {
                    append("x-api-key", Constants.TRANSISTOR_KEY)
                }
                parameter("show_id", "$showID")
                parameter("pagination[page]", "$pageNumber")
            }.body()
            val remoteEpisodes  = episodesDTO.data.map { it.toEpisode() }
            setEpisodeUseCase("${Constants.EPISODE_PREFIX}$showID$pageNumber",
                Triple(
                    pageNumber < 1,
                    pageNumber,
                    remoteEpisodes
                )
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }

        val newlyCachedEpisodes = getEpisodesUseCase("${Constants.EPISODE_PREFIX}$showID$pageNumber")!!
        return Resource.Success(newlyCachedEpisodes)
    }
}