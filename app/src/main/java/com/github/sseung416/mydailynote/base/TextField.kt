package com.github.sseung416.mydailynote.base

import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.github.sseung416.mydailynote.ui.theme.Grey2

// https://stackoverflow.com/questions/56789583/how-to-use-edittext-or-textinput-widget-in-jetpack-compose
// https://stackoverflow.com/questions/65780722/jetpack-compose-how-to-remove-edittext-textfield-underline-and-keep-cursor
// todo custom 정리 (hint와 label의 정리) ㅇㄴ 이친구도 커스텀 화나는건...

@Composable
@Preview(showBackground = true)
fun AppTextField(placeholderText: String = "sdfsdfsadf") {
    var text by rememberSaveable { mutableStateOf("") }

    val onValueChangeListener = { it: String -> text = it }
    val textStyle = TextStyle.Default.copy(
        fontSize = 16.sp,
        fontFamily = imHyemin,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    val colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent,
        cursorColor = Color.Black,
        focusedIndicatorColor = Color.Black,
        unfocusedIndicatorColor = Grey2
    )

    TextField(
        value = text,
        placeholder = { Placeholder(text = placeholderText) },
        onValueChange = onValueChangeListener,
        textStyle = textStyle,
        colors = colors
    )
}

@Composable
private fun Placeholder(text: String) {
    BoldText(text = text, color = Grey2, fontSize = 16.sp)
}