package com.muse.wprk.data.repository
import android.util.Log
import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.data.remote.SpinitronApi
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.repository.SpinitronRepository
import com.toddway.shelf.Shelf
import com.toddway.shelf.getList
import com.toddway.shelf.olderThan
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ActivityScoped
class SpinitronRepositoryImpl  @Inject constructor(
    private val api: SpinitronApi,
    private val shelf: Shelf
): SpinitronRepository {
    override suspend fun getShows(accessToken: String): Resource<Show> {
        val cachedShows = shelf.item(SHOWS_KEY)
            .apply {
                if (olderThan(seconds = MAX_CACHE_TIME))
                    put(emptyList<Show>())
            }
            .getList<Show>()
            .orEmpty()

        if (cachedShows.isNotEmpty()) return Resource.Success(data = cachedShows)
        try {
            val remoteShows = api.getShows(accessToken = accessToken).collection.map { it.toShow() }
            Log.d("FET", "$remoteShows")
            shelf.item(SHOWS_KEY).put(remoteShows)
        } catch (e: HttpException) {
            return Resource.Error(e.localizedMessage ?: "Http Error Type")
        }  catch (e: IOException) {
            return Resource.Error(e.localizedMessage ?: "IO Error Type")
        }
        val newlyCachedShows = shelf.item(SHOWS_KEY).getList<Show>()
        return Resource.Success(data = newlyCachedShows!!)
    }
    companion object {
        const val SHOWS_KEY = "SHOWS"
        const val MAX_CACHE_TIME: Long = 60 * 2
    }
}