package com.muse.wprk.di

import com.mwaibanda.wprksdk.main.usecase.podcasts.GetEpisodesUseCase
import com.mwaibanda.wprksdk.main.usecase.shows.GetShowUseCase
import com.mwaibanda.wprksdk.di.WPRK
import com.mwaibanda.wprksdk.main.usecase.podcasts.GetPodcastsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideShowsUseCase(): GetShowUseCase = WPRK.getShowUseCase

    @Singleton
    @Provides
    fun provideEpisodesUseCase(): GetEpisodesUseCase = WPRK.getEpisodesUseCase

    @Singleton
    @Provides
    fun providePodcastsUseCase(): GetPodcastsUseCase = WPRK.getPodcastsUseCase

}