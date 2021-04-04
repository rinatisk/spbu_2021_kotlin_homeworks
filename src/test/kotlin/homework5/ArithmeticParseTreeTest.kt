package homework5

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ArithmeticParseTreeTest {

    companion object {
        @JvmStatic
        fun inputData() = listOf<Arguments>(
            Arguments.of(30, "bigExpression.txt"),
            Arguments.of(4, "smallExpression.txt"),
            Arguments.of(-37, "negativeExpression.txt")
        )
    }

    @MethodSource("inputData")
    @ParameterizedTest(name = "inputData {index}, {1}")
    fun getValue(expected: Int, expression: String) {
        val resource = this.javaClass.getResource(expression).file

        val tree = ArithmeticParseTree(resource)
        val value = tree.value

        assertEquals(expected, value)
    }

    @Test
    fun getString() {
        val resource = this.javaClass.getResource("testPrint.txt").file
        val expected = this.javaClass.getResource("expectedTestPrint.txt").readText()

        val tree = ArithmeticParseTree(resource)
        val value = tree.toString()

        assertEquals(expected, value)
    }
}