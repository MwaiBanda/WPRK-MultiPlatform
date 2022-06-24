package com.muse.wprk.data.podcastDTO

import kotlinx.serialization.Serializable

@Serializable
data class RelationshipsDTO(
    val episodes: EpisodesDTO
)