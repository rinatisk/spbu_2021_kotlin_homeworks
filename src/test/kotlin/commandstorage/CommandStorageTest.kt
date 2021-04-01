package commandstorage

import action.InsertTail
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

internal class CommandStorageTest {

    @Test
    fun writeSerialization(@TempDir tempDir: Path) {
        val resource = this.javaClass.getResource("writeSerializationTest.json").file
        val inputData = File(resource).readText()

        val commandStorage = CommandStorage()
        commandStorage.doAction(InsertTail(1))
        commandStorage.doAction(InsertTail(2))

        val tempResource = tempDir.resolve("temp.json")

        commandStorage.writeSerialization(tempResource.toString())
        assertEquals(inputData, File(tempResource.toString()).readText())
    }

    @Test
    fun readSerialization() {
        val inputData = listOf(1, 2)

        val commandStorage = CommandStorage()
        val resource = this.javaClass.getResource("readSerializationTest.json").file
        commandStorage.readSerialization(resource.toString())

        assertEquals(inputData, commandStorage.numberList)
    }
}
