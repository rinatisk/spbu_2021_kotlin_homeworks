package action

import commandstorage.CommandStorage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InsertHeadTest {

    @Test
    fun doAction() {
        val inputData = listOf(1, 2)

        val commandStorage = CommandStorage()
        commandStorage.doAction(InsertHead(2))
        commandStorage.doAction(InsertHead(1))

        assertEquals(inputData, commandStorage.numberList)
    }

    @Test
    fun reverseAction() {
        val inputData = listOf(2)

        val commandStorage = CommandStorage()
        commandStorage.doAction(InsertHead(2))
        commandStorage.doAction(InsertHead(1))
        commandStorage.revertLastAction()

        assertEquals(inputData, commandStorage.numberList)
    }
}