package com.github.sseung416.mydailynote.module

import android.content.Context
import androidx.room.Room
import com.github.sseung416.mydailynote.local.AppDatabase
import com.github.sseung416.mydailynote.local.dao.GoalDao
import com.github.sseung416.mydailynote.local.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "todo.db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providesGoalDao(database: AppDatabase): GoalDao =
        database.goalDao()

    @Singleton
    @Provides
    fun providesTodoDao(database: AppDatabase): TodoDao =
        database.todoDao()
}