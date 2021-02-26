package first_homework

import java.util.Scanner

fun getFactorialRecursive(number: Int): Int {
    return when {
        number == 1 -> 1
        number > 1 -> number * getFactorialRecursive(number - 1)
        else -> -1
    }
}

fun getFactorialIterative(number: Int): Int {
    if (number < 1) return -1
    return (1..number).reduce { first, second -> first * second }
}

fun getInputNumber(): Int {
    println("Please write down your number to get factorial")
    val scan = Scanner(System.`in`)

    if (!scan.hasNextInt()) {
        println("Invalid input: you write non-number")
        return -1
    }
    val numberToGetFactorial: Int = scan.nextInt()
    if (numberToGetFactorial < 1) {
        println("Invalid input: you write non-natural number")
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
