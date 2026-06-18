package com.ucbestudoalln.trabalho01_mobile

import com.ucbestudoalln.trabalho01_mobile.Model.WeatherHistory
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherLogicTest {
    
    @Test
    fun testWeatherHistoryCreation() {
        val history = WeatherHistory(
            cep = "12345678",
            city = "Brasilia",
            temperature = 25.5
        )
        
        assertEquals("12345678", history.cep)
        assertEquals("Brasilia", history.city)
        assertEquals(25.5, history.temperature, 0.1)
    }

    @Test
    fun testTimestampIsSet() {
        val before = System.currentTimeMillis()
        Thread.sleep(10)
        val history = WeatherHistory(cep = "", city = "", temperature = 0.0)
        val after = System.currentTimeMillis()
        
        assert(history.timestamp in before..after)
    }
}
