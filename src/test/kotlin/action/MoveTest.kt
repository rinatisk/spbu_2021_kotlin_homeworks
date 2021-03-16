package action

import commandstorage.CommandStorage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MoveTest {

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