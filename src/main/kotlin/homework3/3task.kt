package homework3

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlInput
import commandstorage.CommandStorage
import kotlinx.serialization.Serializable
import java.io.File
import java.io.Serial

@Serializable
data class Class(
    val packageName: String,
    val className: String,
    val functions: List<String>

)
fun main() {
    val input = File("src/main/resources/homework3/config.yaml").readText().trimIndent()

    val result = Yaml.default.decodeFromString(Class.serializer(), input)

    println(result)
}