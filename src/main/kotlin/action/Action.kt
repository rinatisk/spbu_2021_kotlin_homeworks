package action

import commandstorage.CommandStorage

/**
 * Base class for *Action's*.
 * @property commandStorage storage which contains list of performed commands
 */
interface Action {
    val commandStorage: CommandStorage

    fun doAction()
    fun reverseAction()
}
