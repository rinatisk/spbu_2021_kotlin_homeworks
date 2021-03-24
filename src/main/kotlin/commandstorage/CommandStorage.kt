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

/**
 * Storage, which contains number list and performed actions list.
 * @property numberList public list of numbers
 * @property actionList list of performed actions
 */

class CommandStorage {

    val numberList = mutableListOf<Int>()

    private val actionList = mutableListOf<Action>()

    private val format = Json { serializersModule = module }

    /**
     * Perform action and add this to action list.
     */

    fun doAction(action: Action) {
        action.doAction(this)
        actionList.add(action)
    }

    /**
     * Reverse last performed action and remove this from action list.
     */

    fun revertLastAction() {
        actionList.last().reverseAction(this)
        actionList.removeLast()
    }

    fun writeSerialization(resource: String) {
        val toWrite = format.encodeToString(actionList)
        File(resource).writeText(toWrite)
    }

    fun readSerialization(resource: String) {
        val toRead = File(resource).readText()
        format.decodeFromString<MutableList<Action>>(toRead).forEach { doAction(it) }
    }

    companion object SerializersModule {
        private val module = SerializersModule {
            polymorphic(Action::class) {
                subclass(InsertTail::class)
                subclass(InsertHead::class)
                subclass(Move::class)
            }
        }
    }
}
