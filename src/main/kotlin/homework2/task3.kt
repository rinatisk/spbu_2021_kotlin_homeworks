@file:Suppress("MatchingDeclarationName")
package homework2

import action.InsertHead
import action.InsertTail
import action.Move
import commandstorage.CommandStorage
import java.io.File

object Util {
    fun getResource(name: String) = this.javaClass.getResource(name).file
}

fun main() {
   // val resource = getResource()
    val resource = Util.getResource("serialization.json")
    println(File(resource))
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
