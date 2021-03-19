package action

import commandstorage.CommandStorage

interface Action {
    fun doAction(commandStorage: CommandStorage)
    fun reverseAction(commandStorage: CommandStorage)
}
