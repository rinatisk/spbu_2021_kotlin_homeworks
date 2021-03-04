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

    firstTestRange.map { commandStorage.doAction(InsertTail(it, commandStorage)) }
    println(commandStorage.numberList)

    secondTestRange.map { commandStorage.doAction(InsertHead(it, commandStorage)) }
    println(commandStorage.numberList)

    commandStorage.doAction(Move(3, 5, commandStorage))
    println(commandStorage.numberList)

    commandStorage.revertLastAction()
    println(commandStorage.numberList)
}
