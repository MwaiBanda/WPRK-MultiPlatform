package com.muse.wprk.data.showDTO

data class LinksDTO(
    val personas: List<PersonaDTO>,
    val playlists: PlaylistsDTO?,
    val self: SelfDTO?
)