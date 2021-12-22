package com.muse.wprk.data.repository

import com.muse.wprk.core.utilities.Constants
import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.data.remote.TransistorApi
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.repository.TransistorRepository
import com.toddway.shelf.Shelf
import com.toddway.shelf.olderThan
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TransistorRepositoryImpl @Inject constructor(
    private val api: TransistorApi,
    private val shelf: Shelf
): TransistorRepository {
    override suspend fun getPodcasts(): Resource<Podcast> {
        val cachedPodcasts = shelf.item(PODCASTS_KEY)
            .apply {
                if (olderThan(seconds = MAX_CACHE_TIME)) {
                    put(emptyList<Podcast>())
                }
            }
            .getList(Podcast::class)
            .orEmpty()

        if (cachedPodcasts.isNotEmpty()) return Resource.Success(cachedPodcasts)

        try {
            val remotePodcasts = api.getPodcasts(API_KEY = Constants.TRANSISTOR_KEY).collection.map { it.toPodcast() }
            shelf.item(PODCASTS_KEY).put(remotePodcasts)
        } catch (e: HttpException) {
            return Resource.Error(e.localizedMessage ?: "Http Error Type")
        }  catch (e: IOException) {
            return Resource.Error(e.localizedMessage ?: "IO Error Type")
        }
        val newlyCachedPodcasts = shelf.item(PODCASTS_KEY)
            .getList(Podcast::class)
        return Resource.Success(newlyCachedPodcasts!!)
    }

    override suspend fun getEpisodes(showID: String): Resource<Episode> {
        val cachedEpisode = shelf.item(showID)
            .apply {
                if (olderThan(seconds = MAX_CACHE_TIME)) {
                    put(emptyList<Episode>())
                }
            }
            .getList(Episode::class)
            .orEmpty()

        if (cachedEpisode.isNotEmpty()) return Resource.Success(cachedEpisode)

        try {
            val remoteEpisodes = api.getEpisode(API_KEY = Constants.TRANSISTOR_KEY, ID = showID).data.map { it.toEpisode() }
            shelf.item(showID).put(remoteEpisodes)

        } catch (e: HttpException) {
            return Resource.Error(e.localizedMessage ?: "Http Error Type")
        }  catch (e: IOException) {
            return Resource.Error(e.localizedMessage ?: "IO Error Type")
        }
        val newlyCachedEpisodes = shelf.item(showID)
            .getList(Episode::class)
        return Resource.Success(newlyCachedEpisodes!!)
    }
    companion object {
        const val PODCASTS_KEY = "PODCASTS"
        const val MAX_CACHE_TIME: Long = 60 * 2
    }
}
