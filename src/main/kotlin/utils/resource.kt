package utils

import java.io.FileNotFoundException

class Util {
    fun getResource(name: String): String = this.javaClass.getResource(name).file ?: throw FileNotFoundException("No file")
}

