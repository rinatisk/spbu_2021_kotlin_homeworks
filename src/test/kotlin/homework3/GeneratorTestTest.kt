package homework3

import com.charleskorn.kaml.EmptyYamlDocumentException
import com.charleskorn.kaml.MissingRequiredPropertyException
import com.charleskorn.kaml.Yaml

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File


internal class GeneratorTestTest {
    companion object {
        @JvmStatic
        fun inputDataNonEmptyClass() = listOf<Arguments>(
            Arguments.of("bigClassExpected.kt", "bigClass.yaml"),
            Arguments.of("smallClassExpected.kt", "smallClass.yaml"),
        )
        @JvmStatic
        fun inputDataInvalidClass() = listOf<Arguments>(
            Arguments.of("noFunc.yaml"),
            Arguments.of("noNameClass.yaml"),
            Arguments.of("noNamePackage.yaml")
        )
    }

    @MethodSource("inputDataNonEmptyClass")
    @ParameterizedTest(name = "inputDataNonEmptyClass {index}, {1}")
    fun generateNonEmptyTestFile(expected: String, inputConfig: String) {

        val expectedClass = File(this.javaClass.getResource(expected).file)

        val configText = File(this.javaClass.getResource(inputConfig).file).readText()
        val classFromConfig = Yaml.default.decodeFromString(Class.serializer(), configText)
        val actualClass = GeneratorTest.generateTestFile(classFromConfig).toString()

        assertEquals(expectedClass.readText(), actualClass)

    }

    @MethodSource("inputDataInvalidClass")
    @ParameterizedTest(name = "inputDataInvalidClass {index}, {1}")
    fun generateInvalidTestFile(inputConfig: String) {
        val configText = File(this.javaClass.getResource(inputConfig).file).readText()

        assertThrows(MissingRequiredPropertyException::class.java) {
            val classFromConfig = Yaml.default.decodeFromString(Class.serializer(), configText)
            GeneratorTest.generateTestFile(classFromConfig).toString()
        }
    }

    @Test
    fun generateEmptyTestFile() {
        val configText = File(this.javaClass.getResource("emptyClass.yaml").file).readText()

        assertThrows(EmptyYamlDocumentException::class.java) {
            val classFromConfig = Yaml.default.decodeFromString(Class.serializer(), configText)
            GeneratorTest.generateTestFile(classFromConfig).toString()
        }
    }

}