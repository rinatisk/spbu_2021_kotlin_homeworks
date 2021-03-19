package commandstorage

import action.InsertTail
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi

object Util {
    fun getResource(name: String) = this.javaClass.getResource(name).file
}


internal class CommandStorageTest {

    @ExperimentalPathApi
    @Test
    fun writeSerialization(@TempDir tempDir: Path) {
        val inputData = "[{\"type\":\"InsertTail\",\"number\":1},{\"type\":\"InsertTail\",\"number\":2}]"

        val commandstorage = CommandStorage()
        commandstorage.doAction(InsertTail(1))
        commandstorage.doAction(InsertTail(2))

        val tempResource: Path = tempDir.resolve("temp.json")

        commandstorage.writeSerialization(tempResource.toString())
        assertEquals(inputData, File(tempResource.toString()).readText())
    }

    @Test
    fun readSerialization() {
        val inputData = listOf(1, 2)

        val commandstorage = CommandStorage()
        val resource = Util.getResource("readSerializationTest.json")
        commandstorage.readSerialization(resource.toString())

        assertEquals(inputData, commandstorage.numberList)
    }
}
