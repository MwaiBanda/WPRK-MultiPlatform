package com.mwaibanda.wprksdk.data.podcastDTO

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PodcastsDTO(
    @SerialName("data")
    val collection: List<PodcastDTO>
)