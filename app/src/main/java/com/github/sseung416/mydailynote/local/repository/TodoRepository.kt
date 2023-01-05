package com.github.sseung416.mydailynote.local.repository

import com.github.sseung416.mydailynote.local.dto.Todo

interface TodoRepository {

    suspend fun insertTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)
}