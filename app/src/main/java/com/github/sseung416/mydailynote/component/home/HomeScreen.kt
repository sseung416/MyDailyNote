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

        // 다이얼로그 표시
        when (showDialog) {
            is HomeDialog.EditTodo -> {
                val todo = (showDialog as HomeDialog.EditTodo).todo

                EditTodoDialog(
                    onEditButtonClick = { viewModel.`꼭바꿔라`(todoId = todo.id) },
                    onDeleteButtonClick = { viewModel.deleteTodo(todo) },
                    onTomorrowButtonClick = { /*TODO*/ },
                    onRepeatButtonClick = { }
                )
            }

            HomeDialog.Menu -> {

            }

            HomeDialog.AddGoal -> {
                AddGoalDialog(
                    onConfirmButtonClick = { goal -> viewModel.insertGoal(goal) },
                    onCloseButtonClick = { viewModel.showDialog(HomeDialog.Nothing) }
                )
            }

            HomeDialog.Nothing -> {}
        }
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
private fun DateCard(
    checked: Boolean = false, // 선택되었는지 여부
    isVisibleDay: Boolean = true, // 요일 표시 여부
    hasTodo: Boolean = false, // 할일을 작성했는지 여부
    onClick: () -> Unit,
) {
    val color = if (checked) {
        Color.Black
    } else {
        Color.DarkGray
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp, 0.dp)
            .clickable { onClick.invoke() }
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(39.dp)) {
            Image(
                painter = painterResource(R.drawable.ic_circle_border),
                contentDescription = "date circle",
                colorFilter = ColorFilter.tint(color)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BoldText(text = "12", color = color, fontSize = 11.sp)

                if (isVisibleDay)
                    BoldText(text = "금", color = color, fontSize = 9.sp)
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // 날짜별 할일이 있는지 체크
        if (hasTodo) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color)
                    .size(5.dp)
            )
        }
    }
}

@Composable
private fun GoalItem(
    goal: Goal,
    onAddButtonClick: () -> Unit
) {
    val color = GoalColor.valueOf(goal.color).color

    Row(modifier = Modifier.background(color)) {
        RegularText(text = goal.name)
        ImageButton(
            onClick = onAddButtonClick,
            imageResourceId = R.drawable.ic_add,
            drawableDescription = "add todo",
            imageTintColor = color
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TodoItem(
    todo: Todo,
    onLongClick: () -> Unit
) {
    var checked by rememberSaveable { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(0.dp, 8.dp)
    ) {
        // checkBox drawable
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.combinedClickable(
                onClick = { checked = checked.not() },
                onLongClick = onLongClick
            ),
        ) {
            TodoCheckBox(checked = checked)
        }

        Spacer(modifier = Modifier.width(8.dp))

        RegularText(text = todo.todo)
    }
}

@Composable
private fun TodoCheckBox(checked: Boolean) {
    val imageRes =
        if (checked) R.drawable.ic_checkbox_true else R.drawable.ic_checkbox_false
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "check box drawable"
    )
}