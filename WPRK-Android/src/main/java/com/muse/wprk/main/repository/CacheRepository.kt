package com.muse.wprk.main.repository

import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.model.Show

interface CacheRepository {
    fun getShows(id: String): List<Show>
    fun setShows(id: String, shows: List<Show> )
    fun getPodcasts(id: String): List<Podcast>
    fun setPodcasts(id: String, shows: List<Podcast> )
    fun getEpisodes(id: String): List<Episode>
    fun setEpisodes(id: String, shows: List<Episode> )
}