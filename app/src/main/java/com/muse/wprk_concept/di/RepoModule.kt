package com.muse.wprk_concept.di

import com.muse.wprk_concept.DaggerWPRKApplication_HiltComponents_SingletonC
import com.muse.wprk_concept.remote.SpinitronApi
import com.muse.wprk_concept.remote.SpinitronRepository
import com.muse.wprk_concept.remote.TransistorApi
import com.muse.wprk_concept.remote.TransistorRepository
import com.muse.wprk_concept.utilities.Constants
import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {

    @ViewModelScoped
    @Provides
    fun provideSpinitronRepo(
        api: SpinitronApi
    ) = SpinitronRepository(api)


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
    fun provideTransistorRepo(api: TransistorApi) = TransistorRepository(api)
}