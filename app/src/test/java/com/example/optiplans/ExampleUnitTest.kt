package com.example.optiplans

import com.example.optiplans.entities.Column
import com.example.optiplans.entities.Stream
import com.example.optiplans.entities.Unit
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
        var u:Unit = Unit("Unit", "SFCC", 3)
        var s1: Stream = Stream("Этан", "ETN", 3)
        var s2: Stream = Stream("ШФЛУ", "SLU", 3)
        var s3: Stream = Stream("Нафта", "NAF", 3)
        var s4: Stream = Stream("Этилен", "ETY", 3)
        var s5: Stream = Stream("Пропилен", "PRP", 3)
        var s6: Stream = Stream("Пироконденсат", "PIR", 3)
        var c1:Column = Column("Первый режим", "AAA", 3)
        var c2:Column = Column("Второй режим", "BBB", 3)
        var c3:Column = Column("Третий режим", "ССС", 3)
        c1.strAndCoeffs.put(s1, 1f)
        c1.strAndCoeffs.put(s4, -0.8f)
        c1.strAndCoeffs.put(s5, -0.1f)
        c1.strAndCoeffs.put(s6, -0.1f)
        c2.strAndCoeffs.put(s2, 1f)
        c2.strAndCoeffs.put(s4, -0.7f)
        c2.strAndCoeffs.put(s5, -0.15f)
        c2.strAndCoeffs.put(s6, -0.15f)
        c3.strAndCoeffs.put(s3, 1f)
        c3.strAndCoeffs.put(s4, -0.6f)
        c3.strAndCoeffs.put(s5, -0.2f)
        c3.strAndCoeffs.put(s6, -0.2f)
        c1.activities = arrayOf(1f, 0f, 0f)
        c2.activities = arrayOf(1f, 1f, 0f)
        c3.activities = arrayOf(1f, 0f, 1f)
        u.regimes.add(c1)
        u.regimes.add(c2)
        u.regimes.add(c3)
        u.streamBalance()
        //assertEquals(u.feedSum[0],3f)
        //assertArrayEquals(u.products[s4],arrayOf(-2.1f,-0.7f,-0.6f))
        //assertArrayEquals(u.products[s5],arrayOf(-0.45f,-0.15f,-0.2f))
        //assertArrayEquals(u.products[s6],arrayOf(-0.45f,-0.15f,-0.2f))
        assertEquals(true, u.isEqualAll)
    }
}