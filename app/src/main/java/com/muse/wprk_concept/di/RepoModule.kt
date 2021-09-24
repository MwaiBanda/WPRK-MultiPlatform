package com.muse.wprk_concept.di

import com.muse.wprk_concept.remote.SpinitronApi
import com.muse.wprk_concept.remote.SpinitronRepository
import com.muse.wprk_concept.utilities.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(SpinitronApi::class.java)

    }
}