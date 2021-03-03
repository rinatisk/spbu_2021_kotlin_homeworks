package action

import commandstorage.CommandStorage

class InsertTail(private val number: Int, override var commandStorage: CommandStorage) : Action {

    override fun doAction() {
        commandStorage.numberList.add(number)
        commandStorage.addAction(this)
    }

    override fun reverseAction() {
        commandStorage.numberList.removeLast()
    }
}
