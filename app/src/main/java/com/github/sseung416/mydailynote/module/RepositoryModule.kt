package com.github.sseung416.mydailynote.module

import com.github.sseung416.mydailynote.local.dao.GoalDao
import com.github.sseung416.mydailynote.local.dao.TodoDao
import com.github.sseung416.mydailynote.local.repository.GoalRepository
import com.github.sseung416.mydailynote.local.repository.GoalRepositoryImpl
import com.github.sseung416.mydailynote.local.repository.TodoRepository
import com.github.sseung416.mydailynote.local.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesGoalRepository(dao: GoalDao): GoalRepository =
        GoalRepositoryImpl(dao)

    @Provides
    @Singleton
    fun providesTodoRepository(dao: TodoDao): TodoRepository =
        TodoRepositoryImpl(dao)
}