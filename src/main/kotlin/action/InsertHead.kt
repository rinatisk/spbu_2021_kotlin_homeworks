package action

import commandstorage.CommandStorage

public class InsertHead(private val toInsertNumber: Int, override var commandStorage: CommandStorage) : Action {

    override fun doAction() {
        commandStorage.getNumberList().add(0, toInsertNumber)
        commandStorage.getActionList().add(this)
    }

    override fun reverseAction() {
        commandStorage.getNumberList().removeFirst()
        commandStorage.getActionList().removeLast()
    }
}
