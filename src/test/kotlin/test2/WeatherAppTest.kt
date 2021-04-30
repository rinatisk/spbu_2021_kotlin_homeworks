package test2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import test2.WeatherApp.parseData
import java.lang.StringBuilder

internal class WeatherAppTest {
    @Test
    fun parseDataTest() {
        val file1 = javaClass.getResource("positiveTemp.txt").readText()
        val file2 = javaClass.getResource("negativeTemp.txt").readText()

        assertEquals(parseData(StringBuilder(file1)).toString(), "В городе Лондон 9.38 градусов")
        assertEquals(parseData(StringBuilder(file2)).toString(), "В городе Лондон -9.38 градусов")

    }
}