package com.github.sseung416.mydailynote.local

import androidx.room.TypeConverter
import com.github.sseung416.mydailynote.ui.theme.GoalColor
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Room 에서 사용하지 못하는 타입을 변환해주는 클래스
 * */
class Converters {

    @TypeConverter
    fun fromTimestamp(dateString: String?): LocalDate? = dateString?.let { LocalDate.parse(it) }

    @TypeConverter
    fun dateToTimestamp(localDate: LocalDate?): String? =
        localDate?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    @TypeConverter
    fun stringToGoalColor(str: String): GoalColor = GoalColor.valueOf(str)

    @TypeConverter
    fun colorToString(color: GoalColor): String = color.toString()
}