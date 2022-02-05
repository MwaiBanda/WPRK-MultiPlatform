package com.muse.wprk.data.Transistor.episodes

import kotlinx.serialization.Serializable

@Serializable
data class AttributesX(
    val alternate_url: String,
    val audio_processing: Boolean,
    val author: String,
    val created_at: String,
    val description: String,
    val duration: Int,
    val duration_in_mmss: String,
    val email_notifications: String?,
    val embed_html: String,
    val embed_html_dark: String,
    val explicit: Boolean,
    val formatted_published_at: String,
    val formatted_summary: String,
    val image_url: String,
    val keywords: String,
    val media_url: String,
    val number: Int,
    val published_at: String,
    val season: Int,
    val share_url: String,
    val status: String,
    val summary: String,
    val title: String,
    val type: String,
    val updated_at: String
)