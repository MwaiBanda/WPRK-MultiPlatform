package com.muse.wprk.main.usecase

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.data.repository.TransistorRepositoryImpl
import com.muse.wprk.main.model.Podcast

class GetPodcastsUseCase (
    private val transistorRepositoryImpl: TransistorRepositoryImpl
    ) {
        suspend operator fun invoke(onFetchCompletion: (Resource<Podcast>) -> Unit){
            onFetchCompletion(transistorRepositoryImpl.getPodcasts())
        }
}