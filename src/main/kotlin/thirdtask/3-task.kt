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
    println(commandStorage.getSeria())

    firstTestRange.map { commandStorage.doAction(InsertTail(it)) }
   // println(format.encodeToString(commandStorage))

    println(commandStorage.getSeria())
    secondTestRange.map { commandStorage.doAction(InsertHead(it)) }
    println(commandStorage.numberList)

    commandStorage.doAction(Move(3, 5))
    println(commandStorage.numberList)

    commandStorage.revertLastAction()
    println(commandStorage.numberList)
}
