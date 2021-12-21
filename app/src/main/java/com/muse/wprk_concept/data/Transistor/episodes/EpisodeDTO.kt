package com.muse.wprk_concept.data.Transistor.episodes

import com.muse.wprk_concept.main.model.Episode
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDTO(
    val attributes: AttributesX,
    val id: String,
    val relationships: RelationshipsX,
    val type: String
){
    fun toEpisode(): Episode {
        return  Episode(
            id = id,
            title = attributes.title,
            description = attributes.description,
            number = attributes.number,
            duration = attributes.duration_in_mmss,
            episodeURL = attributes.media_url
        )
    }
}

