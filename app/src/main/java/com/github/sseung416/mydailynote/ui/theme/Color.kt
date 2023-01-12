package com.github.sseung416.mydailynote.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Grey2 = Color(0xFF9B9B9B)

@Stable
enum class GoalColor(val color: Color) {
    Red(Color(0xFFFF6262)),
    Orange(Color(0xFFFFA800)),
    Yellow(Color(0xFFFFC83A)),
    Green1(Color(0xFF78CD4F)),
    Green2(Color(0xFF578A52)),
    Blue1(Color(0xFF66B6FF)),
    Blue2(Color(0xFF4664FF)),
    Purple(Color(0xFFBA7FF5)),
    Pink(Color(0xFFFF8EB7)),
    Grey(Color(0xFF9B9B9B))
}