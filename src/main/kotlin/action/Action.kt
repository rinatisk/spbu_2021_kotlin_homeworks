package action

import commandstorage.CommandStorage

/**
 * Base class for *actions*
 *
 * Interface to create *action* subclasses with overriding
 *
 * @property commandStorage storage which contains list of performed commands
 */

interface Action {
    val commandStorage: CommandStorage

    /**
     * Perform an action
     */
    fun doAction()

    /**
     * Perform an reverse action
     */
    fun reverseAction()
}
