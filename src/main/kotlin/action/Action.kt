package action

import commandstorage.CommandStorage

/**
 * Base class for *Action's*.
 * @property commandStorage storage which contains list of performed commands
 */
interface Action {
    fun doAction(commandStorage: CommandStorage)
    fun reverseAction(commandStorage: CommandStorage)
}
