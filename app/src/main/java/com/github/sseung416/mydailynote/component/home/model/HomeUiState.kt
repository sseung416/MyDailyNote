package com.github.sseung416.mydailynote.component.home.model

import java.util.Calendar
import java.util.Date

data class HomeUiState(
    val selectedDate: Date = Calendar.getInstance().time,
    val goalId: Int? = null, // 할일 추가할 때 목표 아이디 값
    val todoId: Int? = null // 수정하거나 삭제할 할일의 아이디 값
)
