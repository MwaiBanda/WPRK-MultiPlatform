package com.muse.wprk.main.api

import com.muse.wprk.data.ShowsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SpinitronApi {
    @GET("shows")
    suspend fun getShows(
        @Query("access-token") accessToken : String
    ): ShowsDTO
}
