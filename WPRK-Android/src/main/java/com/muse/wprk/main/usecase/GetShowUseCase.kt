package com.muse.wprk.main.usecase

import com.muse.wprk.core.utilities.Constants
import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.repository.SpinitronRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetShowUseCase @Inject constructor(
    private val spinitronRepository: SpinitronRepository
) {
     suspend operator fun invoke(onFetchCompletion: (Resource<Show>) -> Unit){
         onFetchCompletion(spinitronRepository.getShows(Constants.SPINITRON_KEY))
    }
}