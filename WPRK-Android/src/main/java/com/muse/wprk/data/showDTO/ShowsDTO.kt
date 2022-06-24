package com.muse.wprk.data

import com.google.gson.annotations.SerializedName


data class ShowsDTO(
    @SerializedName("items")
    var collection: List<ShowDTO>
)