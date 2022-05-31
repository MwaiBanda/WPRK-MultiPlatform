package com.muse.wprk.di

import com.muse.wprk.core.utilities.Constants
import com.muse.wprk.data.remote.SpinitronApi
import com.muse.wprk.data.remote.TransistorApi
import com.muse.wprk.data.repository.SpinitronRepositoryImpl
import com.muse.wprk.data.repository.TransistorRepositoryImpl
import com.muse.wprk.main.repository.CacheRepository
import com.muse.wprk.main.repository.SpinitronRepository
import com.muse.wprk.main.repository.TransistorRepository
import com.muse.wprk.main.usecase.GetEpisodesUseCase
import com.muse.wprk.main.usecase.GetPodcastsUseCase
import com.muse.wprk.main.usecase.GetShowUseCase
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
        cache: CacheRepository
    ): SpinitronRepository = SpinitronRepositoryImpl(api, cache)


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
    fun provideTransistorRepo(
        api: TransistorApi,
        cache: CacheRepository
    ): TransistorRepository = TransistorRepositoryImpl(api, cache)

    @ViewModelScoped
    @Provides
    fun provideShowsUseCase(repo: SpinitronRepository) = GetShowUseCase(repo)

    @ViewModelScoped
    @Provides
    fun provideEpisodesUseCase(repo: TransistorRepository) = GetEpisodesUseCase(repo)

    @ViewModelScoped
    @Provides
    fun providePodcastsUseCase(repo: TransistorRepository) = GetPodcastsUseCase(repo)

}