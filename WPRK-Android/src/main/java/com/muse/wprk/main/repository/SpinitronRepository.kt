package com.muse.wprk.main.repository

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Show

interface SpinitronRepository {
    suspend fun getShows(accessToken: String):Resource<Show>
}