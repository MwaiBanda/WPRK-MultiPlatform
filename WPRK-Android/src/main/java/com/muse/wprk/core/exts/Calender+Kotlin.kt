package com.muse.wprk.core.exts

import org.threeten.bp.LocalDate
import java.util.*


object LocalDateEx {
    @JvmStatic
    fun getNow(): LocalDate = Calendar.getInstance().toLocalDate()
}

fun Calendar.toLocalDate(): LocalDate = LocalDate.of(get(Calendar.YEAR), get(Calendar.MONTH) + 1, get(
    Calendar.DAY_OF_MONTH))