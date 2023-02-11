package com.mwaibanda.wprksdk.data.episodeDTO

import kotlinx.serialization.Serializable

@Serializable
data class RelationshipsDTO(
    val show: ShowDTO?
)