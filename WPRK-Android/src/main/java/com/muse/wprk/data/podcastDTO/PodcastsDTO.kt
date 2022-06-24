package com.muse.wprk.data.podcastDTO

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PodcastsDTO(
    @SerializedName("data")
    val collection: List<PodcastDTO>
)