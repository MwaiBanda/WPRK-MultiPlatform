package com.muse.wprk.main.repository

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast

interface TransistorRepository {
    suspend fun getPodcasts(): Resource<Podcast>
    suspend fun getEpisodes(showID: String): Resource<Episode>
}