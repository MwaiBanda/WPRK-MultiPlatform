package com.mwaibanda.wprksdk.main.model

import kotlinx.serialization.Serializable


@Serializable
data class Show(
    val id: Int,
    val category: String? = null,
    val description: String,
    val duration: Int,
    val end: String,
    val image: String,
    val since: String? = null,
    val start: String,
    val timezone: String,
    val title: String,
    val url: String
)


