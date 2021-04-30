package homework6

import homework6.Sorter.sort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class SorterTest {
    companion object {
        @JvmStatic
        fun testSort(): List<Arguments> = listOf(
            Arguments.of(
                mutableListOf(0, 0, 0), mutableListOf(0, 0, 0)
            ),
            Arguments.of(
                mutableListOf(1, 2, 3, 4), mutableListOf(1, 2 ,3 , 4)
            ),
            Arguments.of(
                mutableListOf(1, 2, 3, 4), mutableListOf(4, 3, 2, 1)
            ),
            Arguments.of(
                (1..100).toMutableList(), (100 downTo 1).toMutableList()
            ),
            Arguments.of(
                mutableListOf(0, 0, 1, 1, 2, 2), mutableListOf(1, 1, 2, 2, 0, 0)
            )
        )

        @JvmStatic
        fun testMT(): List<Arguments> = listOf(
            Arguments.of(
                (1..100).toMutableList(), (100 downTo 1).toMutableList(), 100
            ),
            Arguments.of(
                (1..100).toMutableList(), (100 downTo 1).toMutableList(), 15
            ),
            Arguments.of(
                (1..100).toMutableList(), (100 downTo 1).toMutableList(), 1
            ),
            Arguments.of(
                (1..100).toMutableList(), (100 downTo 1).toMutableList(), 10
            ),
            Arguments.of(
                (1..100).toMutableList(), (100 downTo 1).toMutableList(), 2
            )
        )
    }

    @MethodSource("testSort")
    @ParameterizedTest(name = "sort{index}, {1}")
    fun sort(expected: MutableList<Int>, actual: MutableList<Int>) {
        actual.sort(10)

        assertEquals(expected, actual)
    }

    @MethodSource("testMT")
    @ParameterizedTest(name = "Mt{index}, {1}")
    fun multiThread(expected: MutableList<Int>, actual: MutableList<Int>, numberOfThreads: Int) {
        actual.sort(numberOfThreads)

        assertEquals(expected, actual)
    }
}