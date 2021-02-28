package firsthomework

import java.util.Scanner

fun main() {

    val scan = Scanner(System.`in`)
    println("Please write string and substring throw a line:")

    val mainString = scan.nextLine()

    val substring = scan.nextLine()

    val answer: Int = try {
        mainString.windowed(substring.length) {
        if (it.equals(substring)) 1 else 0 }.sum()
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("you didn't write string")
    }

    print("substring include in string $answer times")
}
