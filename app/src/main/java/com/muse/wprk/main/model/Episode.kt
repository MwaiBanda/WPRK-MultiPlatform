package com.muse.wprk.main.model

import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    val id: String,
    val title: String,
    val description: String,
    val number: Int,
    val duration: String,
    val episodeURL: String
)
