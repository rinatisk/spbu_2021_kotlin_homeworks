package action

import commandstorage.CommandStorage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
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
    @ParameterizedTest(name = "test {index}, {1}")
    fun paramDoAction(expected: List<Int>, inputAction: Action) {
        val commandStorage = CommandStorage()
        (1..4).map { commandStorage.doAction(InsertTail(it)) }
        commandStorage.doAction(inputAction)
        assertEquals(expected, commandStorage.numberList)
    }

    @Test
    fun doAction() {
        val testData = listOf(1, 4, 2, 3)

        val commandStorage = CommandStorage()
        (1..4).map { commandStorage.doAction(InsertTail(it)) }
        commandStorage.doAction(Move(3, 1))

        assertEquals(testData, commandStorage.numberList)
    }

    @Test
    fun reverseAction() {
        val testData = listOf(1, 2, 3, 4)

        val commandStorage = CommandStorage()
        (1..4).map { commandStorage.doAction(InsertTail(it)) }
        commandStorage.doAction(Move(3, 1))
        commandStorage.revertLastAction()

        assertEquals(testData, commandStorage.numberList)
    }
}