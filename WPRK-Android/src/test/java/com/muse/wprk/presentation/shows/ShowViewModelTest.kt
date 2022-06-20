package com.muse.wprk.presentation.shows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.muse.wprk.data.repository.MockCacheRepositoryImpl
import com.muse.wprk.data.repository.MockSpinitronRepositoryImpl
import com.muse.wprk.main.usecase.GetShowUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
internal class ShowViewModelTest {
    private lateinit var mockCacheRepositoryImpl: MockCacheRepositoryImpl
    private lateinit var mockSpinitronRepositoryImpl: MockSpinitronRepositoryImpl
    private lateinit var mockGetShowsUseCase: GetShowUseCase
    private lateinit var showViewModel: ShowViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
        mockCacheRepositoryImpl = MockCacheRepositoryImpl()
        mockSpinitronRepositoryImpl = MockSpinitronRepositoryImpl()
        mockGetShowsUseCase = GetShowUseCase(mockSpinitronRepositoryImpl)
        showViewModel = ShowViewModel(mockGetShowsUseCase, mockCacheRepositoryImpl)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `test fetching shows`()  = runTest {
        showViewModel.getShows {
            val shows = showViewModel.shows.value ?: emptyList()
            assertTrue(shows.isNotEmpty())
        }
    }
}