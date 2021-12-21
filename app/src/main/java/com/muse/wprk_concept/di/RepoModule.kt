package com.muse.wprk_concept.di

import com.muse.wprk_concept.core.utilities.Constants
import com.muse.wprk_concept.data.remote.SpinitronApi
import com.muse.wprk_concept.data.remote.TransistorApi
import com.muse.wprk_concept.data.repository.SpinitronRepositoryImpl
import com.muse.wprk_concept.data.repository.TransistorRepositoryImpl
import com.muse.wprk_concept.main.usecase.GetEpisodesUseCase
import com.muse.wprk_concept.main.usecase.GetPodcastsUseCase
import com.muse.wprk_concept.main.usecase.GetShowUseCase
import com.toddway.shelf.Shelf
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {

    @ViewModelScoped
    @Provides
    fun provideSpinitronRepo(
        api: SpinitronApi,
        shelf: Shelf
    ) = SpinitronRepositoryImpl(api, shelf)


    @ViewModelScoped
    @Provides
    fun provideSpinitron(): SpinitronApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.SPINITRON_BASE_URL)
            .build()
            .create(SpinitronApi::class.java)

    }
    @ViewModelScoped
    @Provides
    fun provideTransistor(): TransistorApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.TRANSISTOR_BASE_URL)
            .build()
            .create(TransistorApi::class.java)
    }

    @ViewModelScoped
    @Provides
    fun provideTransistorRepo(api: TransistorApi, shelf: Shelf) = TransistorRepositoryImpl(api, shelf)

    @ViewModelScoped
    @Provides
    fun provideShowsUseCase(repo: SpinitronRepositoryImpl) = GetShowUseCase(repo)

    @ViewModelScoped
    @Provides
    fun provideEpisodesUseCase(repo: TransistorRepositoryImpl) = GetEpisodesUseCase(repo)

    @ViewModelScoped
    @Provides
    fun providePodcastsUseCase(repo: TransistorRepositoryImpl) = GetPodcastsUseCase(repo)


}