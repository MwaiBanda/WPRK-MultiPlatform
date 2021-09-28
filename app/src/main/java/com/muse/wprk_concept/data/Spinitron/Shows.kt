package com.muse.wprk_concept.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Shows(
    @SerializedName("items")
    @Expose
    var collection: List<Show>? = null
)