package com.github.sseung416.mydailynote.local

import androidx.room.TypeConverter
import com.github.sseung416.mydailynote.ui.theme.GoalColor
import java.util.*

/**
 * Room 에서 사용하지 못하는 타입을 변환해주는 클래스
 * */
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun stringToGoalColor(str: String): GoalColor = GoalColor.valueOf(str)

    @TypeConverter
    fun colorToString(color: GoalColor): String = color.toString()
}