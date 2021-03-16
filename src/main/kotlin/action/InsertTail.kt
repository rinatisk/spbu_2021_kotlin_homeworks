package action

import commandstorage.CommandStorage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("InsertTail")
class InsertTail(private val number: Int) : Action {

     override fun doAction(commandStorage: CommandStorage) {
        commandStorage.numberList.add(number)
    }

     override fun reverseAction(commandStorage: CommandStorage) {
        commandStorage.numberList.removeLast()
    }
}
