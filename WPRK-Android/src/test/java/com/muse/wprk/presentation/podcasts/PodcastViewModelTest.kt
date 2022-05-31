package com.muse.wprk.presentation.podcasts

import com.google.common.truth.Truth.*
import com.muse.wprk.data.repository.FakeTransistorRepository
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.usecase.GetEpisodesUseCase
import com.muse.wprk.main.usecase.GetPodcastsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PodcastViewModelTest {
    private lateinit var getEpisodesUseCase: GetEpisodesUseCase
    private lateinit var getPodcastsUseCase: GetPodcastsUseCase
    private lateinit var viewModel: PodcastViewModel



    @Before
    fun setUp() {
        val fakeRepo = FakeTransistorRepository()
        getEpisodesUseCase = GetEpisodesUseCase(fakeRepo)
        getPodcastsUseCase = GetPodcastsUseCase(fakeRepo)
        Dispatchers.setMain(TestCoroutineDispatcher())

        viewModel = PodcastViewModel(getPodcastsUseCase, getEpisodesUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getPodcasts() = runTest {
        assertThat(viewModel.podcasts.value).isNotEqualTo(emptyList<Podcast>())
    }

    @Test
    fun getEpisodes() {
    }
}