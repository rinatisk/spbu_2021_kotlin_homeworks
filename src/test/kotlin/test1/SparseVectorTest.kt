package test1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalStateException

data class CustomInt(val value: Int) : ArithmeticAvailable<CustomInt> {
    override operator fun plus(i: CustomInt) = CustomInt(value + i.value)
    override operator fun minus(i: CustomInt) = CustomInt(value - i.value)
    override operator fun times(i: CustomInt) = CustomInt(value * i.value)
    override fun isZero() = value == 0
    override fun negative(): CustomInt = this * CustomInt(-1)
}

internal class SparseVectorTest {
    companion object {
        @JvmStatic
        fun testPlus() = listOf<Arguments>(
            Arguments.of(mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(1)), Pair(3, CustomInt(2))),
                mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(1)), Pair(3, CustomInt(2))),
                mutableListOf(Pair(1, CustomInt(value = 2)),
                    Pair(2, CustomInt(value = 2)),
                    Pair(3, CustomInt(value = 4))),
                5),
            Arguments.of(mutableListOf(Pair(1, CustomInt(-11)), Pair(2, CustomInt(-31)), Pair(3, CustomInt(2))),
                mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(0)), Pair(3, CustomInt(43))),
                mutableListOf(Pair(1, CustomInt(value = -10)),
                    Pair(2, CustomInt(value = -31)),
                    Pair(3, CustomInt(value = 45))),
                15))


        @JvmStatic
        fun testTimes() = listOf<Arguments>(Arguments.of(2,
            mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(1)), Pair(3, CustomInt(2))),
            mutableListOf(Pair(1, CustomInt(2)), Pair(2, CustomInt(2)), Pair(3, CustomInt(4))),
            5),
            Arguments.of(1,
                mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(1)), Pair(3, CustomInt(2))),
                mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(1)), Pair(3, CustomInt(2))),
                5))

        @JvmStatic
        fun testMinus() = listOf<Arguments>(
            Arguments.of(mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(1)), Pair(3, CustomInt(2))),
                mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(1)), Pair(3, CustomInt(2))),
                mutableListOf<Pair<Int, CustomInt>>(),
                5),
            Arguments.of(mutableListOf(Pair(1, CustomInt(-11)), Pair(2, CustomInt(-31)), Pair(3, CustomInt(2))),
                mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(0)), Pair(3, CustomInt(43))),
                mutableListOf(Pair(1, CustomInt(-12)), Pair(2, CustomInt(-31)), Pair(3, CustomInt(-41))),
                15))
    }

    @MethodSource("testPlus")
    @ParameterizedTest
    fun testPlus(
        firstList: MutableList<Pair<Int, CustomInt>>,
        secondList: MutableList<Pair<Int, CustomInt>>,
        expectedList: MutableList<Pair<Int, CustomInt>>,
        n: Int,
    ) {
        val actual = SparseVector(n, firstList) + SparseVector(n, secondList)

        assertEquals(actual.data, expectedList)
    }

    @MethodSource("testTimes")
    @ParameterizedTest
    fun testTimes(
        number: Int,
        firstList: MutableList<Pair<Int, CustomInt>>,
        expectedList: MutableList<Pair<Int, CustomInt>>,
        n: Int,
    ) {
        val actual = SparseVector(n, firstList) * CustomInt(number)

        assertEquals(actual.data, expectedList)
    }

    @MethodSource("testMinus")
    @ParameterizedTest
    fun testMinus(
        firstList: MutableList<Pair<Int, CustomInt>>,
        secondList: MutableList<Pair<Int, CustomInt>>,
        expectedList: MutableList<Pair<Int, CustomInt>>,
        n: Int,
    ) {
        val actual = SparseVector(n, firstList) - SparseVector(n, secondList)

        assertEquals(actual.data, expectedList)
    }
}

@Test
fun testInvalidLength() {
    val a =
        SparseVector(5, mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(1)), Pair(3, CustomInt(2))))
    val b =
        SparseVector(10, mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(1)), Pair(3, CustomInt(2))))
    assertThrows(IllegalStateException::class.java) {
        a + b
    }
}

@Test
fun testIsNonZero() {
    val a =
        SparseVector(5, mutableListOf(Pair(1, CustomInt(1)), Pair(2, CustomInt(1)), Pair(3, CustomInt(2))))
    assertTrue(a.isNonZero())
}
