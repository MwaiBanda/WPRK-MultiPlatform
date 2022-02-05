package com.muse.wprk.data.Transistor

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Podcasts(
    @SerializedName("data")
    val collection: List<PodcastDTO>
)