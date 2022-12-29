package com.github.sseung416.mydailynote

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.sseung416.mydailynote.base.*
import java.text.SimpleDateFormat
import java.util.Calendar

@Composable
@Preview(showBackground = true)
fun Home() {
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
            itemsIndexed(
                listOf(
                    "안녕하세요 테스트 문구입니당~",
                    "안녕하세요 테스트 문구입니당~",
                    "안녕하세요 테스트 문구입니당~",
                    "안녕하세요 테스트 문구입니당~",
                    "안녕하세요 테스트 문구입니당~",
                    "안녕하세요 테스트 문구입니당~",
                    "안녕하세요 테스트 문구입니당~",
                )
            ) { _, item ->
                TodoCheckBox(item)
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "menu"
        )
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
    checked: Boolean = false,
    onClick: () -> Unit
) {
    val checkedState by remember { mutableStateOf(checked) }

    val color = if (checkedState) {
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
                contentDescription = "circle...",
                colorFilter = ColorFilter.tint(color)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BoldText(text = "12", color = color, fontSize = 11.sp)
                BoldText(text = "금", color = color, fontSize = 9.sp)
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // 일기가 쓰여져 있는지 체크
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color)
                .size(5.dp)
        )
    }
}

@Composable
private fun Goal(
    text: String,
    color: Color
) {
    Row(modifier = Modifier.background(color)) {
        Text(text = text)
        RegularText(text = text)
        IconButton(onClick = { /*TODO*/ }) {
            Image(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add todo",
                colorFilter = ColorFilter.tint(color)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TodoCheckBox(text: String = "안녕하세요 테스트 문구입니다.") {
    var checked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(0.dp, 8.dp)
    ) {
        // checkBox drawable
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.clickable { checked = checked.not() }
        ) {
            val imageRes =
                if (checked) R.drawable.ic_checkbox_true else R.drawable.ic_checkbox_false
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "check box drawable"
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        RegularText(text = text)
    }
}
