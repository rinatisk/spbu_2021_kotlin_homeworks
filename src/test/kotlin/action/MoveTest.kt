package action

import commandstorage.CommandStorage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MoveTest {

    companion object {
        @JvmStatic
        fun inputData(): List<Arguments> = listOf(
            Arguments.of(listOf(1, 4, 2, 3), Move(3, 1)),
            Arguments.of(listOf(1, 2, 3, 4), Move(0, 0)))
    }

    @MethodSource("inputData")
    @ParameterizedTest(name = "inputData {index}, {1}")
    fun paramDoAction(expected: List<Int>, inputAction: Action) {
        val commandStorage = CommandStorage()
        for( i in (1..4)) { commandStorage.doAction(InsertTail(i)) }

        commandStorage.doAction(inputAction)

        assertEquals(expected, commandStorage.numberList)
    }

    @Test
    fun invalidIndexesTest() {
        val commandStorage = CommandStorage()
        for (i in (1..4)) { commandStorage.doAction(InsertTail(i)) }

        assertThrows(IndexOutOfBoundsException::class.java) {
            commandStorage.doAction(Move(5, 1))
        }

    }

    @Test
    fun doAction() {
        val testData = listOf(1, 4, 2, 3)

        val commandStorage = CommandStorage()
        for( i in (1..4)) { commandStorage.doAction(InsertTail(i)) }
        commandStorage.doAction(Move(3, 1))

        assertEquals(testData, commandStorage.numberList)
    }

    @Test
    fun reverseAction() {
        val testData = listOf(1, 2, 3, 4)

        val commandStorage = CommandStorage()
        for( i in (1..4)) { commandStorage.doAction(InsertTail(i)) }
        commandStorage.doAction(Move(3, 1))
        commandStorage.revertLastAction()

        assertEquals(testData, commandStorage.numberList)
    }
}