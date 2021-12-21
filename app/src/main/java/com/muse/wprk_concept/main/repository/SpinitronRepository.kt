package com.muse.wprk_concept.main.repository

import com.muse.wprk_concept.core.utilities.Resource
import com.muse.wprk_concept.main.model.Show

interface SpinitronRepository {
    suspend fun getShows(accessToken: String):Resource<Show>
}