package commandstorage

import action.Action

class CommandStorage {
    private val _numberList = mutableListOf<Int>()

    val numberList: MutableList<Int> get() = _numberList

    private val actionList = mutableListOf<Action>()

    fun doAction(action: Action) {
        action.doAction()
        actionList.add(action)
    }

    fun revertLastAction() {
        actionList.last().reverseAction()
        actionList.removeLast()
    }
}
