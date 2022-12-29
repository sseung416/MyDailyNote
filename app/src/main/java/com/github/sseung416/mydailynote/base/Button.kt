package com.github.sseung416.mydailynote.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 *
 * */
@Composable
fun ImageTextButton(
    onClick: () -> Unit,
    text: String,
    imageResourceId: Int,
    drawableDescription: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        ImageButton(
            onClick = onClick,
            imageResourceId = imageResourceId,
            drawableDescription = drawableDescription
        )

        Spacer(modifier = Modifier.height(4.dp))

        BoldText(text = text)
    }
}

/**
 * Image 를 사용하는 버튼
 * */
@Composable
fun ImageButton(
    onClick: () -> Unit,
    imageResourceId: Int,
    drawableDescription: String,
    imageTintColor: Color? = null,
    content: (@Composable () -> Unit)? = null
) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = drawableDescription,
            colorFilter = imageTintColor?.let { ColorFilter.tint(it) }
        )

        content?.invoke()
    }
}
