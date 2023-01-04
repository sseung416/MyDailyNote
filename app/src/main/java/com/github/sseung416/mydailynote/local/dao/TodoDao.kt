package com.github.sseung416.mydailynote.local.dao

import androidx.room.Dao
import com.github.sseung416.mydailynote.local.dto.Todo

@Dao
interface TodoDao : BaseDao<Todo>