package com.muse.wprk.main.api

import com.mwaibanda.wprksdk.data.podcastDTO.EpisodesDTO
import com.mwaibanda.wprksdk.data.podcastDTO.PodcastsDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TransistorApi {
    @GET("shows")
    suspend fun getPodcasts(
        @Header("x-api-key")API_KEY: String
    ): PodcastsDTO?

    @GET("episodes")
    suspend fun getEpisodes(
        @Header("x-api-key")API_KEY: String,
        @Query("show_id")ID: String
    ): EpisodesDTO

}