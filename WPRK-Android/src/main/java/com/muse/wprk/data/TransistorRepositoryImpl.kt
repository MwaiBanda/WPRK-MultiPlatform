package com.muse.wprk.data

import com.muse.wprk.core.utilities.Constants
import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.api.TransistorApi
import com.mwaibanda.wprksdk.main.model.Episode
import com.mwaibanda.wprksdk.main.model.Podcast
import com.muse.wprk.main.repository.CacheRepository
import com.muse.wprk.main.repository.TransistorRepository
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
            val remotePodcasts = api.getPodcasts(API_KEY = Constants.TRANSISTOR_KEY)?.collection?.map { it.toPodcast() }.orEmpty()
            println(remotePodcasts)
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
            val remoteEpisodes = api.getEpisodes(API_KEY = Constants.TRANSISTOR_KEY, ID = showID).data.map { it?.toEpisode() }.orEmpty()
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
