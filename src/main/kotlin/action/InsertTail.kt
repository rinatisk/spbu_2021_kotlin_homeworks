package action

import commandstorage.CommandStorage

/**
 * Action subclass, which add number to tail of list or reverse it.
 * @param number is a number to add
 */

class InsertTail(private val number: Int) : Action {

    /**
     * Add number to *last* position in number list.
     */
    override fun doAction(commandStorage: CommandStorage) {
        commandStorage.numberList.add(number)
    }

    /**
     * Remove *last* number from number list.
     */
    override fun reverseAction(commandStorage: CommandStorage) {
        commandStorage.numberList.removeLast()
    }
}
