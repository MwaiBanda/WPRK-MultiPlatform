package com.muse.wprk.data.repository

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.repository.CacheRepository
import com.muse.wprk.main.repository.SpinitronRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockSpinitronRepositoryImpl  @Inject constructor(
    private val cache: CacheRepository
): SpinitronRepository {
    override suspend fun getShows(accessToken: String): Resource<Show> {
        val cachedShows = cache.getShows(SHOWS_KEY)

        if (cachedShows.isNotEmpty()) return Resource.Success(cachedShows)

        try {
            val remoteShows: List<Show> = buildList {
                    add(Show(
                            234731,
                            "",
                            "",
                            0,
                            "2022-06-22T03:00:00+0000",
                            "",
                            "",
                            "2022-06-22T02:00:00+0000",
                            "America/New_York",
                            "The Curtis Earth Show",
                            ""
                        )
                    )
                }

            cache.setShows(SHOWS_KEY, remoteShows)
        } catch (e: Exception){
            e.printStackTrace()
        }
        val newlyCachedShows = cache.getShows(SHOWS_KEY)
        return Resource.Success(newlyCachedShows)
    }
    companion object {
        const val SHOWS_KEY = "SHOWS"
    }
}