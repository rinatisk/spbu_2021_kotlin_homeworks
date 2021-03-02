package action

import commandstorage.CommandStorage

interface Action {
    val commandStorage: CommandStorage
    fun doAction()
    fun reverseAction()
}
