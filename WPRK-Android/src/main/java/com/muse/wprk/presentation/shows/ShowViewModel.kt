package com.muse.wprk.presentation.shows

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muse.wprk.core.exts.LocalDateEx
import com.muse.wprk.core.exts.getShowDate
import com.muse.wprk.core.utilities.ShowTime
import com.mwaibanda.wprksdk.main.model.Show
import com.mwaibanda.wprksdk.main.usecase.shows.GetShowUseCase
import com.mwaibanda.wprksdk.util.Resource
import com.mwaibanda.wprksdk.util.ShowResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(
    private val getShowUseCase: GetShowUseCase,
): ViewModel() {

    private val _shows: MutableLiveData<List<Show>> = MutableLiveData()
    var shows: LiveData<List<Show>> = _shows

    private val _scheduledShows: MutableLiveData<List<Show>> = MutableLiveData()
    var scheduledShows: LiveData<List<Show>> = _scheduledShows

    private var loadError = mutableStateOf("")
    private var isLoading = mutableStateOf(false)

    private val _selectedDate = MutableLiveData(0)
    var selectedDate: LiveData<Int> = _selectedDate


    fun getShows(onSuccess: () -> Unit) {
        viewModelScope.launch {
            getShowUseCase {
                when (it) {
                    is Resource.Success-> {
                        _shows.value = it.data!!
                        isLoading.value = false
                        Log.d("Main", "Fetch Success ${shows.value}")
                        onSuccess()
                    }
                    is Resource.Error-> {
                        isLoading.value = false
                        loadError.value = it.message ?: ""
                        Log.d("Main", "Fetch Failure ${loadError.value}")
                    }
                    is Resource.Loading -> {
                        isLoading.value = true
                    }
                }
            }
        }
    }
    fun onScheduleChange(scheduleDate: LocalDate) {
        _scheduledShows.value = _shows.value?.filter { it.getShowDate(ShowTime.START) == scheduleDate }
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

fun ViewModel.getViewModelScope(coroutineScope: CoroutineScope?) =
    if (coroutineScope == null) this.viewModelScope
    else coroutineScope