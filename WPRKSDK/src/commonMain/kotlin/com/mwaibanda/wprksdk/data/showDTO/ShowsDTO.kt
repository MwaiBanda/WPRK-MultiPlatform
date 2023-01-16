package com.muse.wprk.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ShowsDTO(
    @SerialName("items")
    var collection: List<ShowDTO>
)