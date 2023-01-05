package com.github.sseung416.mydailynote.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.github.sseung416.mydailynote.local.dto.Goal
import com.github.sseung416.mydailynote.local.dto.Todo
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface GoalDao : BaseDao<Goal> {

    @Query("SELECT * FROM GOAL")
    fun getGoals(): Flow<List<Goal>>

    @Query("SELECT GOAL.*, TODO.* FROM GOAL " +
            "LEFT OUTER JOIN TODO " +
            "ON GOAL.ID = TODO.GOAL_ID " +
            "WHERE TODO.DATE = :date")
    fun getAllGoalWithTodos(date: Date): Flow<Map<Goal, List<Todo>>>
}