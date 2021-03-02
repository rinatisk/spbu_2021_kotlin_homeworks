package thirdtask

import action.InsertHead
import action.InsertTail
import action.Move
import commandstorage.CommandStorage

fun main() {
    val commandStorage = CommandStorage()
    println(commandStorage.numberList.size)
    val firstTestRange = (1..10)
    val secondTestRange = (11..20)

    firstTestRange.map { InsertTail(it, commandStorage).doAction() }
    println(commandStorage.numberList)

    secondTestRange.map { InsertHead(it, commandStorage).doAction() }
    println(commandStorage.numberList)

    Move(3, 5, commandStorage).doAction()
    println(commandStorage.numberList)

    commandStorage.revertLastAction()
    println(commandStorage.numberList)
}
