package action

import commandstorage.CommandStorage

public interface Action {
    var commandStorage: CommandStorage
    fun doAction()
    fun reverseAction()
}
