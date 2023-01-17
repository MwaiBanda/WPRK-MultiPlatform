package com.mwaibanda.wprksdk.main.usecase.podcasts

import com.mwaibanda.wprksdk.main.model.Podcast
import com.mwaibanda.wprksdk.main.repository.PodcastRepository
import com.mwaibanda.wprksdk.util.Resource


class GetPodcastsUseCase (
    private val podcastRepository: PodcastRepository
    ) {
    suspend operator fun invoke(onFetchCompletion: (Resource<List<Podcast>>) -> Unit){
        onFetchCompletion(podcastRepository.getPodcasts())
    }
}