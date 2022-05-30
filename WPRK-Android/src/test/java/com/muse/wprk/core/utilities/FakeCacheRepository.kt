package com.muse.wprk.core.utilities

import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.repository.CacheRepository
import org.junit.Assert.*

class FakeCacheRepository: CacheRepository {
    override fun getShows(id: String): List<Show> {
        TODO("Not yet implemented")
    }

    override fun setShows(id: String, shows: List<Show>) {
        TODO("Not yet implemented")
    }

    override fun getPodcasts(id: String): List<Podcast> {
        TODO("Not yet implemented")
    }

    override fun setPodcasts(id: String, shows: List<Podcast>) {
        TODO("Not yet implemented")
    }

    override fun getEpisodes(id: String): List<Episode> {
        TODO("Not yet implemented")
    }

    override fun setEpisodes(id: String, shows: List<Episode>) {
        TODO("Not yet implemented")
    }
}