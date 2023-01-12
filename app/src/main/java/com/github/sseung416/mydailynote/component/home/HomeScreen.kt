package com.github.sseung416.mydailynote.component.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.sseung416.mydailynote.AddGoalDialog
import com.github.sseung416.mydailynote.EditTodoDialog
import com.github.sseung416.mydailynote.R
import com.github.sseung416.mydailynote.component.base.*
import com.github.sseung416.mydailynote.local.dto.Goal
import com.github.sseung416.mydailynote.local.dto.Todo
import com.github.sseung416.mydailynote.ui.theme.GoalColor
import com.github.sseung416.mydailynote.util.currentDate
import java.text.SimpleDateFormat
import java.util.Calendar

sealed class HomeDialog {
    data class EditTodo(val todo: Todo) : HomeDialog()
    object Menu : HomeDialog()
    object AddGoal : HomeDialog()
    object Nothing : HomeDialog()
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val allGoalsWithTodos by viewModel.allGoalsWithTodos.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val showDialog by viewModel.dialogState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TodoToolbar {
            // todo 캘린더로 확장하기
        }

        LazyRow(modifier = Modifier.padding(0.dp, 8.dp)) {
            itemsIndexed(listOf(1, 2, 3, 4, 5, 6, 7)) { index, item ->
                DateCard {}
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            itemsIndexed(allGoalsWithTodos.toList()) { _, (goal, todos) ->
                GoalItem(goal = goal, onAddButtonClick = { viewModel.`꼭바꿔라`(goalId = goal.id) })
                LazyColumn {
                    itemsIndexed(todos) { _, todo ->
                        TodoItem(todo, onLongClick = { viewModel.showDialog(HomeDialog.EditTodo(todo)) })
                        if (uiState.todoId == todo.id) { // 할일 수정
                            TodoTextField(todo = todo, onDone = {
                                todo.todo = it
                                viewModel.updateTodo(todo)
                            })
                        }
                    }
                }
                // 할일을 추가
                if (uiState.goalId == goal.id) {
                    TodoTextField(onDone = {
                        val todo = Todo(goalId = goal.id!!, todo = it, date = currentDate)
                        viewModel.insertTodo(todo)
                    })
                }
            }
        }

        ImageButton(
            onClick = { viewModel.showDialog(HomeDialog.Menu) },
            imageResourceId = R.drawable.ic_menu,
            drawableDescription = "menu"
        )

        DialogContent(
            dialogState = showDialog,
            editTodoDialogListener = lambdaToInterface(
                viewModel::updateTodo,
                viewModel::deleteTodo,
                {},
                {}
            ),
            addGoalDialogListener = lambdaToInterface(
                viewModel::insertGoal,
                viewModel::setDialogState
            )
        )
    }
}

@Composable
fun TodoTextField(
    todo: Todo? = null,
    onDone: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf(todo?.todo ?: "") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        TodoCheckBox(checked = false)
        Spacer(modifier = Modifier.size(8.dp))
        AppTextField(
            text = text,
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { onDone(text) })
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTodoTextField() {
    TodoTextField() {

    }
}

@Composable
private fun TodoToolbar(onClick: () -> Unit) {
    val time = Calendar.getInstance().time
    val dateString = SimpleDateFormat("MM월 dd일 월요일").format(time)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TitleText(text = dateString)
        IconButton(onClick = onClick) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "more"
            )
        }
    }
}

@Composable
fun DialogContent(
    dialogState: HomeDialogState,
    editTodoDialogListener: EditTodoDialogListener,
    addGoalDialogListener: AddGoalDialogListener
) {
    when (dialogState) {
        is HomeDialogState.EditTodo -> {
            val todo = dialogState.todo

            EditTodoDialog(
                onEdit = { editTodoDialogListener.onEdit(todo) },
                onDelete = { editTodoDialogListener.onDelete(todo) },
                onRepeat = { editTodoDialogListener.onRepeat(todo) },
                onTomorrow = { editTodoDialogListener.onTomorrow(todo) }
            )
        }

        HomeDialogState.Menu -> {
            AddGoalDialog(addGoalDialogListener = addGoalDialogListener)
        }

        HomeDialogState.AddGoal -> {}

        HomeDialogState.Nothing -> {}
    }
}

interface EditTodoDialogListener {

    fun onEdit(todo: Todo)
    fun onDelete(todo: Todo)
    fun onTomorrow(todo: Todo)
    fun onRepeat(todo: Todo)
}

interface AddGoalDialogListener {

    fun onAdd(goal: Goal)
    fun onClose()
}

fun lambdaToInterface(
    onEdit: (Todo) -> Unit,
    onDelete: (Todo) -> Unit,
    onTomorrow: (Todo) -> Unit,
    onRepeat: (Todo) -> Unit,
) = object : EditTodoDialogListener {

    override fun onEdit(todo: Todo) {
        onEdit.invoke(todo)
    }

    override fun onDelete(todo: Todo) {
        onDelete.invoke(todo)
    }

    override fun onTomorrow(todo: Todo) {
        onTomorrow.invoke(todo)
    }

    override fun onRepeat(todo: Todo) {
        onRepeat.invoke(todo)
    }
}

fun lambdaToInterface(
    onAdd: (Goal) -> Unit,
    onClose: () -> Unit
) = object : AddGoalDialogListener {

    override fun onAdd(goal: Goal) {
        onAdd.invoke(goal)
    }

    override fun onClose() {
        onClose.invoke()
    }
}