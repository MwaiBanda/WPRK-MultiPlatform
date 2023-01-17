package com.muse.wprk.main.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.muse.wprk.data.repository.MockCacheRepositoryImpl
import com.muse.wprk.data.repository.MockTransistorRepositoryImpl
import com.mwaibanda.wprksdk.main.model.Episode
import com.mwaibanda.wprksdk.main.repository.PodcastRepository
import com.mwaibanda.wprksdk.main.usecase.podcasts.GetEpisodesUseCase
import com.mwaibanda.wprksdk.util.CacheControl
import com.mwaibanda.wprksdk.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetEpisodesDTOUseCaseTest {
    private lateinit var mocCacheRepository: CacheControl
    private lateinit var mockTransistorRepository: PodcastRepository
    private lateinit var sut: GetEpisodesUseCase

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
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
        sut.invoke("10001", 1) {
            when(it) {
                is Resource.Error -> TODO()
                is Resource.Loading -> TODO()
                is Resource.Success -> res = it.data?.third ?: emptyList()
            }
        }

        assertEquals(res, expected)
        assertEquals(res.first().id, "10001")
    }
}