package com.example.optiplans

import com.example.optiplans.entities.ModelExample
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun unit_balance_is_correct() {
        val model = ModelExample
        for (i in 0..<model.units.size) {
            model.units[i].streamBalance()
        }
        assertEquals(true, model.units[0].isEqualAll)
        assertEquals(true, model.units[1].isEqualAll)
        assertEquals(true, model.units[2].isEqualAll)
    }
}