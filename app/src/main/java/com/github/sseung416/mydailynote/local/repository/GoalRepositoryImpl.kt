package com.github.sseung416.mydailynote.local.repository

import com.github.sseung416.mydailynote.local.dao.GoalDao
import com.github.sseung416.mydailynote.local.dto.Goal
import com.github.sseung416.mydailynote.local.dto.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.util.*

class GoalRepositoryImpl(
    private val dao: GoalDao
) : GoalRepository {

    override fun getGoals(): Flow<List<Goal>> =
        dao.getGoals()

    override fun getAllGoalsWithTodos(date: Date): Flow<SortedMap<Goal, List<Todo>>> =
        dao.getAllGoalWithTodos(date).transform { map ->
            emit(map.toSortedMap(compareBy { it.id }))
        } // 목표를 추가한 순서대로 정렬해서 보냄

    override suspend fun insertGoal(goal: Goal) {
        dao.insert(goal)
    }

    override suspend fun updateGoal(goal: Goal) {
        dao.update(goal)
    }

    override suspend fun deleteGoal(goal: Goal) {
        dao.delete(goal)
    }
}