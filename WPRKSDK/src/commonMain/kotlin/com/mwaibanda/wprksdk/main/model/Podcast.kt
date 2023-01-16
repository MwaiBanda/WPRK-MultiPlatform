package com.mwaibanda.wprksdk.main.model

import kotlinx.serialization.Serializable

@Serializable
data class Podcast(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailURL: String,
    val episodesAvailable: Int
)
