package com.github.sseung416.mydailynote.base

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.sseung416.mydailynote.R

val imHyemin = FontFamily(
    Font(R.font.imhyemin_bold, FontWeight.Bold),
    Font(R.font.imhyemin_regular, FontWeight.Normal)
)

// todo 데이터 클래스 빌더패턴으로... ㄲㄱ

@Composable
fun TitleText(
    text: String,
    color: Color = Color.Black
) {
    DefaultText(text, color, FontWeight.Bold, 24.sp)
}

@Composable
fun BoldText(
    text: String,
    color: Color = Color.Black,
    fontSize: TextUnit = 14.sp,
    modifier: Modifier = Modifier
) {
    DefaultText(text, color, FontWeight.Bold, fontSize)
}

@Composable
fun RegularText(
    text: String,
    color: Color = Color.Black
) {
    DefaultText(text, color)
}

@Composable
fun SmallText(
    text: String,
    color: Color = Color.Black
) {
    DefaultText(text = text, color = color, fontSize = 12.sp)
}

@Composable
private fun DefaultText(
    text: String,
    color: Color,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 14.sp
) {
    Text(
        text = text,
        fontFamily = imHyemin,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
    )
}

val a = Modifier.size(1.dp).padding(2.dp)

/*

    <style name="RegularBoldTextView.White" parent="Widget.AppCompat.TextView">
        <item name="android:textSize">16sp</item>
        <item name="android:fontFamily">@font/imhyemin_bold</item>
        <item name="android:textColor">@color/white</item>
    </style>

    <style name="RegularBoldTextView.Black" parent="Widget.AppCompat.TextView">
        <item name="android:textSize">16sp</item>
        <item name="android:fontFamily">@font/imhyemin_bold</item>
        <item name="android:textColor">@color/black</item>
    </style>

    <style name="RegularTextView" parent="Widget.AppCompat.TextView">
        <item name="android:textSize">16sp</item>
        <item name="android:fontFamily">@font/imhyemin_regular</item>
        <item name="android:textColor">@color/black</item>
    </style>

    <style name="SmallTextView" parent="Widget.AppCompat.TextView">
        <item name="android:textSize">13sp</item>
        <item name="android:fontFamily">@font/imhyemin_bold</item>
        <item name="android:textColor">@color/black</item>
    </style>
* */