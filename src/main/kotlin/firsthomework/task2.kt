package firsthomework

import java.util.Scanner

fun main() {

    val scan = Scanner(System.`in`)
    println("Please write string and substring throw a line:")

    val mainString = try {
        scan.nextLine()
    } catch (e: IllegalArgumentException) {
        error("you didn't write main string")
    }

    val substring = try {
        scan.nextLine()
    } catch (e: IllegalArgumentException) {
        error("you didn't write substring")
    }

    val answer: Int = try {
        mainString.windowed(substring.length) {
        if (it.equals(substring)) 1 else 0 }.sum()
    } catch (e: IllegalArgumentException) { error("you didn't write main string") }

    print("substring include in string $answer times")
}
