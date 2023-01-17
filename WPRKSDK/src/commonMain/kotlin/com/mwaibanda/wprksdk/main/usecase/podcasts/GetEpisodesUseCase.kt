package com.mwaibanda.wprksdk.main.usecase.podcasts

import com.mwaibanda.wprksdk.main.repository.PodcastRepository
import com.mwaibanda.wprksdk.util.EpisodeResponse
import com.mwaibanda.wprksdk.util.Resource


class GetEpisodesUseCase(
    private val podcastRepository: PodcastRepository
) {
    suspend operator fun invoke(showID: String, pageNumber: Int, onFetchCompletion: (Resource<EpisodeResponse>) -> Unit){
        onFetchCompletion(podcastRepository.getEpisodes(showID, pageNumber))
    }
}