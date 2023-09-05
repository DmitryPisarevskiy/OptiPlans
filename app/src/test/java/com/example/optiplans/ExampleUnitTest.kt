package com.example.optiplans

import com.example.optiplans.entities.Column
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val c = Column("abcd", "efgh", null)
        println(c.nameOfColumn)
        assertEquals(4, 2 + 2)
    }
}