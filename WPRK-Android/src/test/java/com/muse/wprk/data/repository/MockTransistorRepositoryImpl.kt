package com.muse.wprk.data.repository

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.repository.CacheRepository
import com.muse.wprk.main.repository.TransistorRepository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockTransistorRepositoryImpl  @Inject constructor(
    private val cache: CacheRepository
): TransistorRepository {
    override suspend fun getPodcasts(): Resource<Podcast> {
        val cachedPodcasts = cache.getPodcasts(PODCASTS_KEY)

        if (cachedPodcasts.isNotEmpty()) return Resource.Success(cachedPodcasts)

        val remote = buildList {
            add(Podcast("10001", "", "", "", 10))
        }
        cache.setPodcasts(PODCASTS_KEY, remote)

        val newlyCachedPodcasts = cache.getPodcasts(PODCASTS_KEY)
        return Resource.Success(newlyCachedPodcasts)
    }

    override suspend fun getEpisodes(showID: String): Resource<Episode> {
        val cachedEpisodes = cache.getEpisodes(showID)

        if (cachedEpisodes.isNotEmpty()) return Resource.Success(cachedEpisodes)

        val remote = buildList {
            add(Episode("10001", "", "", 1, "00.00", ""))
            add(Episode("10002", "", "", 2, "00.00", ""))
            add(Episode("10003", "", "", 3, "00.00", ""))
            add(Episode("10004", "", "", 4, "00.00", ""))
            add(Episode("10004", "", "", 5, "00.00", ""))

        }
        cache.setEpisodes(showID, remote)

        val newlyCachedEpisodes = cache.getEpisodes(showID)
        return Resource.Success(newlyCachedEpisodes)
    }
    companion object {
        const val PODCASTS_KEY = "PODCASTS"
    }
}