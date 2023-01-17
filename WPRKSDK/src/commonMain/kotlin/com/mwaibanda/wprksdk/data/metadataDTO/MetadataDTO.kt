package com.mwaibanda.wprksdk.data.metadataDTO

import kotlinx.serialization.Serializable

@Serializable
data class MetadataDTO (
    val currentPage: Int,
    val totalPages: Int,
    val totalCount: Int
)