package com.github.sseung416.mydailynote

// TODO: 음 다른 타입(파라미터, return)의 리스너를 받았을 때는?
fun concatListener(vararg listeners: () -> Unit) : () -> Unit = {
    for (listener in listeners) {
        listener.invoke()
    }
}