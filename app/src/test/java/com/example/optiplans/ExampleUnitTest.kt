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
        var a: Array<Float> = Array<Float> (4) {1f}
        for (i in 0..<a.size) {
            a[i] = a[i] *2
        }
        assertEquals(a[2],2f)
    }
}