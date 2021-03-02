package action

import commandstorage.CommandStorage

class InsertHead(private val number: Int, override var commandStorage: CommandStorage) : Action {

    override fun doAction() {
        commandStorage.numberList.add(0, number)
        commandStorage.addAction(this)
    }

    override fun reverseAction() {
        commandStorage.numberList.removeFirst()
    }
}
