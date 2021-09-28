package com.muse.wprk_concept.screens.Podcasts

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.muse.wprk_concept.data.Transistor.Episode
import com.muse.wprk_concept.data.Transistor.Podcast
import com.muse.wprk_concept.remote.TransistorRepository
import com.muse.wprk_concept.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val repo: TransistorRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _podcasts: MutableLiveData<List<Podcast>> = MutableLiveData()
    var podcasts: LiveData<List<Podcast>> = _podcasts
    var isLoading = mutableStateOf(false)

    var loadError = mutableStateOf("")

    private val _episodes: MutableLiveData<List<Episode>> = MutableLiveData()
    var episode: LiveData<List<Episode>> = _episodes
    var isLoadingEpisode = mutableStateOf(false)

    init {
        fetchShows {
            when(it) {
                is Resource.Success -> {
                    _podcasts.value = it.data!!
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

    private fun fetchShows(completion: (Resource<Podcast>) -> Unit) {
        viewModelScope.launch {
            val result = repo.getPodcasts()
            completion(result)
        }
    }

    private fun fetchEpisodes(showID: String, completion: (Resource<Episode>) -> Unit) {
        viewModelScope.launch {
            val result = repo.getEpisode(showID = showID)
            completion(result)
        }
    }

    fun getEpisodes(showID: String){
        fetchEpisodes(showID) {
            when(it) {
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