package com.mwaibanda.wprksdk.main.repository

import com.mwaibanda.wprksdk.main.model.Show
import com.mwaibanda.wprksdk.util.Resource
import com.mwaibanda.wprksdk.util.ShowResponse

interface ShowsRepository {
    suspend fun getShows(accessToken: String): Resource<ShowResponse>
}