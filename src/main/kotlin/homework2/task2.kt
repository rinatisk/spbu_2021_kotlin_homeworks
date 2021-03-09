package homework2

import java.io.File
import java.util.Scanner

fun main() {
    val list = getUserList()
    if (list.isEmpty()) println("write numbers to input.txt")
    println(list.removeDuplicates())
}

fun ArrayList<Int>.removeDuplicates() = this.reversed().distinct().reversed()

fun getUserList(): ArrayList<Int> {
    val userList = arrayListOf<Int>()
    val resource = object {}.javaClass.getResource("input.txt").file
    val scan = Scanner(File(resource))
    while (scan.hasNextInt()) {
        userList.add(scan.nextInt())
    }
    return userList
}
