package action

import commandstorage.CommandStorage

class InsertHead(private val number: Int, override val commandStorage: CommandStorage) : Action {

    override fun doAction() {
        commandStorage.numberList.add(0, number)
    }

    override fun reverseAction() {
        commandStorage.numberList.removeFirst()
    }
}
