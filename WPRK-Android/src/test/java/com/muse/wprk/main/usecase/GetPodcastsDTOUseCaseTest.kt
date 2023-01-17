package com.muse.wprk.main.usecase

import com.mwaibanda.wprksdk.main.model.Podcast
import com.mwaibanda.wprksdk.main.repository.PodcastRepository
import com.mwaibanda.wprksdk.main.usecase.podcasts.GetPodcastsUseCase
import com.mwaibanda.wprksdk.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetPodcastsDTOUseCaseTest {
    private lateinit var mockTransistorRepository: PodcastRepository
    private lateinit var sut: GetPodcastsUseCase

    @BeforeEach
    fun setUp() {
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