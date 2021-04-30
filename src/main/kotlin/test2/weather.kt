package test2

import com.beust.klaxon.Json
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser.Companion.default
import java.io.File
import java.net.URL

val API = "3f29633eeef810afa69ded4ac143231a"

private fun getJson(city: String) = URL("https://api.openweathermap.org/data/2.5/weather?q=$city&lang=ru&units=metric&appid=$API").readText()

fun parseData(city: String) {
    val parser = default()
    val stringBuilder = StringBuilder(getJson(city))
    val json: JsonObject = parser.parse(stringBuilder) as JsonObject
    val body = json.obj("main")
}

object Util {
    fun getResource(name: String): String = this.javaClass.getResource(name).file
}

fun main() {

    val file = Util.getResource("cities.txt")
    val cities = File(file).readText().split(" ")

    cities.forEach() {
        val response = URL("https://api.openweathermap.org/data/2.5/weather?q=$it&lang=ru&units=metric&appid=$API").readText()
        println(body)
    }

    println(cities)
    println(json)
    println(body?.double("temp"))
    println(json.string("name"))
}