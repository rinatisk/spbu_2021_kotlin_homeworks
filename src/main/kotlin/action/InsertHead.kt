package action

import commandstorage.CommandStorage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Action subclass, which add number to head of list or reverse it.

 * @param number is a number to add
*/

@Serializable
@SerialName("InsertHead")
class InsertHead(private val number: Int) : Action {

    /**
     * Add number to *first* position in number list.
     */
    override fun doAction(commandStorage: CommandStorage) {
        commandStorage.numberList.add(0, number)
    }

    /**
     * Remove *first* number from number list.
     */
    override fun reverseAction(commandStorage: CommandStorage) {
        commandStorage.numberList.removeFirst()
    }
}
