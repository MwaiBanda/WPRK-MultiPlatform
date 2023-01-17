package com.mwaibanda.wprksdk.data.episodeDTO

import com.mwaibanda.wprksdk.data.metadataDTO.MetadataDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesDTO(
    @SerialName("data")
    val episodes: List<EpisodeDTO>,
    val `meta`: MetadataDTO? = null
)