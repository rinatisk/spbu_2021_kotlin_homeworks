package action

import commandstorage.CommandStorage

/**
 * Action subclass, which add number to tail of list or reverse it.
 * @param number is a number to add
 * @param commandStorage is a *commandStorage* to add number into
 */

class InsertTail(private val number: Int, override val commandStorage: CommandStorage) : Action {

    /**
     * Add number to *last* position in number list.
     */
    override fun doAction() {
        commandStorage.numberList.add(number)
    }

    /**
     * Remove *last* number from number list.
     */
    override fun reverseAction() {
        commandStorage.numberList.removeLast()
    }
}
