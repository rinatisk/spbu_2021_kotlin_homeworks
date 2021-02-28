package firsthomework

import java.lang.IllegalArgumentException
import java.util.Scanner

fun getFactorialRecursive(number: Int): Int {
    return when {
        number == 1 -> 1
        number > 1 -> number * getFactorialRecursive(number - 1)
        else -> throw IllegalArgumentException("invalid input, you write non-natural number")
    }
}

fun getFactorialIterative(number: Int): Int {
    if (number < 1) throw IllegalArgumentException("invalid input, you write non-natural number")
    return (1..number).reduce { first, second -> first * second }
}

fun getInputNumber(): Int {
    println("Please write down your number to get factorial")
    val scan = Scanner(System.`in`)

    if (!scan.hasNextInt()) {
        println("Invalid input: you write non-number")
        throw IllegalArgumentException("invalid input, you write non-number")
    }
    val numberToGetFactorial: Int = scan.nextInt()
    if (numberToGetFactorial < 1) {
        throw IllegalArgumentException("invalid input, you write non-natural number")
    }

    return numberToGetFactorial
}

fun main() {

    val numberToGetFactorial: Int = getInputNumber()

    if (numberToGetFactorial > 0) print("recursive factorial $numberToGetFactorial! = ${
        getFactorialRecursive(numberToGetFactorial)
    }\n" +
            "iterative factorial $numberToGetFactorial! = ${getFactorialIterative(numberToGetFactorial)}")
}
