package com.muse.wprk.data.repository

import com.mwaibanda.wprksdk.main.model.Episode
import com.mwaibanda.wprksdk.main.model.Podcast
import com.mwaibanda.wprksdk.main.repository.PodcastRepository
import com.mwaibanda.wprksdk.main.usecase.cache.GetAllItemsUseCase
import com.mwaibanda.wprksdk.main.usecase.cache.GetItemUseCase
import com.mwaibanda.wprksdk.main.usecase.cache.SetItemUseCase
import com.mwaibanda.wprksdk.util.EpisodeResponse
import com.mwaibanda.wprksdk.util.PodcastResponse
import com.mwaibanda.wprksdk.util.Resource

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockTransistorRepositoryImpl  @Inject constructor(
    private val getPodcastsUseCase: GetItemUseCase<PodcastResponse>,
    private val setPodcastUseCase: SetItemUseCase<PodcastResponse>,
    private val getEpisodesUseCase: GetItemUseCase<EpisodeResponse>,
    private val setEpisodeUseCase: SetItemUseCase<EpisodeResponse>,
    private val getAllEpisodesUseCase: GetAllItemsUseCase<EpisodeResponse>
): PodcastRepository {
    override suspend fun getPodcasts(): Resource<PodcastResponse> {
        val cachedPodcasts = getPodcastsUseCase(PODCASTS_KEY).orEmpty()

        if (cachedPodcasts.isNotEmpty()) return Resource.Success(cachedPodcasts)

        val remote = buildList {
            add(Podcast("10001", "", "", "", 10))
        }
        setPodcastUseCase(PODCASTS_KEY, remote)

        val newlyCachedPodcasts = getPodcastsUseCase(PODCASTS_KEY).orEmpty()
        return Resource.Success(newlyCachedPodcasts)
    }

    override suspend fun getEpisodes(showID: String, pageNumber: Int): Resource<EpisodeResponse> {
        getEpisodesUseCase(showID)?.let {
            if (it.third.isNotEmpty()) return Resource.Success(it)
        }
        val remote = buildList {
            add(Episode("10001", "", "", 1, "00.00", ""))
            add(Episode("10002", "", "", 2, "00.00", ""))
            add(Episode("10003", "", "", 3, "00.00", ""))
            add(Episode("10004", "", "", 4, "00.00", ""))
            add(Episode("10004", "", "", 5, "00.00", ""))

        }
        setEpisodeUseCase(showID, Triple(false, 1, remote))

        val newlyCachedEpisodes = getEpisodesUseCase(showID)!!
        return Resource.Success(newlyCachedEpisodes)
    }
    companion object {
        const val PODCASTS_KEY = "PODCASTS"
    }
}