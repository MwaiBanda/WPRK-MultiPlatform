package com.muse.wprk.main.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.data.repository.MockCacheRepositoryImpl
import com.muse.wprk.data.repository.MockSpinitronRepositoryImpl
import com.muse.wprk.data.repository.MockTransistorRepositoryImpl
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.repository.CacheRepository
import com.muse.wprk.main.repository.TransistorRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetEpisodesUseCaseTest {
    private lateinit var mocCacheRepository: CacheRepository
    private lateinit var mockTransistorRepository: TransistorRepository
    private lateinit var sut: GetEpisodesUseCase

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
        mocCacheRepository = MockCacheRepositoryImpl()
        mockTransistorRepository = MockTransistorRepositoryImpl(mocCacheRepository)
        sut = GetEpisodesUseCase(mockTransistorRepository)
    }



    @ExperimentalCoroutinesApi
    @Test
    fun `test get episodes use case`() = runTest {
        val expected = buildList {
            add(Episode("10001", "", "", 1, "00.00", ""))
            add(Episode("10002", "", "", 2, "00.00", ""))
            add(Episode("10003", "", "", 3, "00.00", ""))
            add(Episode("10004", "", "", 4, "00.00", ""))
            add(Episode("10004", "", "", 5, "00.00", ""))

        }
        var res = emptyList<Episode>()
        sut.invoke("10001") {
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