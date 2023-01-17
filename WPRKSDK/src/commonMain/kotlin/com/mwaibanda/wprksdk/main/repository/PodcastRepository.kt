package com.mwaibanda.wprksdk.main.repository

import com.mwaibanda.wprksdk.util.EpisodeResponse
import com.mwaibanda.wprksdk.util.PodcastResponse
import com.mwaibanda.wprksdk.util.Resource

interface PodcastRepository {
    suspend fun getPodcasts(): Resource<PodcastResponse>
    suspend fun getEpisodes(showID: String, pageNumber: Int): Resource<EpisodeResponse>
}