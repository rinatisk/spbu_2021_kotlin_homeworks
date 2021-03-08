package homework2

import java.io.File
import java.util.Scanner

fun main() {
    val list = getUserList()
    if (list.isEmpty()) println("write numbers to input.txt")
    println(list.reversed().distinct().reversed())
}

fun getUserList(): MutableList<Int> {
    val userList = arrayListOf<Int>()
    val scan = Scanner(File("src/main/kotlin/homework2/input.txt"))
    while (scan.hasNextInt()) {
        userList.add(scan.nextInt())
    }
    return userList
}
