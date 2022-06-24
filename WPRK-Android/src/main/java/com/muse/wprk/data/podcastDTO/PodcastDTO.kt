package com.muse.wprk.data.podcastDTO

import com.muse.wprk.main.model.Podcast
import kotlinx.serialization.Serializable

@Serializable
data class PodcastDTO(
    val attributes: AttributesDTO,
    val id: String,
    val relationships: RelationshipsDTO,
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
