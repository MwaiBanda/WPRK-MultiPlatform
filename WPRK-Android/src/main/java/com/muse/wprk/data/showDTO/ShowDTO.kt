package com.muse.wprk.data

import com.google.gson.annotations.SerializedName
import com.muse.wprk.data.showDTO.LinksDTO
import com.muse.wprk.main.model.Show

data class ShowDTO(
    @SerializedName("_links")
    val links: LinksDTO,
    val category: String? = null,
    val description: String,
    val duration: Int,
    val end: String,
    val hide_dj: Int,
    val id: Int,
    val image: String,
    val one_off: Boolean,
    val since: String? = null,
    val start: String,
    val timezone: String,
    val title: String,
    val url: String
) {
    fun toShow(): Show {
        return Show(
            id = id,
            category = category,
            description = description,
            duration = duration,
            end = end,
            image = image,
            since = since,
            start = start,
            timezone = timezone,
            title = title,
            url = url
        )
    }
}


