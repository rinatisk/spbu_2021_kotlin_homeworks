package commandstorage

import action.Action
import action.InsertHead
import action.InsertTail
import action.Move
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.io.File

class CommandStorage {
    private val _numberList = mutableListOf<Int>()

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

    private val format = Json { serializersModule = module }

    fun writeSerialization(resource: String) {
        val toWrite = format.encodeToString(actionList)
        File(resource).writeText(toWrite)
    }

    fun readSerialization(resource: String) {
        val toRead = File(resource).readText()
        format.decodeFromString<MutableList<Action>>(toRead).map { doAction(it) }
    }
}
