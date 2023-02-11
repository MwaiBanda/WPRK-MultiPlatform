package com.mwaibanda.wprksdk.data.episodeDTO

import com.mwaibanda.wprksdk.main.model.Episode
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDTO(
    val attributes: AttributesDTO?,
    val id: String,
    val relationships: RelationshipsDTO?,
    val type: String?
){
    fun toEpisode(): Episode {
        return  Episode(
            id = id,
            title = attributes?.title ?: "",
            description = attributes?.description
                ?.replace("<br>", "")
                ?.replace("&nbsp;", " ")
                ?.replace("<strong>", "")
                ?.replace("</strong>", "")
                ?: "",
            number = attributes?.number ?: 0,
            duration = attributes?.duration_in_mmss ?: "",
            episodeURL = attributes?.media_url ?: "",
            showId = relationships?.show?.data?.id ?: ""
        )
    }
}

