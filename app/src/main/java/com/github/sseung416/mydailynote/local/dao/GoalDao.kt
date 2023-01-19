package com.github.sseung416.mydailynote.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.github.sseung416.mydailynote.local.dto.Goal
import com.github.sseung416.mydailynote.local.dto.Todo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.*

@Dao
interface GoalDao : BaseDao<Goal> {

    @Query("SELECT * FROM GOAL")
    fun getGoals(): Flow<List<Goal>>

    @Query("SELECT G.*, T.* FROM GOAL G " +
            "LEFT OUTER JOIN " +
            "(SELECT * FROM TODO WHERE TODO.date = :date) T " +
            "ON G.g_id = T.t_g_id")
    fun getAllGoalWithTodos(date: LocalDate): Flow<Map<Goal, List<Todo>>>
}