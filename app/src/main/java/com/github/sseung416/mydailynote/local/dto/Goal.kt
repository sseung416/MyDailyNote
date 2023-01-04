package com.github.sseung416.mydailynote.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal")
data class Goal(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    val name: String,
    val color: String,
    val isCompleted: Boolean = false,
)