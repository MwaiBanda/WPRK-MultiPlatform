package com.muse.wprk.di

import android.app.Application
import com.muse.wprk.data.CacheRepositoryImpl
import com.mwaibanda.wprksdk.main.model.Episode
import com.mwaibanda.wprksdk.main.model.Podcast
import com.mwaibanda.wprksdk.main.model.Show
import com.muse.wprk.main.repository.CacheRepository
import com.toddway.shelf.FileStorage
import com.toddway.shelf.GsonSerializer
import com.toddway.shelf.KotlinxSerializer
import com.toddway.shelf.Shelf
import com.toddway.shelf.Shelf.Companion.serializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @OptIn(InternalSerializationApi::class)
    @Provides
    @Singleton
    fun provideShelf(
        app: Application
    ) = Shelf(FileStorage(app.cacheDir), KotlinxSerializer()).apply {
        serializer = KotlinxSerializer().apply {
            register(Show.serializer())
            register(Podcast.serializer())
            register(Episode.serializer())
        }
    }
    @Singleton
    @Provides
    fun provideCache(
        shelf: Shelf
    ): CacheRepository = CacheRepositoryImpl(shelf)

}