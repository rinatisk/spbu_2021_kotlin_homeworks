package action

import commandstorage.CommandStorage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InsertTailTest {

    @Test
    fun doAction() {
        val inputData = listOf(1, 2, 3, 4)

        val commandStorage = CommandStorage()
        for( i in (1..4)) { commandStorage.doAction(InsertTail(i)) }

        assertEquals(inputData, commandStorage.numberList)
    }

    @Test
    fun reverseAction() {
        val inputData = listOf(1, 2, 3)

        val commandStorage = CommandStorage()
        for( i in (1..4)) { commandStorage.doAction(InsertTail(i)) }
        commandStorage.revertLastAction()

        assertEquals(inputData, commandStorage.numberList)
    }
}