package com.mwaibanda.wprksdk.data.podcastDTO

import com.mwaibanda.wprksdk.data.episodeDTO.EpisodesDTO
import kotlinx.serialization.Serializable

@Serializable
data class RelationshipsDTO(
    val episodes: EpisodesDTO
)