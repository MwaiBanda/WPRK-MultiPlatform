package com.muse.wprk.main.model

import android.annotation.SuppressLint
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
) {
    private fun getDate(showTime: ShowTime): String {
        val date = when (showTime) {
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
    fun getTime(showTime: ShowTime): String {
        val formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)

        val timeStr = when (showTime) {
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
                    SimpleDateFormat("h:mm a").format(dateObj)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                res.toString()

            }
            ShowTime.END -> {
                val date: String = end.drop(11).dropLast(8)
                val res = try {
                    val sdf = SimpleDateFormat("HH:mm")
                    val dateObj: Date = sdf.parse(date)
                    SimpleDateFormat("h:mm a").format(dateObj)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                res.toString()
            }
        }
        return timeStr
    }

    fun getShowDate(showTime: ShowTime): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val date = when (showTime) {
            ShowTime.START -> {
                val current = getTime(ShowTime.START)
                val date =
                    (if (current.endsWith("PM") && (current.split(":").first().toInt()) > 6) {
                        LocalDateTime.parse(getDate(showTime = ShowTime.START), formatter)
                            .minusDays(1)
                    } else {
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
    fun getShowDateTime(showTime: ShowTime): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val date = when (showTime) {
            ShowTime.START -> {
                val current = getTime(ShowTime.START)
                val date =
                    if (current.endsWith("PM") && (current.split(":").first().toInt()) > 6) {
                        LocalDateTime.parse(getDate(showTime = ShowTime.START), formatter)
                            .minusDays(1).minusHours(5)
                    } else {
                        LocalDateTime.parse(getDate(showTime = ShowTime.START), formatter).minusHours(5)
                    }
                date

            }
            ShowTime.END -> {
                LocalDateTime.parse(getDate(showTime = ShowTime.END), formatter)
            }
        }
        return date
    }
    fun getDisplayDate(): String {
        val res = try {
            val dateObj: LocalDate = getShowDate(ShowTime.START)
            val formatter = DateTimeFormatter.ofPattern("E, d MMM yyyy")
            formatter.format(dateObj)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return res.toString()
    }
}

