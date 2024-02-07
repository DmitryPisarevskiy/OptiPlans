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
        for (i in 0..<model.streams.size) {
            model.streams[i].materialBalance(model)
            println("${model.streams[i].name}:")
            for (j in 0..<3) {
                println("produced in $j period: ${model.streams[i].produced[j]}")
                println("purchased in $j period: ${model.streams[i].purchases[j]}")
                println("spent in $j period: ${model.streams[i].spent[j]}")
                println("sold in $j period: ${model.streams[i].sails[j]}")
            }
            println()
        }

        assertEquals(true, model.units[0].isEqualAll)
        assertEquals(true, model.units[1].isEqualAll)
        assertEquals(true, model.units[2].isEqualAll)
        assertEquals(true, model.streams[0].isEqualed)
        assertEquals(true, model.streams[1].isEqualed)
        assertEquals(true, model.streams[2].isEqualed)
        assertEquals(true, model.streams[3].isEqualed)
        assertEquals(true, model.streams[4].isEqualed)
        assertEquals(true, model.streams[5].isEqualed)
        assertEquals(true, model.streams[6].isEqualed)
        assertEquals(true, model.streams[7].isEqualed)
        assertEquals(true, model.streams[8].isEqualed)
    }
}