package com.muse.wprk.main.usecase

import com.muse.wprk.core.utilities.Constants
import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.data.repository.SpinitronRepositoryImpl
import com.muse.wprk.main.model.Show

class GetShowUseCase(
    private val sprinitronRepositoryImpl: SpinitronRepositoryImpl
) {
     suspend operator fun invoke(onFetchCompletion: (Resource<Show>) -> Unit){
         onFetchCompletion(sprinitronRepositoryImpl.getShows(Constants.SPINITRON_KEY))
    }
}