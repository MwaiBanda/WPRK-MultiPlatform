package com.muse.wprk_concept.data.Transistor

import com.google.gson.annotations.SerializedName

data class Podcast(
    val attributes: Attributes,
    val id: String,
    val relationships: Relationships,
    val type: String
)
data class Podcasts(
    @SerializedName("data")
    val collection: List<Podcast>
)