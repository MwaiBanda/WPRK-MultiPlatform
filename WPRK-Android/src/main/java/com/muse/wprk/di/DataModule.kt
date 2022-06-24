package com.muse.wprk.di

import com.muse.wprk.core.utilities.Constants
import com.muse.wprk.main.api.SpinitronApi
import com.muse.wprk.main.api.TransistorApi
import com.muse.wprk.data.repository.SpinitronRepositoryImpl
import com.muse.wprk.data.repository.TransistorRepositoryImpl
import com.muse.wprk.main.repository.CacheRepository
import com.muse.wprk.main.repository.SpinitronRepository
import com.muse.wprk.main.repository.TransistorRepository
import com.muse.wprk.main.usecase.GetEpisodesUseCase
import com.muse.wprk.main.usecase.GetPodcastsUseCase
import com.muse.wprk.main.usecase.GetShowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideSpinitron(): SpinitronApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.SPINITRON_BASE_URL)
            .build()
            .create(SpinitronApi::class.java)

    }
    @Singleton
    @Provides
    fun provideTransistor(): TransistorApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.TRANSISTOR_BASE_URL)
            .build()
            .create(TransistorApi::class.java)
    }
    @Singleton
    @Provides
    fun provideSpinitronRepo(
        api: SpinitronApi,
        cache: CacheRepository
    ): SpinitronRepository = SpinitronRepositoryImpl(api, cache)

    @Singleton
    @Provides
    fun provideTransistorRepo(
        api: TransistorApi,
        cache: CacheRepository
    ): TransistorRepository = TransistorRepositoryImpl(api, cache)

    @Singleton
    @Provides
    fun provideShowsUseCase(repo: SpinitronRepository) = GetShowUseCase(repo)

    @Singleton
    @Provides
    fun provideEpisodesUseCase(repo: TransistorRepository) = GetEpisodesUseCase(repo)

    @Singleton
    @Provides
    fun providePodcastsUseCase(repo: TransistorRepository) = GetPodcastsUseCase(repo)

}