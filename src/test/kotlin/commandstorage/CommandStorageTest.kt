package commandstorage

import action.InsertTail
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import org.junit.rules.TemporaryFolder

import org.junit.Rule
import org.junit.jupiter.api.io.TempDir
import java.lang.NullPointerException
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.pathString
import kotlin.io.path.readText


internal class CommandStorageTest {

    @ExperimentalPathApi
    @Test
    fun writeSerialization(@TempDir tempDir: Path) {
        val inputData = "[{\"type\":\"InsertTail\",\"number\":1},{\"type\":\"InsertTail\",\"number\":2}]"

        val commandstorage = CommandStorage()
        commandstorage.doAction(InsertTail(1))
        commandstorage.doAction(InsertTail(2))

        val numbers: Path = tempDir.resolve("temp.json")

        commandstorage.writeSerialization(numbers.toString())
        assertEquals(inputData, File(numbers.toString()).readText())
    }
/*
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
 */
}