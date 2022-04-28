package com.muse.wprk.main.usecase

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.repository.TransistorRepository

class GetPodcastsUseCase (
    private val transistorRepository: TransistorRepository
    ) {
        suspend operator fun invoke(onFetchCompletion: (Resource<Podcast>) -> Unit){
            onFetchCompletion(transistorRepository.getPodcasts())
        }
}