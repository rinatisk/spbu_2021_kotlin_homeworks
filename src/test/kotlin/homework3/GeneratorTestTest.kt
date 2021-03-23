package homework3

import com.charleskorn.kaml.Yaml
import commandstorage.CommandStorage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.nio.file.Path
import kotlin.io.path.readText


internal class GeneratorTestTest {
    companion object {
        @JvmStatic
        fun inputDataNonEmptyClass() = listOf<Arguments>(
            Arguments.of("bigClassExpected.kt", "bigClass.yaml"),
            Arguments.of("smallClassExpected.kt", "smallClass.yaml"),
        )
        @JvmStatic
        fun inputDataEmptyClass() = listOf<Arguments>(
            Arguments.of("noFuncExpected.kt"),
            Arguments.of("noNameClassExpected.kt"),
            Arguments.of("noNamePackageExpected.kt")
        )
    }

    @MethodSource("inputDataNonEmptyClass")
    @ParameterizedTest(name = "inputDataNonEmptyClass {index}, {1}")
    fun generateNonEmptyTestFile(expected: String, inputConfig: String) {

        val expectedClass = File(Util.getResource(expected))
        val configText = File(Util.getResource(inputConfig)).readText()
        val config = Yaml.default.decodeFromString(Class.serializer(), configText)
        val actualClass = GeneratorTest.generateTestFile(config).toString()


        assertEquals(expectedClass.readText(), actualClass)

    }

    @MethodSource("inputDataEmptyClass")
    @ParameterizedTest(name = "inputDataEmptyClass {index}, {1}")
    fun generateEmptyTestFile(expected: String, inputConfig: String) {
        val configText = File(Util.getResource(inputConfig)).readText()
        val config = Yaml.default.decodeFromString(Class.serializer(), configText)
        val actualClass = GeneratorTest.generateTestFile(config).toString()

        assertThrows(GeneratorTest.generateTestFile(config), IllegalAccessError)
    }

}