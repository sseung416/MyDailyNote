package com.github.sseung416.mydailynote.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.sseung416.mydailynote.local.dao.GoalDao
import com.github.sseung416.mydailynote.local.dao.TodoDao
import com.github.sseung416.mydailynote.local.dto.Goal
import com.github.sseung416.mydailynote.local.dto.Todo

@Database(entities = [Goal::class, Todo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun goalDao(): GoalDao
    abstract fun todoDao(): TodoDao
}