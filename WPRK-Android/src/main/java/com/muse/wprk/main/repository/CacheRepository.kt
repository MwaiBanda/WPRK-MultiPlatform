package com.muse.wprk.main.repository

import com.mwaibanda.wprksdk.main.model.Episode
import com.mwaibanda.wprksdk.main.model.Podcast
import com.mwaibanda.wprksdk.main.model.Show

interface CacheRepository {
    fun getShows(id: String): List<Show>
    fun setShows(id: String, shows: List<Show> )
    fun getPodcasts(id: String): List<Podcast>
    fun setPodcasts(id: String, shows: List<Podcast> )
    fun getEpisodes(id: String): List<Episode>
    fun setEpisodes(id: String, shows: List<Episode> )
}