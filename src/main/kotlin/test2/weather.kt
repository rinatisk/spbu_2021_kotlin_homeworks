package test2

import com.beust.klaxon.Json
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser
import com.beust.klaxon.Parser.Companion.default
import java.net.URL
import java.nio.charset.Charset




val API = "3f29633eeef810afa69ded4ac143231a"

val CITY = "London"

val response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&lang=ru&units=metric&appid=$API").readText()

data class City(
    @Json(name = "temp")
    val temp: String,

    @Json(name = "name")
    val name: String
)

val parser = default(  )
val stringBuilder = StringBuilder(response)
val json: JsonObject = parser.parse(stringBuilder) as JsonObject
val body = json.obj("main")


fun main() {
    println(response)
    println(json)
    println(body)
    println(json.string("name"))
}