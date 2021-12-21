package com.muse.wprk_concept.data.Transistor

import com.muse.wprk_concept.data.Transistor.episodes.EpisodeDTO
import kotlinx.serialization.Serializable

@Serializable
data class Episodes(
    val data: List<EpisodeDTO>

)