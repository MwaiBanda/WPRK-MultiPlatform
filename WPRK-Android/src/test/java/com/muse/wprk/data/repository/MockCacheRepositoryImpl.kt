package com.muse.wprk.data.repository

import android.util.Log
import com.mwaibanda.wprksdk.main.model.Episode
import com.mwaibanda.wprksdk.main.model.Podcast
import com.mwaibanda.wprksdk.main.model.Show
import com.muse.wprk.main.repository.CacheRepository
import org.junit.jupiter.api.Assertions.*

class MockCacheRepositoryImpl: CacheRepository {
    private val inMemoryCache = hashMapOf<String, List<Any>?>()

    override fun getShows(id: String): List<Show> {
        return (inMemoryCache[id] ?: emptyList()) as List<Show>
    }

    override fun setShows(id: String, shows: List<Show>) {
        inMemoryCache[id] = shows
    }

    override fun getPodcasts(id: String): List<Podcast> {
        return (inMemoryCache[id] ?: emptyList()) as List<Podcast>
    }

    override fun setPodcasts(id: String, podcasts: List<Podcast>) {
        inMemoryCache[id] = podcasts
    }

    override fun getEpisodes(id: String): List<Episode> {
        return (inMemoryCache[id] ?: emptyList()) as List<Episode>
    }

    override fun setEpisodes(id: String, episodes: List<Episode>) {
        inMemoryCache[id] = episodes
    }

}