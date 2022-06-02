package com.muse.wprk.data.repository
import android.util.Log
import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.data.remote.SpinitronApi
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.repository.CacheRepository
import com.muse.wprk.main.repository.SpinitronRepository
import com.toddway.shelf.Shelf
import com.toddway.shelf.getList
import com.toddway.shelf.olderThan
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ActivityScoped
class SpinitronRepositoryImpl @Inject constructor(
    private val api: SpinitronApi,
    private val cache: CacheRepository
): SpinitronRepository {
    override suspend fun getShows(accessToken: String): Resource<Show> {
        val cachedShows = cache.getShows(SHOWS_KEY)

        if (cachedShows.isNotEmpty()) return Resource.Success(data = cachedShows)

        try {
            val remoteShows = api.getShows(accessToken = accessToken).collection.map { it.toShow() }
            Log.d("FET", "$remoteShows")
            cache.setShows(SHOWS_KEY, remoteShows)
        } catch (e: HttpException) {
            return Resource.Error(e.localizedMessage ?: "Http Error Type")
        }  catch (e: IOException) {
            return Resource.Error(e.localizedMessage ?: "IO Error Type")
        }

        val newlyCachedShows = cache.getShows(SHOWS_KEY)
        return Resource.Success(data = newlyCachedShows)
    }
    companion object {
        const val SHOWS_KEY = "SHOWS"
    }
}