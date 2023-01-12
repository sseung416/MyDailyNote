package com.github.sseung416.mydailynote.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.sseung416.mydailynote.ui.theme.GoalColor

@Entity(tableName = "goal")
data class Goal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="g_id")
    var id: Int? = null,

    val name: String,

    val goalColor: GoalColor,

    val isCompleted: Boolean = false,
)