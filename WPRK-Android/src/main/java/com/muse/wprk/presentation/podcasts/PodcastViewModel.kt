package com.muse.wprk.presentation.podcasts

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.muse.wprk.core.exts.LocalDateEx
import com.mwaibanda.wprksdk.main.model.Episode
import com.mwaibanda.wprksdk.main.model.Podcast
import com.mwaibanda.wprksdk.main.usecase.podcasts.GetEpisodesUseCase
import com.mwaibanda.wprksdk.main.usecase.podcasts.GetPodcastsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject
import com.mwaibanda.wprksdk.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val getPodcastsUseCase: GetPodcastsUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase,
) : ViewModel() {

    private val _podcasts: MutableLiveData<List<Podcast>> = MutableLiveData()
    var podcasts: LiveData<List<Podcast>> = _podcasts
    var isLoading = mutableStateOf(false)

    var loadError = mutableStateOf("")

    private val _episodes: MutableLiveData<List<Episode>> = MutableLiveData()
    var episodes: LiveData<List<Episode>> = _episodes
    var isLoadingEpisode = mutableStateOf(false)
    private val currentPage = MutableStateFlow(1)
    private val _canLoadMore = MutableStateFlow(false)
    val canLoadMore = _canLoadMore.asStateFlow()


    fun getPodcasts(onSuccess: () -> Unit) {
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
            getEpisodesUseCase(showID, 1) {
                when (it) {
                    is Resource.Success -> {
                        _episodes.value = it.data?.third?.take(4)
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

    private fun getEpisodes(showID: String, pageNumber: Int) {
        viewModelScope.launch {
            getEpisodesUseCase(showID, pageNumber) {
                when (it) {
                    is Resource.Success -> {
                        _canLoadMore.value = it.data?.first ?: false
                        currentPage.value = (it.data?.second ?: 1) + 1
                        _episodes.value = buildList {
                            addAll(
                                ((it.data?.third ?: emptyList()) + (_episodes.value ?: emptyList()))
                            )
                            sortBy { it.number }
                            reverse()
                        }
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

    fun getEpisodes(showID: String) {
        getEpisodes(showID, currentPage.value)
    }

    fun currentDay(): LocalDate {
        return LocalDateEx.getNow()
    }

    fun getDayByOffset(offset: Long): LocalDate {
        return currentDay().plusDays(offset)
    }

}