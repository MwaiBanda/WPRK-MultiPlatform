package com.muse.wprk_concept.main.usecase

import com.muse.wprk_concept.core.utilities.Constants
import com.muse.wprk_concept.core.utilities.Resource
import com.muse.wprk_concept.data.repository.SpinitronRepositoryImpl
import com.muse.wprk_concept.main.model.Show

class GetShowUseCase(
    private val sprinitronRepositoryImpl: SpinitronRepositoryImpl
) {
     suspend operator fun invoke(onFetchCompletion: (Resource<Show>) -> Unit){
         onFetchCompletion(sprinitronRepositoryImpl.getShows(Constants.SPINITRON_KEY))
    }
}