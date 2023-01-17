package com.mwaibanda.wprksdk.data.repository

import com.muse.wprk.data.ShowsDTO
import com.mwaibanda.wprksdk.main.repository.ShowsRepository
import com.mwaibanda.wprksdk.main.usecase.cache.GetItemUseCase
import com.mwaibanda.wprksdk.main.usecase.cache.SetItemUseCase
import com.mwaibanda.wprksdk.util.Constants
import com.mwaibanda.wprksdk.util.Resource
import com.mwaibanda.wprksdk.util.ShowResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ShowsRepositoryImpl(
    private val httpClient: HttpClient,
    private val getShowsUseCase: GetItemUseCase<ShowResponse>,
    private val setShowsUseCase: SetItemUseCase<ShowResponse>,
): ShowsRepository {
    override suspend fun getShows(accessToken: String): Resource<ShowResponse> {
        val cachedPodcasts = getShowsUseCase(Constants.SHOWS_KEY).orEmpty()
        if (cachedPodcasts.isNotEmpty()) return Resource.Success(cachedPodcasts)
        try {
            val remotePodcasts: ShowResponse = httpClient.get {
                url("${Constants.SPINITRON_BASE_URL}/shows")
                parameter("access-token", Constants.SPINITRON_KEY)
            }.body<ShowsDTO>().collection.map { it.toShow() }
            setShowsUseCase(Constants.SHOWS_KEY, remotePodcasts)
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        val  newlyCachedPodcasts = getShowsUseCase(Constants.SHOWS_KEY).orEmpty()
        return Resource.Success(newlyCachedPodcasts)
    }
}