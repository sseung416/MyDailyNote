package com.github.sseung416.mydailynote.local.repository

import com.github.sseung416.mydailynote.local.dto.Goal
import com.github.sseung416.mydailynote.local.dto.Todo
import kotlinx.coroutines.flow.Flow
import java.util.*

interface GoalRepository {

    /**
     * 모든 목표를 조회함
     * */
    fun getGoals(): Flow<List<Goal>>

    /**
     * 모든 목표와 그 목표에 속해있는 할일을 조회함
     * */
    fun getAllGoalsWithTodos(date: Date): Flow<SortedMap<Goal, List<Todo>>> // todo 키 값 오름차순으로 정렬

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