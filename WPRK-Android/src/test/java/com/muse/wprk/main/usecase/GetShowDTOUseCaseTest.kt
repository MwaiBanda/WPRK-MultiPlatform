package com.muse.wprk.main.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mwaibanda.wprksdk.main.model.Show
import com.mwaibanda.wprksdk.main.repository.ShowsRepository
import com.mwaibanda.wprksdk.main.usecase.shows.GetShowUseCase
import com.mwaibanda.wprksdk.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetShowDTOUseCaseTest {
    private lateinit var mockSpinitronRepository: ShowsRepository
    private lateinit var sut: GetShowUseCase

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
        sut = GetShowUseCase(mockSpinitronRepository)
    }



    @ExperimentalCoroutinesApi
    @Test
    fun `test get shows use case`() = runTest {
        val expected = buildList {
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
        var res = emptyList<Show>()
        sut.invoke {
            when(it) {
                is Resource.Error -> TODO()
                is Resource.Loading -> TODO()
                is Resource.Success -> res = it.data ?: emptyList()
            }
        }

        assertEquals(res, expected)
        assertEquals(res.first().id, 234731)
    }
}