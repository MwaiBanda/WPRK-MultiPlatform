package com.muse.wprk.presentation.podcasts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.muse.wprk.data.repository.MockTransistorRepositoryImpl
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.usecase.GetEpisodesUseCase
import com.muse.wprk.main.usecase.GetPodcastsUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
import org.junit.runner.RunWith

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
class PodcastViewModelTest {
    private lateinit var mockTransistorRepositoryImpl: MockTransistorRepositoryImpl
    private lateinit var mockGetPodcastUseCase: GetPodcastsUseCase
    private lateinit var mockGetEpisodesUseCase: GetEpisodesUseCase
    private lateinit var sut: PodcastViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
        mockTransistorRepositoryImpl = MockTransistorRepositoryImpl()
        mockGetPodcastUseCase = GetPodcastsUseCase(mockTransistorRepositoryImpl)
        mockGetEpisodesUseCase = GetEpisodesUseCase(mockTransistorRepositoryImpl)
        sut = PodcastViewModel(mockGetPodcastUseCase, mockGetEpisodesUseCase)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
    @Test
    fun `testing fetch podcasts`() = runTest {
        sut.getPodcasts {
            val podcasts = sut.podcasts.value ?: emptyList()
            assertNotEquals(podcasts, emptyList<Podcast>())
        }
    }

    @Test
    fun `testing fetch episodes`() = runTest {
        sut.getEpisodes("10010")
        sut.episodes?.value?.let {
            assertFalse(it.isEmpty())
            assertNotEquals(it, emptyList<Episode>())
            assertTrue(it.count() > 4)
        }
    }

    @Test
    fun `testing fetch featured episodes`() = runTest {
        sut.getFeaturedEpisodes("10010")
        sut.episodes?.value?.let {
            assertFalse(it.isEmpty())
            assertNotEquals(it, emptyList<Episode>())
            assertTrue(it.count() < 5)

        }
    }
    @Test
    fun `testing featured episodes sort order`() = runTest {
        sut.getFeaturedEpisodes("10010")
        sut.episodes?.value?.let {
            assertFalse(it.isEmpty())
            assertNotEquals(it, emptyList<Episode>())
            assertTrue(it[0].number > it[1].number)
        }
    }
}