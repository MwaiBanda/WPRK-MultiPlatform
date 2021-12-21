package com.muse.wprk_concept.main.model

import com.muse.wprk_concept.core.utilities.ShowTime
import kotlinx.serialization.Serializable
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@Serializable
data class Show(
    val id: Int,
    val category: String? = null,
    val description: String,
    val duration: Int,
    val end: String,
    val image: String,
    val since: String? = null,
    val start: String,
    val timezone: String,
    val title: String,
    val url: String
)

fun Show.getDate(showTime: ShowTime): String {
    val date = when(showTime) {
        ShowTime.START -> {
            start.dropLast(14)
        }
        ShowTime.END -> {
            end.dropLast(14)
        }
    }
    return date
}
fun Show.getTime(showTime: ShowTime): String {
    val timeStr = when(showTime) {
        ShowTime.START -> {
            start.drop(11).dropLast(8)
        }
        ShowTime.END -> {
            end.drop(11).dropLast(8)
        }
    }
    return timeStr
}


fun Show.getFormattedDate(showTime: ShowTime): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = when(showTime) {
        ShowTime.START -> {
            LocalDate.parse(getDate(showTime = ShowTime.START), formatter)
        }
        ShowTime.END -> {
            LocalDate.parse(getDate(showTime = ShowTime.END), formatter)
        }
    }
    return date!!
}



