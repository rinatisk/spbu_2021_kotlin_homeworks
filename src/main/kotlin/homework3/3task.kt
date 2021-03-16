package homework3

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlInput
import com.squareup.kotlinpoet.*
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
    val resource = object {}.javaClass.getResource("config.yaml").file
    val input = File(resource).readText().trimIndent()

    val result = Yaml.default.decodeFromString(Class.serializer(), input)

    println(result)

    val greeterClass = ClassName("", "Greeter")
    val createTaco = MemberName("com.squareup.tacos", "createTaco")
    val isVegan = MemberName("com.squareup.tacos", "isVegan")
    val file = FileSpec.builder(result.packageName, "HelloWorld")
        .addType(
            TypeSpec.classBuilder("Greeter")
            .primaryConstructor(FunSpec.constructorBuilder()
                .addParameter("name", String::class)
                .build())
            .addProperty(
                PropertySpec.builder("name", String::class)
                .initializer("name")
                .build())
            .addFunction(FunSpec.builder("greet")
                .addStatement("val taco = %M()", createTaco)
                .addStatement("println(taco.%M)", isVegan)
                .addStatement("println(%P)", "Hello, \$name")
                .build())
            .build())
        .addFunction(
            FunSpec.builder("main")
            .addParameter("args", String::class, KModifier.VARARG)
            .addStatement("%T(args[0]).greet()", greeterClass)
            .build())
        .build()

    file.writeTo(System.out)

}