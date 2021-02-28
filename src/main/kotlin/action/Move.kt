package action

import commandstorage.CommandStorage
import kotlin.IndexOutOfBoundsException

public class Move(private val fromIndex: Int, private val toIndex: Int, override var commandStorage: CommandStorage) :
    Action {
    override fun doAction() {
        val toAddNumberCopy = try {
            commandStorage.getNumberList()[fromIndex]
        } catch (e: java.lang.IndexOutOfBoundsException) {
            throw IndexOutOfBoundsException("Invalid index to move")
        }
        commandStorage.getNumberList().removeAt(fromIndex)

        commandStorage.getNumberList().add(toIndex, toAddNumberCopy)
        commandStorage.getActionList().add(this)
    }

    override fun reverseAction() {
        val toAddNumberCopy = commandStorage.getNumberList()[toIndex]
        commandStorage.getNumberList().removeAt(toIndex)
        commandStorage.getNumberList().add(fromIndex, toAddNumberCopy)
        commandStorage.getActionList().add(this)
        commandStorage.getActionList().removeLast()
    }
}