package com.github.sseung416.mydailynote

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.sseung416.mydailynote.component.base.AppTextField
import com.github.sseung416.mydailynote.component.base.BoldText
import com.github.sseung416.mydailynote.component.base.ImageButton
import com.github.sseung416.mydailynote.component.base.ImageTextButton
import com.github.sseung416.mydailynote.component.home.AddGoalDialogListener
import com.github.sseung416.mydailynote.local.dto.Goal
import com.github.sseung416.mydailynote.ui.theme.GoalColor
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class IndexedValue<T>(val index: Int, val value: @RawValue T) : Parcelable

@Composable
fun EditTodoDialog(
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onTomorrow: () -> Unit,
    onRepeat: () -> Unit,
) {
    AppAlertDialog {
        Row(modifier = Modifier.fillMaxWidth()) {
            ImageTextButton(
                onClick = onEdit,
                text = "수정",
                imageResourceId = R.drawable.ic_circle_edit,
                drawableDescription = "edit"
            )

            ImageTextButton(
                onClick = onDelete,
                text = "삭제",
                imageResourceId = R.drawable.ic_circle_close,
                drawableDescription = "delete"
            )

            ImageTextButton(
                onClick = onTomorrow,
                text = "내일하기",
                imageResourceId = R.drawable.ic_circle_next,
                drawableDescription = "tomorrow"
            )

            ImageTextButton(
                onClick = onRepeat,
                text = "반복",
                imageResourceId = R.drawable.ic_circle_repeat,
                drawableDescription = "repeat"
            )
        }
    }
}

@Composable
fun AddGoalDialog(
    addGoalDialogListener: AddGoalDialogListener
) {
    var text by rememberSaveable { mutableStateOf("") }
    var selectedColor by rememberSaveable { mutableStateOf(IndexedValue(0, GoalColor.Red)) }

    val onConfirmButtonClickListener = {
        val goal = Goal(name = text, goalColor = selectedColor.value)
        addGoalDialogListener.onAdd(goal)
    }

    AppAlertDialog {
        Column {
            Row {
                ImageButton(
                    onClick = addGoalDialogListener::onClose,
                    imageResourceId = R.drawable.ic_close,
                    drawableDescription = "dismiss"
                )
                Spacer(modifier = Modifier.weight(1f))
                ImageButton(
                    onClick = onConfirmButtonClickListener,
                    imageResourceId = R.drawable.ic_check,
                    drawableDescription = "confirm"
                )
            }

            AppTextField(
                text = text,
                placeholder = "목표를 입력해주세요!",
                onValueChange = { text = it }
            )
            Spacer(modifier = Modifier.size(24.dp))

            BoldText(text = "대표 색")
            ColorPalette(
                selectedIndex = selectedColor.index,
                onColorChange = { selectedColor = it })
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ColorPalette(
    selectedIndex: Int,
    onColorChange: (IndexedValue<GoalColor>) -> Unit
) {
    LazyVerticalGrid(cells = GridCells.Fixed(5)) {
        itemsIndexed(GoalColor.values()) { index, item ->
            // 색상 선택 버튼
            ImageButton(
                onClick = { onColorChange.invoke(IndexedValue(index, item)) },
                imageResourceId = R.drawable.ic_circle_solid,
                drawableDescription = "goal color $index",
                imageTintColor = item.color
            ) {
                // todo drawable 웃는 얼굴로 바꾸기
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
        onEdit = { printLog("edit") },
        onDelete = { printLog("delete") },
        onTomorrow = { printLog("tomorrow") },
        onRepeat = { printLog("repeat") })
}

@Composable
@Preview(showBackground = true)
private fun PreviewAddGoalDialog() {
    val printLog = { str: String -> Log.d("PreviewAddGoalDialog", "$str button clicked") }
//    AddGoalDialog({}, {})
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
