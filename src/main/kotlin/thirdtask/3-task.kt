package thirdtask

import action.InsertHead
import action.InsertTail
import action.Move
import commandstorage.CommandStorage

fun main() {
    var commandStorage = CommandStorage()

    val firstTestRange = (1..10)
    val secondTestRange = (11..20)

    firstTestRange.map { InsertTail(it, commandStorage).doAction() }
    println(commandStorage.getNumberList())
    secondTestRange.map { InsertHead(it, commandStorage).doAction() }
    println(commandStorage.getNumberList())
    Move(3, 5, commandStorage).doAction()
}
