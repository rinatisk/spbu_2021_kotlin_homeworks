package action

import commandstorage.CommandStorage

class InsertTail(private val number: Int, override val commandStorage: CommandStorage) : Action {

    override fun doAction() {
        commandStorage.numberList.add(number)
    }

    override fun reverseAction() {
        commandStorage.numberList.removeLast()
    }
}
