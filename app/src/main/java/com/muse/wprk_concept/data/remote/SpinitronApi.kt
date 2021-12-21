package com.muse.wprk_concept.data.remote

import com.muse.wprk_concept.data.Shows
import retrofit2.http.GET
import retrofit2.http.Query

interface SpinitronApi {
    @GET("shows")
    suspend fun getShows(
        @Query("access-token") accessToken : String
    ): Shows
}
