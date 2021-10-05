package com.muse.wprk_concept.data.Transistor

import com.google.gson.annotations.SerializedName

data class Podcasts(
    @SerializedName("data")
    val collection: List<Podcast>
)