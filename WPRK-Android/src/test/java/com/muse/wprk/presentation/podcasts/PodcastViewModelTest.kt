package com.muse.wprk.presentation.podcasts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.muse.wprk.data.repository.MockCacheRepositoryImpl
import com.muse.wprk.data.repository.MockSpinitronRepositoryImpl
import com.muse.wprk.data.repository.MockTransistorRepositoryImpl
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.usecase.GetEpisodesUseCase
import com.muse.wprk.main.usecase.GetPodcastsUseCase
import com.muse.wprk.main.usecase.GetShowUseCase
import com.muse.wprk.presentation.shows.ShowViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
internal class PodcastViewModelTest {
    private lateinit var mockCacheRepositoryImpl: MockCacheRepositoryImpl
    private lateinit var mockTransistorRepositoryImpl: MockTransistorRepositoryImpl
    private lateinit var mockGetPodcastUseCase: GetPodcastsUseCase
    private lateinit var mockGetEpisodesUseCase: GetEpisodesUseCase
    private lateinit var podcastViewModel: PodcastViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
        mockCacheRepositoryImpl = MockCacheRepositoryImpl()
        mockTransistorRepositoryImpl = MockTransistorRepositoryImpl()
        mockGetPodcastUseCase = GetPodcastsUseCase(mockTransistorRepositoryImpl)
        mockGetEpisodesUseCase = GetEpisodesUseCase(mockTransistorRepositoryImpl)
        podcastViewModel = PodcastViewModel(mockGetPodcastUseCase, mockGetEpisodesUseCase)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
    @Test
    fun `testing fetch podcasts`() = runTest {
        podcastViewModel.getPodcasts {
            val podcasts = podcastViewModel.podcasts.value ?: emptyList()
            assertNotEquals(podcasts, emptyList<Podcast>())
        }
    }

    @Test
    fun `testing fetch episodes`() = runTest {
        podcastViewModel.getEpisodes("10010")
        podcastViewModel.episodes?.value?.let {
            assertFalse(it.isEmpty())
            assertNotEquals(it, emptyList<Episode>())
            assertTrue(it.count() > 4)
        }
    }

    @Test
    fun `testing fetch featured episodes`() = runTest {
        podcastViewModel.getFeaturedEpisodes("10010")
        podcastViewModel.episodes?.value?.let {
            assertFalse(it.isEmpty())
            assertNotEquals(it, emptyList<Episode>())
            assertTrue(it.count() < 5)

        }
    }
    @Test
    fun `testing featured episodes sort order`() = runTest {
        podcastViewModel.getFeaturedEpisodes("10010")
        podcastViewModel.episodes?.value?.let {
            assertFalse(it.isEmpty())
            assertNotEquals(it, emptyList<Episode>())
            assertTrue(it[0].number > it[1].number)
        }
    }
}