package com.muse.wprk_concept.composables.Podcasts

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.muse.wprk_concept.data.Show
import com.muse.wprk_concept.data.Transistor.Podcast
import com.muse.wprk_concept.remote.TransistorRepository
import com.muse.wprk_concept.utilities.Constants
import com.muse.wprk_concept.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val repo: TransistorRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _podcasts: MutableLiveData<List<Podcast>> = MutableLiveData()
    var podcasts: LiveData<List<Podcast>> = _podcasts
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private val _episodes: MutableLiveData<List<Podcast>> = MutableLiveData()
    var episode: LiveData<List<Podcast>> = _podcasts
    var loadError2 = mutableStateOf("")
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


}