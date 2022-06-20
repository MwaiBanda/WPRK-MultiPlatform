package com.muse.wprk.presentation.podcasts

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.muse.wprk.core.exts.LocalDateEx
import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.usecase.GetEpisodesUseCase
import com.muse.wprk.main.usecase.GetPodcastsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val getPodcastsUseCase: GetPodcastsUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase,
): ViewModel() {

    private val _podcasts: MutableLiveData<List<Podcast>> = MutableLiveData()
    var podcasts: LiveData<List<Podcast>> = _podcasts
    var isLoading = mutableStateOf(false)

    var loadError = mutableStateOf("")

    private val _episodes: MutableLiveData<List<Episode>> = MutableLiveData()
    var episodes: LiveData<List<Episode>> = _episodes
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

    fun getFeaturedEpisodes(showID: String) {
        viewModelScope.launch {
            getEpisodesUseCase(showID) {
                when (it) {
                    is Resource.Success -> {
                        _episodes.value = it.data?.take(4)
                            ?.sortedBy { it.number }
                            ?.reversed()
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