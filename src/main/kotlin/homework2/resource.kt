package homework2

import java.io.FileNotFoundException

object Util {
    fun getResource(name: String): String = this.javaClass.getResource(name).file ?: throw FileNotFoundException("No file")
}

