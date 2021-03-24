package homework3

import com.charleskorn.kaml.Yaml
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.File

object Util {
    fun getResource(name: String): String = this.javaClass.getResource(name).file
}

/**
 * Data class, which contains information from yaml file.
 * @param packageName name of package for test class
 * @param className name of test class
 * @param functions list of functions to test in test class
 */
@Serializable
@SerialName("Class")
data class Class(
    val packageName: String,
    val className: String,
    val functions: List<String>
)
/**
 * Object which generates test class from yaml config.
 */
object GeneratorTest {

    private fun generateTestFunction(functionName: String) =
        FunSpec.builder(functionName)
            .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
            .build()

    private fun generateTestClass(functionList: List<String>, className: String) =
        TypeSpec.classBuilder("${className}Test")
            .addModifiers(KModifier.INTERNAL)
            .addFunctions(functionList.map { generateTestFunction(it) })
            .build()

    fun generateTestFile(config: Class) =
        FileSpec.builder(config.packageName, "${config.className}Test")
            .addType(generateTestClass(config.functions, config.className))
            .build()
}

fun main() {
    println("Write path to file in which you want to print test class:")
    val resultPath = readLine()

    val configName = Util.getResource("config.yaml")
    val configText = File(configName).readText().trimIndent()

    val config = Yaml.default.decodeFromString(Class.serializer(), configText)
    GeneratorTest.generateTestFile(config).writeTo(File(resultPath))
}
