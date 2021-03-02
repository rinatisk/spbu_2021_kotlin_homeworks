package action

import commandstorage.CommandStorage

class InsertTail(private val toInsertNumber: Int, override var commandStorage: CommandStorage) : Action {

    override fun doAction() {
        commandStorage.numberList.add(toInsertNumber)
    }

    override fun reverseAction() {
        commandStorage.numberList.removeLast()
    }
}
