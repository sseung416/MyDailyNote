package com.github.sseung416.mydailynote.base.property

import androidx.compose.runtime.Stable

@Stable
interface TextCustom {
    infix fun then(other: TextCustom): TextCustom = this
}
// 너무 어렵네...