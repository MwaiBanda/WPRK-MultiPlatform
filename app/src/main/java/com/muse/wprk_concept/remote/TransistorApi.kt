package com.muse.wprk_concept.remote

import com.muse.wprk_concept.data.Transistor.Episode
import com.muse.wprk_concept.data.Transistor.Podcasts
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TransistorApi {
    @GET("shows")
    suspend fun getPodcasts(
        @Header("x-api-key")API_KEY: String
    ): Podcasts

    @GET("episodes/{ID}")
    suspend fun getEpisode(
        @Header("x-api-key")API_KEY: String,
        @Path("ID")ID: String
    ): Episode

}