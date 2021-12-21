package com.muse.wprk_concept.data.Spinitron

import kotlinx.serialization.Serializable

data class Links(
    val personas: List<Persona>,
    val playlists: Playlists?,
    val self: Self?
)