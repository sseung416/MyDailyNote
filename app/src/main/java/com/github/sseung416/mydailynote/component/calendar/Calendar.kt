package com.github.sseung416.mydailynote.component.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.github.sseung416.mydailynote.R
import com.github.sseung416.mydailynote.component.base.BoldText
import com.github.sseung416.mydailynote.util.shortenName
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
@Preview(showBackground = true)
fun PreviewWeeklyCalendarContent() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    WeeklyCalendarContent(
        selectedDate = selectedDate,
        onDateChange = { selectedDate = it })
}

/**
 * 주별 투두 체크
 * */
@Composable
fun WeeklyCalendarContent(
    selectedDate: LocalDate,
    onDateChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val monday = selectedDate.with(DayOfWeek.MONDAY)
    val saturday = selectedDate.with(DayOfWeek.SATURDAY)
    // 저번주 일요일 ~ 이번주 토요일까지의 날짜
    val week = (monday.dayOfMonth - 1 ..saturday.dayOfMonth).toList()

    LazyRow(modifier = modifier) {
        items(week) { day ->
            val localDate = LocalDate.of(selectedDate.year, selectedDate.month, day)
            DayItem(
                date = localDate,
                onClick = { onDateChange.invoke(localDate) },
                checked = selectedDate == localDate, // 선택한 날짜와 리스트에 출력한 날짜가 같을 때
                isVisibleDay = true,
                hasTodo = false // todo 이건 나중에 로컬 데이터베이스 저장해서.. 비교 로직 추가
            )
        }
    }
}

@Composable
private fun DayItem(
    date: LocalDate,
    onClick: () -> Unit,
    checked: Boolean, // 선택되었는지 여부
    isVisibleDay: Boolean, // 요일 표시 여부
    hasTodo: Boolean, // 할일을 작성했는지 여부
) {
    val color = if (checked) Color.Black else Color.LightGray
    val size = 39.dp // todo size 동적 조정 가능하게 설정

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp, 0.dp)
            .clickable { onClick.invoke() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(size)
        ) {
            // background circle icon
            Image(
                painter = painterResource(R.drawable.ic_circle_border),
                contentDescription = "date circle",
                colorFilter = ColorFilter.tint(color)
            )

            // textContent
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BoldText(
                    text = date.dayOfMonth.toString(),
                    color = color,
                    fontSize = 11.sp
                )

                if (isVisibleDay)
                    BoldText(
                        text = date.dayOfWeek.shortenName(),
                        color = color,
                        fontSize = 7.5.sp
                    )
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
