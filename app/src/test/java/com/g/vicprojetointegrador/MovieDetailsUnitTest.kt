package com.g.vicprojetointegrador

import com.g.vicprojetointegrador.utils.formatHourMinutes
import com.g.vicprojetointegrador.utils.formatPercentage
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDetailsUnitTest {

    @Test
    fun formatting_minutes_to_Hours_minutes() {
        assertEquals(
            "0h 00min",
            0.formatHourMinutes()
        )

        assertEquals(
            "1h 30min",
            90.formatHourMinutes()
        )

        assertEquals(
            "0h 15min",
            15.formatHourMinutes()
        )

        assertEquals(
            "10h 10min",
            610.formatHourMinutes()
        )
    }

    @Test
    fun formatting_Percentage() {
        assertEquals(
            "0%",
            0.0.formatPercentage()
        )

        assertEquals(
            "50%",
            5.0.formatPercentage()
        )

        assertEquals(
            "100%",
            10.0.formatPercentage()
        )

        assertEquals(
            "79%",
            7.9.formatPercentage()
        )
    }

}

