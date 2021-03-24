package firsthomework

import action.InsertHead
import action.InsertTail
import action.Move
import commandstorage.CommandStorage

fun getResource(): String = object {}.javaClass.getResource("serialization.json").file

fun main() {
    val resource = getResource()

    val commandStorage = CommandStorage()
    println(commandStorage.numberList.size)
    val firstTestRange = (1..10)
    val secondTestRange = (11..20)

    commandStorage.readSerialization(resource)
    println(commandStorage.numberList)

    firstTestRange.forEach { commandStorage.doAction(InsertTail(it)) }
    println(commandStorage.numberList)

    secondTestRange.forEach { commandStorage.doAction(InsertHead(it)) }
    println(commandStorage.numberList)

    commandStorage.doAction(Move(3, 5))
    println(commandStorage.numberList)

    commandStorage.revertLastAction()
    println(commandStorage.numberList)

}
