package com.mwaibanda.wprksdk.data.showDTO

@kotlinx.serialization.Serializable
data class LinksDTO(
    val personas: List<PersonaDTO>?,
    val playlists: PlaylistsDTO?,
    val self: SelfDTO?
)