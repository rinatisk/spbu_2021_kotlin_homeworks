package action

import commandstorage.CommandStorage

public class InsertTail(private val toInsertNumber: Int, override var commandStorage: CommandStorage) : Action {

    override fun doAction() {
        commandStorage.getNumberList().add(toInsertNumber)
        commandStorage.getActionList().add(this)
    }

    override fun reverseAction() {
        commandStorage.getNumberList().removeLast()
        commandStorage.getActionList().removeLast()
    }
}
