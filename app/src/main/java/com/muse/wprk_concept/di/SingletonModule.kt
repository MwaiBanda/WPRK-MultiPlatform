package com.muse.wprk_concept.di

import android.app.Application
import com.muse.wprk_concept.main.model.Podcast
import com.muse.wprk_concept.main.model.Show
import com.toddway.shelf.FileStorage
import com.toddway.shelf.GsonSerializer
import com.toddway.shelf.KotlinxSerializer
import com.toddway.shelf.Shelf
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @OptIn(InternalSerializationApi::class)
    @Provides
    @Singleton
    fun provideShelf(
        app: Application
    ) = Shelf(FileStorage(app.cacheDir), GsonSerializer()).apply {
        serializer = KotlinxSerializer().apply {
            register(Show.serializer())
            register(Podcast.serializer())
        }
    }
}