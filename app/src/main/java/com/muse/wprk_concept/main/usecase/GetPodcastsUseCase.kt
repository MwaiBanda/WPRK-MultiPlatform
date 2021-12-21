package com.muse.wprk_concept.main.usecase

import com.muse.wprk_concept.core.utilities.Resource
import com.muse.wprk_concept.data.repository.TransistorRepositoryImpl
import com.muse.wprk_concept.main.model.Podcast

class GetPodcastsUseCase (
    private val transistorRepositoryImpl: TransistorRepositoryImpl
    ) {
        suspend operator fun invoke(onFetchCompletion: (Resource<Podcast>) -> Unit){
            onFetchCompletion(transistorRepositoryImpl.getPodcasts())
        }
}