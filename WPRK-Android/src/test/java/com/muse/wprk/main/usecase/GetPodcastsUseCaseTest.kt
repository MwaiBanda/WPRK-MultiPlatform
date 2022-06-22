package com.muse.wprk.main.usecase

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.data.repository.MockCacheRepositoryImpl
import com.muse.wprk.data.repository.MockTransistorRepositoryImpl
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.repository.CacheRepository
import com.muse.wprk.main.repository.TransistorRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetPodcastsUseCaseTest {
    private lateinit var mocCacheRepository: CacheRepository
    private lateinit var mockTransistorRepository: TransistorRepository
    private lateinit var sut: GetPodcastsUseCase

    @BeforeEach
    fun setUp() {
        mocCacheRepository = MockCacheRepositoryImpl()
        mockTransistorRepository = MockTransistorRepositoryImpl(mocCacheRepository)
        sut = GetPodcastsUseCase(mockTransistorRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test get podcasts use case`() = runTest {
        val expected =  buildList {
            add(Podcast("10001", "", "", "", 10))
        }
        var res = emptyList<Podcast>()
        sut.invoke {
            when(it) {
                is Resource.Error -> TODO()
                is Resource.Loading -> TODO()
                is Resource.Success -> res = it.data ?: emptyList()
            }
        }

        assertEquals(res, expected)
        assertEquals(res.first().id, "10001")
    }

}