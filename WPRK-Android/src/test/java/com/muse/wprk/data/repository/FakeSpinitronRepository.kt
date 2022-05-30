package com.muse.wprk.data.repository

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.repository.SpinitronRepository
import org.junit.Assert.*

import org.junit.After
import org.junit.Before

class FakeSpinitronRepository: SpinitronRepository {
    override suspend fun getShows(accessToken: String): Resource<Show> {
        return Resource.Success(List(4){ Show(10101,"","",0,"","", "", "", "", "", "")})
    }
}