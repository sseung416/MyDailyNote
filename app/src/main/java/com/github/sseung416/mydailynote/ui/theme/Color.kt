package com.github.sseung416.mydailynote.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Grey2 = Color(0xFF9B9B9B)

// TODO: @Stable, @Immutable ...
object GoalColor {
    val red = Color(0xFFFF6262)
    val orange = Color(0xFFFFA800)
    val yellow = Color(0xFFFFC83A)
    val green1 = Color(0xFF78CD4F)
    val green2 = Color(0xFF578A52)
    val blue1 = Color(0xFF66B6FF)
    val blue2 = Color(0xFF4664FF)
    val purple = Color(0xFFBA7FF5)
    val pink = Color(0xFFFF8EB7)
    val grey3 = Color(0xFF9B9B9B)

    // TODO: 이 노답 하드코딩,.. 방법 찾아보기
    private val colorList =
        listOf(red, orange, yellow, green1, green2, blue1, blue2, purple, pink, grey3)

    // Color의 개수
    @Stable
    val count = colorList.count()

    fun getAllColors() = colorList
}