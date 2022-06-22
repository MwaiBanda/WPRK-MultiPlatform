package com.muse.wprk.data.repository

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.repository.TransistorRepository
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockTransistorRepositoryImpl  @Inject constructor(): TransistorRepository {
    override suspend fun getPodcasts(): Resource<Podcast> {
        return Resource.Success(
            listOf<Podcast>().apply {
                buildList {
                    add(Podcast("10001", "", "", "", 10))
                }
            }
        )
    }

    override suspend fun getEpisodes(showID: String): Resource<Episode> {
        return Resource.Success(
            listOf<Episode>().apply {
                buildList {
                    add(Episode("10001", "", "", 1, "00.00", ""))
                    add(Episode("10002", "", "", 2, "00.00", ""))
                    add(Episode("10003", "", "", 3, "00.00", ""))
                    add(Episode("10004", "", "", 4, "00.00", ""))
                    add(Episode("10004", "", "", 5, "00.00", ""))
                }
            }
        )
    }

}