package com.mwaibanda.wprksdk.data.podcastDTO

import kotlinx.serialization.Serializable

@Serializable

data class DataDTO(
    val id: String,
    val type: String
)