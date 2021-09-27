package com.muse.wprk_concept.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.muse.wprk_concept.data.Spinitron.Links
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

enum class ShowTime {
    START,
    END
}

data class Show(
    @SerializedName("_links")
    val links: Links,
    val category: String,
    val description: String,
    val duration: Int,
    val end: String,
    val hide_dj: Int,
    val id: Int,
    val image: String,
    val one_off: Boolean,
    val since: Any,
    val start: String,
    val timezone: String,
    val title: String,
    val url: String
)

fun Show.getDate(showTime: ShowTime): String {
    var date = when(showTime) {
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
    var timeStr = when(showTime) {
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
    var date = when(showTime) {
        ShowTime.START -> {
            LocalDate.parse(getDate(showTime = ShowTime.START), formatter)
        }
        ShowTime.END -> {
            LocalDate.parse(getDate(showTime = ShowTime.END), formatter)
        }
    }
    return date!!
}


data class Shows(
    @SerializedName("items")
    @Expose
    var collection: List<Show>? = null
)