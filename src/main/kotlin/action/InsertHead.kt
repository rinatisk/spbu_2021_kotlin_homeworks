package action

import commandstorage.CommandStorage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Action subclass, which add number to head of list or reverse it.

 * @param number is a number to add
 * @param commandStorage is a *commandStorage* to add number into
*/

class InsertHead(private val number: Int, override val commandStorage: CommandStorage) : Action {

    /**
     * Add number to *first* position in number list.
     */
    override fun doAction() {
        commandStorage.numberList.add(0, number)
    }

    /**
     * Remove *first* number from number list.
     */
    override fun reverseAction() {
        commandStorage.numberList.removeFirst()
    }
}
