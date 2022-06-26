package com.muse.wprk.data.episodeDTO

import kotlinx.serialization.Serializable

@Serializable
data class RelationshipsDTO(
    val show: ShowDTO
)