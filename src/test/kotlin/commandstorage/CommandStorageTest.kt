package commandstorage

import action.InsertTail
import homework2.getResource
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class CommandStorageTest {

    @Test
    fun writeSerialization() {
        val inputData = "[{\"type\":\"InsertTail\",\"number\":1},{\"type\":\"InsertTail\",\"number\":2}]"

        val commandstorage = CommandStorage()
        commandstorage.doAction(InsertTail(1))
        commandstorage.doAction(InsertTail(2))

        commandstorage.writeSerialization(getResource())
        assertEquals(inputData, File(getResource()).readText())
    }

    @Test
    fun readSerialization() {
        val inputData = listOf<Int>(1, 2)

        val resource = getResource()
        val expectedSerialization = "[{\"type\":\"InsertTail\",\"number\":1},{\"type\":\"InsertTail\",\"number\":2}]"
        File(resource).writeText(expectedSerialization)

        val commandstorage = CommandStorage()
        commandstorage.readSerialization(resource)

        assertEquals(inputData, commandstorage.numberList)
    }
}