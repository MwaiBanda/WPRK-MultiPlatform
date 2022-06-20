package com.muse.wprk.data.repository

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.repository.TransistorRepository
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class MockTransistorRepositoryImpl: TransistorRepository {
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
                }
            }
        )
    }

}