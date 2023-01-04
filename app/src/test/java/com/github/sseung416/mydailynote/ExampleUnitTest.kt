package com.github.sseung416.mydailynote

import org.junit.Test

import org.junit.Assert.*
import java.util.Calendar

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun calendarTest() {
        val c = Calendar.getInstance()
        c.set(2004,4,16)
        println("값은 ${c.time}")
    }
}