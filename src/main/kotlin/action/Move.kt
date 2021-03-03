package action

import commandstorage.CommandStorage
import kotlin.IndexOutOfBoundsException

private fun moveElements(commandStorage: CommandStorage, fromIndex: Int, toIndex: Int) {
    val toAddNumberCopy = commandStorage.numberList[fromIndex]
    commandStorage.numberList.removeAt(fromIndex)
    commandStorage.numberList.add(toIndex, toAddNumberCopy)
}

private fun checkIndexes(commandStorage: CommandStorage, fromIndex: Int, toIndex: Int) {
    if ((fromIndex !in (0..commandStorage.numberList.size)) ||
        toIndex !in (0..commandStorage.numberList.size)) {
        throw IndexOutOfBoundsException("Invalid index to move")
    }
}

class Move(private val fromIndex: Int, private val toIndex: Int, override val commandStorage: CommandStorage) :
    Action {
    override fun doAction() {
        checkIndexes(commandStorage, fromIndex, toIndex)
        moveElements(commandStorage, fromIndex, toIndex)
    }

    override fun reverseAction() {
        checkIndexes(commandStorage, toIndex, fromIndex)
        moveElements(commandStorage, toIndex, fromIndex)
    }
}
