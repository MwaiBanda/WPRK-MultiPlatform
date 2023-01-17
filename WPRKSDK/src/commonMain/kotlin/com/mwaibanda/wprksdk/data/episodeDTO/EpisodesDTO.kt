package com.mwaibanda.wprksdk.data.episodeDTO

import com.mwaibanda.wprksdk.data.episodeDTO.EpisodeDTO
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesDTO(
    val `data`: List<EpisodeDTO>
)