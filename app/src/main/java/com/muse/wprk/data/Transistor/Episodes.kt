package com.muse.wprk.data.Transistor

import com.muse.wprk.data.Transistor.episodes.EpisodeDTO
import kotlinx.serialization.Serializable

@Serializable
data class Episodes(
    val data: List<EpisodeDTO>

)