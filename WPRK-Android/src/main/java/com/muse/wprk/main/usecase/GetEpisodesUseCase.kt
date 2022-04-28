package com.muse.wprk.main.usecase

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.repository.TransistorRepository

class GetEpisodesUseCase(
    private val transistorRepository: TransistorRepository
) {
    suspend operator fun invoke(showID: String, onFetchCompletion: (Resource<Episode>) -> Unit){
        onFetchCompletion(transistorRepository.getEpisodes(showID))
    }
}