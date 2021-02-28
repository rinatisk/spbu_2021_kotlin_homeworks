package commandstorage

import action.Action

public class CommandStorage {
    private var numberList = mutableListOf<Int>()
    public fun getNumberList() = numberList

    private var actionList = mutableListOf<Action>()
    public fun getActionList() = actionList

    public fun revertLastAction() = actionList.last().reverseAction()
}
