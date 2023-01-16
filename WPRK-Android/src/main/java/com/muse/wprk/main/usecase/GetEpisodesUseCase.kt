package com.muse.wprk.main.usecase

import com.muse.wprk.core.utilities.Resource
import com.mwaibanda.wprksdk.main.model.Episode
import com.muse.wprk.main.repository.TransistorRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetEpisodesUseCase @Inject constructor(
    private val transistorRepository: TransistorRepository
) {
    suspend operator fun invoke(showID: String, onFetchCompletion: (Resource<Episode>) -> Unit){
        onFetchCompletion(transistorRepository.getEpisodes(showID))
    }
}