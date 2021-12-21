package com.muse.wprk_concept.data.Transistor

import com.muse.wprk_concept.main.model.Podcast
import kotlinx.serialization.Serializable

@Serializable
data class PodcastDTO(
    val attributes: Attributes,
    val id: String,
    val relationships: Relationships,
    val type: String
){
    fun toPodcast(): Podcast {
        return Podcast(
            id = id,
            title = attributes.title,
            description = attributes.description ?: "",
            thumbnailURL = attributes.image_url,
            episodesAvailable = relationships.episodes.data.count()
        )
    }
}
