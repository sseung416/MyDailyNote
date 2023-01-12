package com.github.sseung416.mydailynote.local.repository

import com.github.sseung416.mydailynote.local.dao.GoalDao
import com.github.sseung416.mydailynote.local.dto.Goal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.util.*

class GoalRepositoryImpl(
    private val dao: GoalDao
) : GoalRepository {

    override fun getGoals(): Flow<List<Goal>> =
        dao.getGoals()

    override fun getAllGoalsWithTodos(date: Date): Flow<List<Any>> =
        dao.getAllGoalWithTodos(date).transform { map ->
            val list = arrayListOf<Any>()

            // goal id 순으로 정렬 후 리스트로 변환
            map.toSortedMap(compareBy { it.id }).forEach { (goal, todos) ->
                list.add(goal)
                todos.forEach {
                    list.add(it)
                }
            }

            emit(list)
        }

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