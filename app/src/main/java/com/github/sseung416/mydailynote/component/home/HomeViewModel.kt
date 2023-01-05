package com.github.sseung416.mydailynote.component.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.sseung416.mydailynote.component.home.model.HomeUiState
import com.github.sseung416.mydailynote.local.dto.Goal
import com.github.sseung416.mydailynote.local.dto.Todo
import com.github.sseung416.mydailynote.local.repository.GoalRepository
import com.github.sseung416.mydailynote.local.repository.TodoRepository
import com.github.sseung416.mydailynote.util.currentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val todoRepository: TodoRepository
) : ViewModel() {

    val uiState = MutableStateFlow(HomeUiState())
    val dialogState = MutableStateFlow(HomeDialog.Nothing as HomeDialog) // todo rename

    val allGoalsWithTodos = goalRepository.getAllGoalsWithTodos(uiState.value.selectedDate) // todo 다른 변수 값이 변경되도 업데이트되는지 테스트
        .stateIn(
            viewModelScope, SharingStarted.Lazily, mapOf()
        )

    fun showDialog(type: HomeDialog) {
        dialogState.value = type
    }

    fun `꼭바꿔라`(selectedDate: Date? = null, goalId: Int? = null, todoId: Int? = null) {
        val currentState = uiState.value
        uiState.value = currentState.copy(
            selectedDate = selectedDate ?: currentDate,
            goalId = goalId,
            todoId = todoId
        )
    }

    fun insertGoal(goal: Goal) {
        viewModelScope.launch {
            goalRepository.insertGoal(goal)
        }
    }

    fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }
}