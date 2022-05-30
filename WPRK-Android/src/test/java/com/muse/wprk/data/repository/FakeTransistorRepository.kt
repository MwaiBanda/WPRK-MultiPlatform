package com.muse.wprk.data.repository

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.repository.TransistorRepository
import org.junit.Assert.*

class FakeTransistorRepository: TransistorRepository {
    override suspend fun getPodcasts(): Resource<Podcast> {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisodes(showID: String): Resource<Episode> {
        TODO("Not yet implemented")
    }
}