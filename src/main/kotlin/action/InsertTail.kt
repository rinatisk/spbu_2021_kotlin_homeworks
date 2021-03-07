package action

import commandstorage.CommandStorage

/**
 * Action subclass
 *
 * Add number to head of list or reverse it
 *
 * @param number is a number to add
 * @param commandStorage is a *commandStorage* to add number into
 */

class InsertTail(private val number: Int, override val commandStorage: CommandStorage) : Action {

    /**
     * add number *last* to number list
     */
    override fun doAction() {
        commandStorage.numberList.add(number)
    }

    /**
     * remove *last* number from number list
     */
    override fun reverseAction() {
        commandStorage.numberList.removeLast()
    }
}
