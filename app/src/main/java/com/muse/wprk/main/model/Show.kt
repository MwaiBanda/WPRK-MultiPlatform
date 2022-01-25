package com.muse.wprk.main.model

import android.annotation.SuppressLint
import android.util.Log
import com.muse.wprk.core.utilities.ShowTime
import kotlinx.serialization.Serializable
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
            start.dropLast(5).replace("T", " ")
        }
        ShowTime.END -> {
            end.dropLast(14)
        }
    }
    return date
}

@SuppressLint("SimpleDateFormat")
fun Show.getTime(showTime: ShowTime): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)

    val timeStr = when(showTime) {
        ShowTime.START -> {
            val startTime = start.drop(11).dropLast(8)
           val date: String = startTime
               .replaceBefore(
                   ":",
                   "${
                       (startTime
                           .split(":")
                           .first()
                           .toInt()) - 5
                   }"
               )

            val res = try {
                val sdf = SimpleDateFormat("H:mm")
                val dateObj: Date = sdf.parse(date)
                System.out.println(dateObj)
                System.out.println(SimpleDateFormat("K:mm a").format(dateObj))
                SimpleDateFormat("K:mm a").format(dateObj)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            res.toString()
        }
        ShowTime.END -> {
            val date: String = end.drop(11).dropLast(8)
            try {
                val sdf = SimpleDateFormat("HH:mm")
                val dateObj: Date = sdf.parse(date)
                System.out.println(dateObj)
                Log.d("TIME/PW", SimpleDateFormat("KK:mm aa").format(dateObj))
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            date
        }
    }
    return timeStr
}


fun Show.getFormattedDate(showTime: ShowTime): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val date = when(showTime) {
        ShowTime.START -> {
            val current = getTime(ShowTime.START)
            val date = (if (current.endsWith("PM") && (current.split(":").first().toInt()) > 6) {
                 LocalDateTime.parse(getDate(showTime = ShowTime.START), formatter).minusDays(1)
            }
             else {
                 LocalDateTime.parse(getDate(showTime = ShowTime.START), formatter)
             }).toLocalDate()
            date

        }
        ShowTime.END -> {
            LocalDateTime.parse(getDate(showTime = ShowTime.END), formatter).toLocalDate()
        }
    }
    return date
}



