package com.muse.wprk.data.repository

import com.mwaibanda.wprksdk.main.model.Show
import com.mwaibanda.wprksdk.main.repository.ShowsRepository
import com.mwaibanda.wprksdk.main.usecase.cache.GetItemUseCase
import com.mwaibanda.wprksdk.main.usecase.cache.SetItemUseCase
import com.mwaibanda.wprksdk.util.Resource
import com.mwaibanda.wprksdk.util.ShowResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockSpinitronRepositoryImpl @Inject constructor(
    private val getShowsUseCase: GetItemUseCase<ShowResponse>,
    private val setShowsUseCase: SetItemUseCase<ShowResponse>,
) : ShowsRepository {
    override suspend fun getShows(accessToken: String): Resource<ShowResponse> {
        val cachedShows = getShowsUseCase(SHOWS_KEY).orEmpty()

        if (cachedShows.isNotEmpty()) return Resource.Success(cachedShows)

        val remoteShows: List<Show> = buildList {
            add(
                Show(
                    234731,
                    "",
                    "",
                    0,
                    "2022-06-22T03:00:00+0000",
                    "",
                    "",
                    "2022-06-22T02:00:00+0000",
                    "America/New_York",
                    "The Curtis Earth Show",
                    ""
                )
            )
        }

        setShowsUseCase(SHOWS_KEY, remoteShows)

        val newlyCachedShows = getShowsUseCase(SHOWS_KEY).orEmpty()
        return Resource.Success(newlyCachedShows)
    }

    companion object {
        const val SHOWS_KEY = "SHOWS"
    }
}