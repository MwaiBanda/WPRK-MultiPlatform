package com.muse.wprk.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.muse.wprk.core.utilities.Constants
import com.muse.wprk.main.api.SpinitronApi
import com.muse.wprk.main.api.TransistorApi
import com.muse.wprk.data.SpinitronRepositoryImpl
import com.muse.wprk.data.TransistorRepositoryImpl
import com.muse.wprk.main.repository.CacheRepository
import com.muse.wprk.main.repository.SpinitronRepository
import com.muse.wprk.main.repository.TransistorRepository
import com.muse.wprk.main.usecase.GetEpisodesUseCase
import com.muse.wprk.main.usecase.GetPodcastsUseCase
import com.muse.wprk.main.usecase.GetShowUseCase
import com.toddway.shelf.KotlinxSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideFactory(): Converter.Factory {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues = true
        }.asConverterFactory("application/json".toMediaType())
    }

    @Singleton
    @Provides
    fun provideSpinitron(converter: Converter.Factory): SpinitronApi {
        return Retrofit.Builder()
            .addConverterFactory(converter)
            .baseUrl(Constants.SPINITRON_BASE_URL)
            .build()
            .create(SpinitronApi::class.java)

    }

    @Singleton
    @Provides
    fun provideTransistor(converter: Converter.Factory): TransistorApi {
        return Retrofit.Builder()
            .addConverterFactory(converter)
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