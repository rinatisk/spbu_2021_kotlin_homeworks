package commandstorage

import action.Action
import action.InsertHead
import action.InsertTail
import action.Move
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass


class CommandStorage {
    private val _numberList = mutableListOf<Int>(1, 2, 3)

    val numberList: MutableList<Int> get() = _numberList

    private val actionList = mutableListOf<Action>()

    fun doAction(action: Action) {
        action.doAction(this)
        actionList.add(action)
    }

    fun revertLastAction() {
        actionList.last().reverseAction(this)
        actionList.removeLast()
    }

    private val module = SerializersModule {
        polymorphic(Action::class) {
            subclass(InsertTail::class)
            subclass(InsertHead::class)
            subclass(Move::class)
        }
    }

    val format = Json { serializersModule = module }

    fun getSeria(): String {
        return format.encodeToString(actionList)
    }


}
