package com.muse.wprk_concept.main.repository

import com.muse.wprk_concept.core.utilities.Resource
import com.muse.wprk_concept.main.model.Episode
import com.muse.wprk_concept.main.model.Podcast

interface TransistorRepository {
    suspend fun getPodcasts(): Resource<Podcast>
    suspend fun getEpisodes(showID: String): Resource<Episode>
}