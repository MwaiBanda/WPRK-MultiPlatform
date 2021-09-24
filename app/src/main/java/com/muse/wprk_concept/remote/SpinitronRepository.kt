package com.muse.wprk_concept.remote
import com.muse.wprk_concept.data.Show
import com.muse.wprk_concept.utilities.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class SpinitronRepository  @Inject constructor(
    private val api: SpinitronApi
){
    suspend fun getShows(accessToken: String): Resource<Show> {
        val res = try {
            api.getShows(accessToken = accessToken)
        } catch (e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(res.collection!!)
    }
}