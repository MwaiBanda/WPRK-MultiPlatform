package com.muse.wprk_concept.main.usecase

import com.muse.wprk_concept.core.utilities.Resource
import com.muse.wprk_concept.data.repository.TransistorRepositoryImpl
import com.muse.wprk_concept.main.model.Episode

class GetEpisodesUseCase(
    private val transistorRepositoryImpl: TransistorRepositoryImpl
) {
    suspend operator fun invoke(showID: String, onFetchCompletion: (Resource<Episode>) -> Unit){
        onFetchCompletion(transistorRepositoryImpl.getEpisodes(showID))
    }
}