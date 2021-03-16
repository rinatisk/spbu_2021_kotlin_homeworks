package action

import commandstorage.CommandStorage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InsertTailTest {

    @Test
    fun doAction() {
        val inputData = listOf(1, 2)

        val commandStorage = CommandStorage()
        commandStorage.doAction(InsertTail(1))
        commandStorage.doAction(InsertTail(2))

        assertEquals(inputData, commandStorage.numberList)
    }

    @Test
    fun reverseAction() {
        val inputData = listOf(1, 2)

        val commandStorage = CommandStorage()
        commandStorage.doAction(InsertTail(1))
        commandStorage.doAction(InsertTail(2))
        commandStorage.doAction(InsertTail(3))
        commandStorage.revertLastAction()

        assertEquals(inputData, commandStorage.numberList)
    }
}