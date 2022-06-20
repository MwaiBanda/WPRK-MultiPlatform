package com.muse.wprk.data.repository

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.repository.SpinitronRepository
import org.junit.Assert.*

class MockSpinitronRepositoryImpl: SpinitronRepository {
    override suspend fun getShows(accessToken: String): Resource<Show> {
        return Resource.Success(listOf<Show>().apply {
            buildList {
                add(Show(0, "","",0,"","", "", "", "", "", ""))
            }
        })
    }
}