package homework2

import java.io.File
import java.util.Scanner

fun main() {
    val list = getUserArray()
  //  list.removeRepeatedElements()
    println(list)
}

fun MutableList<Int>.removeRepeatedElements() {
    this.map { if (this.contains(it)) this.remove(element = it) }
}

fun getUserArray(): MutableList<Int> {
    val userList = mutableListOf<Int>()
    val scan = Scanner(File("src/main/kotlin/homework2/input.txt"))
  //  val input = File("input.txt").readBytes()
 //   userList[0] = scan.nextInt()
    while (scan.hasNextInt()) {
        userList.add(scan.nextInt())
    }
    return userList
}
