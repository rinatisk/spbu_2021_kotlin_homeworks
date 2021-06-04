package test2

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser.Companion.default
import test2.WeatherApp.getData
import test2.WeatherApp.parseData
import java.io.File
import java.net.URL

val API = File(".env").readText()

object Util {
    fun getResource(name: String): String = this.javaClass.getResource(name).file
}

object WeatherApp {

    private fun getJson(city: String) =
        URL("https://api.openweathermap.org/data/2.5/weather?q=$city&lang=ru&units=metric&appid=$API").readText()

    data class City(val name: String?, val temp: Double?) {
        override fun toString(): String = "В городе $name $temp градусов"
    }

    fun getData(city: String): StringBuilder {
        return StringBuilder(getJson(city))
    }

    fun parseData(stringBuilder: StringBuilder): City {
        val parser = default()
        val json: JsonObject = parser.parse(stringBuilder) as JsonObject
        val mainFieldBody = json.obj("main")
        return City(json.string("name"), mainFieldBody?.double("temp"))
    }
}

fun main() {

    val file = Util.getResource("cities.txt")
    val cities = File(file).readText().split(" ")

    cities.forEach {
        println(parseData(getData(it)))
    }
}
