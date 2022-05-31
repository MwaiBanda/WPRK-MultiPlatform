package com.muse.wprk.data.repository

import com.muse.wprk.core.utilities.Constants
import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.data.remote.TransistorApi
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.repository.CacheRepository
import com.muse.wprk.main.repository.TransistorRepository
import com.toddway.shelf.Shelf
import com.toddway.shelf.olderThan
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TransistorRepositoryImpl @Inject constructor(
    private val api: TransistorApi,
    private val cache: CacheRepository
): TransistorRepository {
    override suspend fun getPodcasts(): Resource<Podcast> {
        val cachedPodcasts = cache.getPodcasts(PODCASTS_KEY)

        if (cachedPodcasts.isNotEmpty()) return Resource.Success(cachedPodcasts)

        try {
            val remotePodcasts = api.getPodcasts(API_KEY = Constants.TRANSISTOR_KEY).collection.map { it.toPodcast() }
            cache.setPodcasts(PODCASTS_KEY, remotePodcasts)
        } catch (e: HttpException) {
            return Resource.Error(e.localizedMessage ?: "Http Error Type")
        }  catch (e: IOException) {
            return Resource.Error(e.localizedMessage ?: "IO Error Type")
        }
        val newlyCachedPodcasts = cache.getPodcasts(PODCASTS_KEY)
        return Resource.Success(newlyCachedPodcasts)
    }

    override suspend fun getEpisodes(showID: String): Resource<Episode> {
        val cachedEpisode = cache.getEpisodes(showID)

        if (cachedEpisode.isNotEmpty()) return Resource.Success(cachedEpisode)

        try {
            val remoteEpisodes = api.getEpisode(API_KEY = Constants.TRANSISTOR_KEY, ID = showID).data.map { it.toEpisode() }
            cache.setEpisodes(showID, remoteEpisodes)
        } catch (e: HttpException) {
            return Resource.Error(e.localizedMessage ?: "Http Error Type")
        }  catch (e: IOException) {
            return Resource.Error(e.localizedMessage ?: "IO Error Type")
        }
        val newlyCachedEpisodes = cache.getEpisodes(showID)
        return Resource.Success(newlyCachedEpisodes)
    }
    companion object {
        const val PODCASTS_KEY = "PODCASTS"
    }
}
