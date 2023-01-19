package com.github.sseung416.mydailynote.local.repository

import com.github.sseung416.mydailynote.local.dto.Goal
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface GoalRepository {

    /**
     * 모든 목표를 조회함
     * */
    fun getGoals(): Flow<List<Goal>>

    /**
     * 모든 목표와 그 목표에 속해있는 할일을 조회함
     * */
    fun getAllGoalsWithTodos(date: LocalDate): Flow<List<Any>>

    /**
     * 목표를 삽입함
     *
     * @param goal
     * */
    suspend fun insertGoal(goal: Goal)

    /**
     * 목표를 수정함
     *
     * @param goal
     * */
    suspend fun updateGoal(goal: Goal)

    /**
     * 목표를 삭제함
함    *
     * @param goal
     * */
    suspend fun deleteGoal(goal: Goal)

}