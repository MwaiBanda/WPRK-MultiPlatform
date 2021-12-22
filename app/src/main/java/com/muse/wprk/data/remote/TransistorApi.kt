package com.muse.wprk.data.remote

import com.muse.wprk.data.Transistor.Episodes
import com.muse.wprk.data.Transistor.Podcasts
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TransistorApi {
    @GET("shows")
    suspend fun getPodcasts(
        @Header("x-api-key")API_KEY: String
    ): Podcasts

    @GET("episodes")
    suspend fun getEpisode(
        @Header("x-api-key")API_KEY: String,
        @Query("show_id")ID: String
    ): Episodes

}