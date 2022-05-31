package com.muse.wprk.data.repository

import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.repository.CacheRepository
import com.toddway.shelf.Shelf
import com.toddway.shelf.olderThan
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheRepositoryImpl @Inject constructor(
    private val shelf: Shelf
): CacheRepository {
    override fun getShows(id: String): List<Show> {
        return shelf.item(id).apply {
            if (olderThan(seconds = MAX_CACHE_TIME)) {
                put(emptyList<Show>())
            }
        }.getList(Show::class).orEmpty()
    }

    override fun setShows(id: String, shows: List<Show> ) {
        shelf.item(id).put(shows)
    }

    override fun getPodcasts(id: String): List<Podcast> {
        return shelf.item(id)
            .apply {
                if (olderThan(seconds = MAX_CACHE_TIME)) {
                    put(emptyList<Podcast>())
                }
            }.getList(Podcast::class).orEmpty()
    }

    override fun setPodcasts(id: String, podcasts: List<Podcast>) {
        shelf.item(id).put(podcasts)
    }

    override fun getEpisodes(id: String): List<Episode> {
        return shelf.item(id).apply {
            if (olderThan(seconds = MAX_CACHE_TIME)) {
                put(emptyList<Episode>())
            }
        }.getList(Episode::class).orEmpty()
    }

    override fun setEpisodes(id: String, episodes: List<Episode>) {
        shelf.item(id).put(episodes)

    }

    companion object {
        const val MAX_CACHE_TIME: Long = 60 * 45
    }
}