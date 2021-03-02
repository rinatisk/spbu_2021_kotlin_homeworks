package commandstorage

import action.Action

class CommandStorage {
    private val _numberList = mutableListOf<Int>()

    val numberList: MutableList<Int> get() = _numberList

    fun addAction(action: Action) = actionList.add(action)

    private var actionList = mutableListOf<Action>()

    fun revertLastAction() {
        actionList.last().reverseAction()
        actionList.removeLast()
    }
}
