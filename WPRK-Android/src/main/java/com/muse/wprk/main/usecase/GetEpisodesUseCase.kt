package com.muse.wprk.main.usecase

import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.data.repository.TransistorRepositoryImpl
import com.muse.wprk.main.model.Episode

class GetEpisodesUseCase(
    private val transistorRepositoryImpl: TransistorRepositoryImpl
) {
    suspend operator fun invoke(showID: String, onFetchCompletion: (Resource<Episode>) -> Unit){
        onFetchCompletion(transistorRepositoryImpl.getEpisodes(showID))
    }
}