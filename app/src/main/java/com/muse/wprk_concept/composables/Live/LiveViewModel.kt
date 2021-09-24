package com.muse.wprk_concept.composables.Live

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.jakewharton.threetenabp.AndroidThreeTen
import com.muse.wprk_concept.data.Show
import com.muse.wprk_concept.remote.SpinitronRepository
import com.muse.wprk_concept.utilities.Constants.SPINITRON_KEY
import com.muse.wprk_concept.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import java.util.*
import javax.annotation.meta.When
import javax.inject.Inject

@HiltViewModel
class LiveViewModel @Inject constructor(
    private val repository: SpinitronRepository,
    private val savedStateHandle: SavedStateHandle
    ): ViewModel()
{
    private val _shows: MutableLiveData<List<Show>> = MutableLiveData()
    var shows: LiveData<List<Show>> = _shows
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private val _selectedDate = MutableLiveData(0)
    var selectedDate: LiveData<Int> = _selectedDate

    init {
        fetchShows {
            when(it) {
                is Resource.Success -> {
                    _shows.value = it.data!!
                    isLoading.value = false
                    Log.d("Main", "Fetch Success $shows")
                }
                is Resource.Error -> {
                    isLoading.value = false
                    loadError.value  = it.message ?: ""
                    Log.d("Main", "Fetch Failure ${loadError.value}")
                }
                is Resource.Loading -> {
                    isLoading.value = true
                }
            }
        }

    }

    private fun fetchShows(completion: (Resource<Show>) -> Unit) {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getShows(accessToken = SPINITRON_KEY)
            completion(result)
        }
    }
    fun onSelectedChange(newValue: Int) {
        _selectedDate.value = newValue
    }
    fun currentDay(): LocalDate {
        return LocalDateEx.getNow()
    }

    fun getDayByOffset(offset: Long): LocalDate {
        return currentDay().plusDays(offset)
    }
}
object LocalDateEx {
    @JvmStatic
    fun getNow(): LocalDate = Calendar.getInstance().toLocalDate()
}

fun Calendar.toLocalDate(): LocalDate = LocalDate.of(get(Calendar.YEAR), get(Calendar.MONTH) + 1, get(Calendar.DAY_OF_MONTH))