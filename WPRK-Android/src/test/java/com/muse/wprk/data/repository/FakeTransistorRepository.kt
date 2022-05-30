package com.muse.wprk.data.repository

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.repository.TransistorRepository
import org.junit.Assert.*

class FakeTransistorRepository: TransistorRepository {
    override suspend fun getPodcasts(): Resource<Podcast> {
        return Resource.Success(List(4) { Podcast("1010", "The Joe Rogan: Experience ", "description", "", 4)})
    }

    override suspend fun getEpisodes(showID: String): Resource<Episode> {
        return Resource.Success(List(5){ Episode("1001", "Space Cadet", "", 0, "", "") })
    }
}