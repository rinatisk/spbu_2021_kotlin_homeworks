package commandstorage

import action.Action

/**
 * Storage which contains number list and action list
 *
 * Storage to store a list of number in which we can add numbers with *actions*
 * and list of performed actions in which we can reverse last
 * @property _numberList private list of numbers
 * @property numberList public *API* to get list of numbers
 * @property actionList list of performed actions
 */

class CommandStorage {
    private val _numberList = mutableListOf<Int>()

    val numberList: MutableList<Int> get() = _numberList

    private val actionList = mutableListOf<Action>()

    /**
     * perform action and add this to action list
     */
    fun doAction(action: Action) {
        action.doAction()
        actionList.add(action)
    }

    /**
     * reverse last performed action and remove this from action list
     */

    fun revertLastAction() {
        actionList.last().reverseAction()
        actionList.removeLast()
    }
}
