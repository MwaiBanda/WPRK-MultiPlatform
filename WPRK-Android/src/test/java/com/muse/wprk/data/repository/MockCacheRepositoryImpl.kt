package com.muse.wprk.data.repository

import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.repository.CacheRepository
import org.junit.jupiter.api.Assertions.*

class MockCacheRepositoryImpl: CacheRepository {
    override fun getShows(id: String): List<Show> {
        return emptyList()
    }

    override fun setShows(id: String, shows: List<Show>) {
        print("Set")
    }

    override fun getPodcasts(id: String): List<Podcast> {
        return emptyList()
    }

    override fun setPodcasts(id: String, shows: List<Podcast>) {
        print("Set")
    }

    override fun getEpisodes(id: String): List<Episode> {
        return emptyList()
    }

    override fun setEpisodes(id: String, shows: List<Episode>) {
        print("Set")
    }

}