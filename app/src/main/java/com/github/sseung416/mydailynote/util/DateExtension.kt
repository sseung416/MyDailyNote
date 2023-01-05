package com.github.sseung416.mydailynote.util

import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT = "yyyyMMdd"
private val calendar = Calendar.getInstance()

internal val currentDate = Calendar.getInstance().time

fun getTodayString(): String = calendar.time.formatToString()

fun Int.getCalendar(): Calendar =
    calendar.apply { this.set(Calendar.DATE, this@getCalendar) }

fun Int.getWeekDateString(weekType: Int = DateExtension.THIS_WEEK): String =
    with(calendar) {
        if (weekType == DateExtension.NEXT_WEEK)
            this.add(Calendar.DATE, 14)

        this.set(Calendar.DAY_OF_WEEK, this@getWeekDateString)
        time.formatToString()
    }

fun Date.formatToString(): String = SimpleDateFormat(FORMAT).format(this)

fun String.formatToDate(): Date = SimpleDateFormat(FORMAT).parse(this)

fun Date.sum(num: Int): Date {
    Calendar.getInstance().apply {
        time = this@sum
        add(Calendar.DATE, num)
        return time
    }
}

object DateExtension {
    const val THIS_WEEK = 0
    const val NEXT_WEEK = 1
}