package com.muse.wprk.data.podcastDTO

import com.muse.wprk.data.episodeDTO.EpisodeDTO
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesDTO(
    val data: List<EpisodeDTO>

)