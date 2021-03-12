package action

import commandstorage.CommandStorage

interface Action {
   // val commandStorage: CommandStorage
     fun doAction(commandStorage: CommandStorage)
     fun reverseAction(commandStorage: CommandStorage)
}
