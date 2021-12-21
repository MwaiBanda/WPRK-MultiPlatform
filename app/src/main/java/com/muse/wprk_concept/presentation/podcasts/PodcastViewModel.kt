package com.muse.wprk_concept.presentation.podcasts

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.muse.wprk_concept.core.utilities.Resource
import com.muse.wprk_concept.main.model.Episode
import com.muse.wprk_concept.main.model.Podcast
import com.muse.wprk_concept.main.usecase.GetEpisodesUseCase
import com.muse.wprk_concept.main.usecase.GetPodcastsUseCase
import com.muse.wprk_concept.presentation.shows.LocalDateEx
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val getPodcastsUseCase: GetPodcastsUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _podcasts: MutableLiveData<List<Podcast>> = MutableLiveData()
    var podcasts: LiveData<List<Podcast>> = _podcasts
    var isLoading = mutableStateOf(false)

    var loadError = mutableStateOf("")

    private val _episodes: MutableLiveData<List<Episode>> = MutableLiveData()
    var episodeDTO: LiveData<List<Episode>> = _episodes
    var isLoadingEpisode = mutableStateOf(false)


    fun getPodcasts(onSuccess: () -> Unit){
        viewModelScope.launch {
            getPodcastsUseCase {
                when (it) {
                    is Resource.Success -> {
                        _podcasts.value = it.data!!
                        Log.d("Main", "Fetch Success ${it.data!!}")
                        onSuccess()
                    }
                    is Resource.Error -> {
                        loadError.value = it.message!!
                        Log.d("Main", "Fetch Failure ${it.message!!}")
                    }
                    is Resource.Loading -> {
                    }
                }
            }
        }
    }


    fun getEpisodes(showID: String){
        viewModelScope.launch {
            getEpisodesUseCase(showID) {
                when (it) {
                    is Resource.Success -> {
                        _episodes.value = it.data!!
                        Log.d("Main", "Fetch Success ${it.data!!}")
                    }
                    is Resource.Error -> {
                        loadError.value = it.message!!
                        Log.d("Main", "Fetch Failure ${it.message!!}")
                    }
                    is Resource.Loading -> {
                    }
                }
            }
        }
    }
    fun currentDay(): LocalDate {
        return LocalDateEx.getNow()
    }

    fun getDayByOffset(offset: Long): LocalDate {
        return currentDay().plusDays(offset)
    }

}