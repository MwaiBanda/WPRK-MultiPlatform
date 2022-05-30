package com.muse.wprk.core.utilities

import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.repository.CacheRepository
import com.toddway.shelf.Shelf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheRepositoryImpl @Inject constructor(
    private val shelf: Shelf
): CacheRepository {
    override fun getShows(id: String): List<Show> {
        return shelf.item(id).getList(Show::class).orEmpty()
    }

    override fun setShows(id: String, shows: List<Show> ) {
        shelf.item(id).put(shows)
    }

    override fun getPodcasts(id: String): List<Podcast> {
        return shelf.item(id).getList(Podcast::class).orEmpty()
    }

    override fun setPodcasts(id: String, podcasts: List<Podcast>) {
        shelf.item(id).put(podcasts)
    }

    override fun getEpisodes(id: String): List<Episode> {
        return shelf.item(id).getList(Episode::class).orEmpty()
    }

    override fun setEpisodes(id: String, episodes: List<Episode>) {
        shelf.item(id).put(episodes)

    }
}