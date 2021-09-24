package com.muse.wprk_concept.di

import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.muse.wprk_concept.utilities.Constants.COLLECTION_NAME
import com.muse.wprk_concept.utilities.Constants.ORDER_BY
import com.google.firebase.firestore.Query.Direction.*
import com.google.gson.Gson
import com.muse.wprk_concept.remote.SpinitronRepository
import com.muse.wprk_concept.remote.SpinitronApi
import com.muse.wprk_concept.utilities.Constants.BASE_URL
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideQueryProductsByName() = FirebaseFirestore.getInstance()
        .collection(COLLECTION_NAME)
        .orderBy(ORDER_BY, ASCENDING)


}