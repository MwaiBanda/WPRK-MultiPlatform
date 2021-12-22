package com.muse.wprk.presentation.shows

import android.util.Log
import androidx.annotation.MainThread
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muse.wprk.core.utilities.Resource
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.usecase.GetShowUseCase
import com.toddway.shelf.Shelf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LiveViewModel @Inject constructor(
    private val getShowUseCase: GetShowUseCase,
    private val shelf: Shelf,
    ): ViewModel() {

    private val _shows: MutableLiveData<List<Show>> = MutableLiveData()
    var shows: LiveData<List<Show>> = _shows
    private var loadError = mutableStateOf("")
    private var isLoading = mutableStateOf(false)

    private val _selectedDate = MutableLiveData(0)
    var selectedDate: LiveData<Int> = _selectedDate


    fun getShows(onSuccess: () -> Unit) {
        viewModelScope.launch {
            getShowUseCase {
                when (it) {
                    is Resource.Success -> {
                        _shows.value = it.data!!
                        isLoading.value = false
                        Log.d("Main", "Fetch Success $shows")
                        onSuccess()
                    }
                    is Resource.Error -> {
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
    @MainThread
    fun onScheduleChange(scheduleDate: LocalDate): Flow<List<Show>> {
       val shows = shelf.item("SHOWS").getList(Show::class).orEmpty()
        return flow {
                emit(shows)
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