package com.github.sseung416.mydailynote

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Colors
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.sseung416.mydailynote.base.*
import com.github.sseung416.mydailynote.ui.theme.GoalColor

// https://blog.logrocket.com/adding-alertdialog-jetpack-compose-android-apps/

@Composable
fun EditTodoDialog(
    onEditButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onTomorrowButtonClick: () -> Unit,
    onRepeatButtonClick: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(true) }
    val dismissListener = { showDialog = true } // TODO: 이거 맞음? 

    if (showDialog) {
        AppAlertDialog {
            Row(modifier = Modifier.fillMaxWidth()) {
                ImageTextButton(
                    onClick = concatListener(onEditButtonClick, dismissListener),
                    text = "수정",
                    imageResourceId = R.drawable.ic_circle_edit,
                    drawableDescription = "edit"
                )

                ImageTextButton(
                    onClick = concatListener(onDeleteButtonClick, dismissListener),
                    text = "삭제",
                    imageResourceId = R.drawable.ic_circle_close,
                    drawableDescription = "delete"
                )

                ImageTextButton(
                    onClick = concatListener(onTomorrowButtonClick, dismissListener),
                    text = "내일하기",
                    imageResourceId = R.drawable.ic_circle_next,
                    drawableDescription = "tomorrow"
                )

                ImageTextButton(
                    onClick = concatListener(onTomorrowButtonClick, dismissListener),
                    text = "반복",
                    imageResourceId = R.drawable.ic_circle_repeat,
                    drawableDescription = "repeat"
                )
            }
        }
    }
}

// todo showDialog 부분 어떻게 베이스 다이얼로그로 옮길지 고민하기
@Composable
@ExperimentalFoundationApi
fun AddGoalDialog() {
    var showDialog by remember { mutableStateOf(true) } // 근데 이렇게 하는거 맞긴 함?

    if (showDialog) {
        AppAlertDialog {
            Column {
                Row {
                    ImageButton(
                        onClick = { showDialog = !showDialog },
                        imageResourceId = R.drawable.ic_close,
                        drawableDescription = "dismiss"
                    )

                    // https://tedblob.com/jetpack-compose-spacer/
                    Spacer(modifier = Modifier.weight(1f))

                    ImageButton(
                        onClick = { /*TODO*/ },
                        imageResourceId = R.drawable.ic_check,
                        drawableDescription = "confirm"
                    )
                }

                AppTextField("목표를 입력해주세요!")
                
                Spacer(modifier = Modifier.size(24.dp))
                
                BoldText(text = "대표 색")

                ColorPalette()
            }
        }
    }
}

@Composable
@ExperimentalFoundationApi
private fun ColorPalette() {
    // 선택한 색상의 인덱스 값
    var selectedIndex by remember { mutableStateOf(-1) }

    LazyVerticalGrid(cells = GridCells.Fixed(5)) {
        itemsIndexed(GoalColor.getAllColors()) { index, item ->
            // 색상 선택 버튼
            ImageButton(
                onClick = {
                    selectedIndex = index
                },
                imageResourceId = R.drawable.ic_circle_solid,
                drawableDescription = "goal color $index",
                imageTintColor = item
            ) {
                // todo drawable 웃는 얼굴로 바꾸기, 첫 번째 색상 미리 선택되어 있게 만들기

                if (selectedIndex == index) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = "select"
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewEditTodoDialog() {
    // todo logUtil 클래스 만들긔
    val printLog = { str: String -> Log.d("PreviewEditTodoDialog", "$str button clicked") }

    EditTodoDialog(
        onEditButtonClick = { printLog("edit") },
        onDeleteButtonClick = { printLog("delete") },
        onTomorrowButtonClick = { printLog("tomorrow") },
        onRepeatButtonClick = { printLog("repeat") })
}

@ExperimentalFoundationApi
@Composable
@Preview(showBackground = true)
private fun PreviewAddGoalDialog() {
    val printLog = { str: String -> Log.d("PreviewAddGoalDialog", "$str button clicked") }

    AddGoalDialog()
}

private val AppDialogBackground = Modifier
    .background(
        color = Color.White,
        shape = RoundedCornerShape(percent = 10)
    )
    .padding(16.dp)

@Composable
private fun AppAlertDialog(
    onDismissRequest: () -> Unit = {},
    background: Modifier = AppDialogBackground,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Transparent
        ) {
            Box(background) {
                content.invoke()
            }
        }
    }
}
