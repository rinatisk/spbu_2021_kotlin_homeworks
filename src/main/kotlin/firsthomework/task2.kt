package firsthomework

import java.util.Scanner

fun String.getOccurrencesCount(substring: String): Int {
    return this.windowed(substring.length) {
        if (it == substring) 1 else 0 }.sum()
}

fun main() {

    val scan = Scanner(System.`in`)
    println("Please write string and substring throw a line:")

    val mainString = scan.nextLine()

    val substring = scan.nextLine()

    val answer: Int = try {
        mainString.getOccurrencesCount(substring)
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("you didn't write string")
    }

    print("substring include in string $answer times")
}
