package com.mwaibanda.wprksdk.data.podcastDTO

import kotlinx.serialization.Serializable

@Serializable
data class PodcastDTOMetadata(
    val currentPage: Int,
    val totalPages: Int,
    val totalCount: Int
)