package com.muse.wprk.main.repository

import com.muse.wprk.core.utilities.Resource
import com.mwaibanda.wprksdk.main.model.Episode
import com.mwaibanda.wprksdk.main.model.Podcast


interface TransistorRepository {
    suspend fun getPodcasts(): Resource<Podcast>
    suspend fun getEpisodes(showID: String): Resource<Episode>
}