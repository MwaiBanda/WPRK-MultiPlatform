package com.muse.wprk_concept.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.ASCENDING
import com.muse.wprk_concept.core.utilities.Constants.COLLECTION_NAME
import com.muse.wprk_concept.core.utilities.Constants.ORDER_BY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideQueryProductsByName() = FirebaseFirestore.getInstance()
        .collection(COLLECTION_NAME)
        .orderBy(ORDER_BY, ASCENDING)


}