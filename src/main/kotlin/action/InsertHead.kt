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

class InsertHead(private val number: Int, override val commandStorage: CommandStorage) : Action {

    /**
     * add number to *first* position in number list
     */
    override fun doAction() {
        commandStorage.numberList.add(0, number)
    }

    /**
     * remove *first* number from number list
     */
    override fun reverseAction() {
        commandStorage.numberList.removeFirst()
    }
}
