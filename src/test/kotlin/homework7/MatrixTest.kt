package homework7

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalStateException

internal class MatrixTest {

    companion object {

        @JvmStatic
        fun validMatrices() = listOf<Arguments>(
            Arguments.of(
                Matrix(arrayOf(
                    intArrayOf(1, 1, 1),
                    intArrayOf(1, 1, 1),
                    intArrayOf(1, 1, 1))),

                Matrix(arrayOf(
                    intArrayOf(2, 1, 1),
                    intArrayOf(1, 1, 1),
                    intArrayOf(1, 2, 1))),

                Matrix(arrayOf(
                    intArrayOf(4, 4, 3),
                    intArrayOf(4, 4, 3),
                    intArrayOf(4, 4, 3)))),


            Arguments.of(
                Matrix(arrayOf(
                    intArrayOf(24, 202, 7, 25, 34, 34, 44),
                    intArrayOf(25, 62, 23, 60, 6, 6, 6),
                    intArrayOf(460, 640, 64, 264, 87, 7, 78),
                    intArrayOf(268, 2678, 276, 2752, 324, 245, 243),
                    intArrayOf(25, 575, 57, 857, 58, 968, 669),
                    intArrayOf(15, 53, 45, 78, 69, 698, 69),
                    intArrayOf(54, 78, 868, 69, 465, 45, 35))),

                Matrix(arrayOf(
                    intArrayOf(213, 232, 1324, 64, 753, 61, 57),
                    intArrayOf(14, 25, 324, 24, 57, 85, 235),
                    intArrayOf(14, 23, 56, 567, 76, 847, 4),
                    intArrayOf(324, 2, 584, 8579, 889, 56, 356),
                    intArrayOf(25, 245, 35, 67, 4, 7889, 52),
                    intArrayOf(4553, 56, 67, 6, 677, 33, 67),
                    intArrayOf(53, 67, 67, 857, 356, 67, 35))),

                Matrix(arrayOf(
                    intArrayOf(174122, 24011, 118632, 265018, 91161, 298259, 63352),
                    intArrayOf(53741, 10207, 90530, 536449, 83669, 77570, 38371),
                    intArrayOf(231552, 151653, 982900, 2418661, 655275, 843252, 278583),
                    intArrayOf(2126552, 250359, 2889164, 24078753, 3075623, 3212264, 1667190),
                    intArrayOf(4736052, 136441, 834789, 7982949, 1711537, 681000, 533157),
                    intArrayOf(3213215, 66612, 138908, 764853, 584464, 619901, 94027),
                    intArrayOf(265467, 153370, 207307, 1150855, 217202, 4421199, 77864)))),

            Arguments.of(
                Matrix(arrayOf(
                    intArrayOf(1, 2, 2),
                    intArrayOf(2, 1, 1),
                    intArrayOf(1, 1, 2))),

                Matrix(arrayOf(
                    intArrayOf(1, 0, 0),
                    intArrayOf(0, 1, 0),
                    intArrayOf(0, 0, 1))),

                Matrix(arrayOf(
                    intArrayOf(1, 2, 2),
                    intArrayOf(2, 1, 1),
                    intArrayOf(1, 1, 2)))),

            Arguments.of(
                Matrix(arrayOf(
                    intArrayOf(1, 2, 2),
                    intArrayOf(2, 1, 1),
                    intArrayOf(1, 1, 2))),

                Matrix(arrayOf(
                    intArrayOf(0, 0, 0),
                    intArrayOf(0, 0, 0),
                    intArrayOf(0, 0, 0))),

                Matrix(arrayOf(
                    intArrayOf(0, 0, 0),
                    intArrayOf(0, 0, 0),
                    intArrayOf(0, 0, 0)))),

            Arguments.of(
                Matrix(arrayOf(
                    intArrayOf(-24, -55, -76),
                    intArrayOf(-78, -45, -465),
                    intArrayOf(-45, -45, -45))),

                Matrix(arrayOf(
                    intArrayOf(75, 4, 24),
                    intArrayOf(42, 57, 3),
                    intArrayOf(54, 35, 77))),

                Matrix(arrayOf(
                    intArrayOf(-8214, -5891, -6593),
                    intArrayOf(-32850, -19152, -37812),
                    intArrayOf(-7695, -4320, -4680))))
        )

        @JvmStatic
        fun invalidMatrices() = listOf<Arguments>(
            Arguments.of(
                Matrix(emptyArray()),

                Matrix(arrayOf(
                    intArrayOf(213, 232, 1324, 64, 753, 61, 57),
                    intArrayOf(14, 25, 324, 24, 57, 85, 235),
                    intArrayOf(14, 23, 56, 567, 76, 847, 4),
                    intArrayOf(324, 2, 584, 8579, 889, 56, 356),
                    intArrayOf(25, 245, 35, 67, 4, 7889, 52),
                    intArrayOf(4553, 56, 67, 6, 677, 33, 67),
                    intArrayOf(53, 67, 67, 857, 356, 67, 35)))),

            Arguments.of(
                Matrix(arrayOf(
                    intArrayOf(1, 2, 2),
                    intArrayOf(2, 1, 1),
                    intArrayOf(1, 1, 2))),

                Matrix(arrayOf(
                    intArrayOf(1, 0, 0, 2),
                    intArrayOf(0, 1, 0, 2),
                    intArrayOf(0, 0, 1, 4),
                    intArrayOf(0, 0, 1, 1)))),
        )
    }

    @ParameterizedTest
    @MethodSource("validMatrices")
    fun testTimes(firstMatrix: Matrix, secondMatrix: Matrix, expected: Matrix) {

        val actual = firstMatrix * secondMatrix

        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("invalidMatrices")
    fun testInvalidMatrices(firstMatrix: Matrix, secondMatrix: Matrix) {

        assertThrows(IllegalStateException::class.java) {
            firstMatrix * secondMatrix
        }
    }

    @Test
    fun `test not square matrix`() {
        assertThrows(IllegalStateException::class.java) {
            Matrix(arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1)))
        }
    }

    @Test
    fun `test strange not square matrix`() {
        assertThrows(IllegalStateException::class.java) {
            Matrix(arrayOf(
                intArrayOf(1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1)))
        }
    }
}
