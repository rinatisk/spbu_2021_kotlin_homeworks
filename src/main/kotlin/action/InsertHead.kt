package action

import commandstorage.CommandStorage
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("InsertHead")
class InsertHead(private val number: Int) : Action {

    override fun doAction(commandStorage: CommandStorage) {
        commandStorage.numberList.add(0, number)
    }

    override fun reverseAction(commandStorage: CommandStorage) {
        commandStorage.numberList.removeFirst()
    }
}
