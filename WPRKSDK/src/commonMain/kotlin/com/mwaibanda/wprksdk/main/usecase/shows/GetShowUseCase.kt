package com.mwaibanda.wprksdk.main.usecase.shows

import com.mwaibanda.wprksdk.main.repository.ShowsRepository
import com.mwaibanda.wprksdk.util.Constants
import com.mwaibanda.wprksdk.util.Resource
import com.mwaibanda.wprksdk.util.ShowResponse


class GetShowUseCase(
    private val showsRepository: ShowsRepository
) {
    suspend operator fun invoke(onFetchCompletion: (Resource<ShowResponse>) -> Unit) {
        onFetchCompletion(showsRepository.getShows(Constants.SPINITRON_KEY))
    }
}