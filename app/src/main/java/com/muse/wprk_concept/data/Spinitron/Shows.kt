package com.muse.wprk_concept.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


data class Shows(
    @SerializedName("items")
    var collection: List<ShowDTO>
)