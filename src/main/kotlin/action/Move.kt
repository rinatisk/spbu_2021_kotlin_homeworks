package action

import commandstorage.CommandStorage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.IndexOutOfBoundsException

/**
 * Move element in number list.
 * @param commandStorage storage which contains number list
 * @param fromIndex *index* of element we move
 * @param toIndex *index* to which we move element
 */
private fun moveElements(commandStorage: CommandStorage, fromIndex: Int, toIndex: Int) {
    val toAddNumberCopy = commandStorage.numberList[fromIndex]
    commandStorage.numberList.removeAt(fromIndex)
    commandStorage.numberList.add(toIndex, toAddNumberCopy)
}

/**
 * Check indexes for validity.
 * @param commandStorage storage which contains size of number list
 * @param fromIndex *index* of element we move
 * @param toIndex *index* to which we move element
 * @exception IndexOutOfBoundsException invalid indexes
 */

private fun checkIndexes(commandStorage: CommandStorage, fromIndex: Int, toIndex: Int) {
    if ((fromIndex !in (0..commandStorage.numberList.size)) ||
        toIndex !in (0..commandStorage.numberList.size)) {
        throw IndexOutOfBoundsException("Invalid index to move")
    }
}

/**
 * Action subclass, which move number in number list.
 * @param fromIndex *index* of element in number list we move
 * @param toIndex *index* to which we move element in number list
 * @param commandStorage is a *commandStorage* which contains number list
 */

@Serializable
@SerialName("Move")
class Move(private val fromIndex: Int, private val toIndex: Int) :
    Action {
    /**
     * Move element.
     */
    override fun doAction(commandStorage: CommandStorage) {
        checkIndexes(commandStorage, fromIndex, toIndex)
        moveElements(commandStorage, fromIndex, toIndex)
    }

    /**
     * Revert move element.
     */
    override fun reverseAction(commandStorage: CommandStorage) {
        checkIndexes(commandStorage, toIndex, fromIndex)
        moveElements(commandStorage, toIndex, fromIndex)
    }
}
