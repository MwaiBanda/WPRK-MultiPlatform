package com.muse.wprk.data.podcastDTO.episodes

import kotlinx.serialization.Serializable

@Serializable
data class RelationshipsDTO(
    val show: ShowDTO
)