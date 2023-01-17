package com.muse.wprk.data

import com.mwaibanda.wprksdk.data.showDTO.LinksDTO
import com.mwaibanda.wprksdk.main.model.Show
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ShowDTO(
    @SerialName("_links")
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
            category = category?.replace("unset","WPRK"),
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


