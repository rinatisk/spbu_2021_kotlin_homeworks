package action

import commandstorage.CommandStorage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InsertHeadTest {

    @Test
    fun doAction() {
        val inputData = listOf(4, 3, 2, 1)

        val commandStorage = CommandStorage()
        for( i in (1..4)) { commandStorage.doAction(InsertHead(i)) }

        assertEquals(inputData, commandStorage.numberList)
    }

    @Test
    fun reverseAction() {
        val inputData = listOf(3, 2, 1)

        val commandStorage = CommandStorage()
        for( i in (1..4)) { commandStorage.doAction(InsertHead(i)) }
        commandStorage.revertLastAction()

        assertEquals(inputData, commandStorage.numberList)
    }
}