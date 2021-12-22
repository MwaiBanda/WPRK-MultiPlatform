package com.muse.wprk.data

import com.google.gson.annotations.SerializedName


data class Shows(
    @SerializedName("items")
    var collection: List<ShowDTO>
)