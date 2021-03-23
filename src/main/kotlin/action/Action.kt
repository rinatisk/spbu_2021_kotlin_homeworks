package action

import commandstorage.CommandStorage

/**
 * Base class for *Action's*.
 */
interface Action {
    fun doAction(commandStorage: CommandStorage)
    fun reverseAction(commandStorage: CommandStorage)
}
