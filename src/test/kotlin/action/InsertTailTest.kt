package action

import commandstorage.CommandStorage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InsertTailTest {

    @Test
    fun doAction() {
        val inputData = listOf(1, 2, 3, 4)

        val commandStorage = CommandStorage()
        (1..4).map { commandStorage.doAction(InsertTail(it)) }

        assertEquals(inputData, commandStorage.numberList)
    }

    @Test
    fun reverseAction() {
        val inputData = listOf(1, 2, 3)

        val commandStorage = CommandStorage()
        (1..4).map { commandStorage.doAction(InsertTail(it)) }
        commandStorage.revertLastAction()

        assertEquals(inputData, commandStorage.numberList)
    }
}