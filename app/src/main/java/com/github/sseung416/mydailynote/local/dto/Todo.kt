package com.github.sseung416.mydailynote.local.dto

import androidx.room.*
import java.util.Date

@Entity(
    tableName = "todo",
    foreignKeys = [ForeignKey(
    entity = Goal::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("goal_id")
)])
data class Todo(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "goal_id") val goalId: Int,
    var todo: String,
    var date: Date,
    var isCompleted: Boolean = false,
    var isRepeat: Boolean = false,
) {
    @Ignore var type: TodoType = TodoType.Add
}

sealed class TodoType {
    object Add : TodoType()
    data class Update(val todo: Todo) : TodoType()
    object Default : TodoType()
}