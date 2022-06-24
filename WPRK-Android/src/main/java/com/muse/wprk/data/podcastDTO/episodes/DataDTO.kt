package com.muse.wprk.data.podcastDTO.episodes

import kotlinx.serialization.Serializable

@Serializable
data class DataDTO(
    val id: String,
    val type: String
)