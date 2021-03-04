package firsthomework

import java.lang.IllegalArgumentException
import java.util.Scanner

fun getFactorialRecursive(number: Int): Int {
    return when (number) {
        0, 1 -> 1
        else -> number * getFactorialRecursive(number - 1)
    }
}

fun getFactorialIterative(number: Int): Int {
    return when (number) {
        0, 1 -> 1
        else -> (1..number).reduce { first, second -> first * second }
    }
}

fun getInputNumber(): Int {
    println("Please write down your number to get factorial")
    val scan = Scanner(System.`in`)

    if (!scan.hasNextInt()) {
        throw IllegalArgumentException("invalid input, you write non-number")
    }
    val numberToGetFactorial: Int = scan.nextInt()
    if (numberToGetFactorial < 0) {
        throw IllegalArgumentException("invalid input, you write non-natural number")
    }

    return numberToGetFactorial
}

fun main() {

    val number: Int = getInputNumber()

    println("recursive factorial $number! = ${getFactorialRecursive(number)}")
    println("iterative factorial $number! = ${getFactorialIterative(number)}")
}
