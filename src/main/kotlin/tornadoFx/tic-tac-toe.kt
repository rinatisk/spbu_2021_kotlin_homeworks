package tornadoFx
import javafx.application.Application
import tornadofx.*

enum class Mark {
    O, X
}

class Cell(type: Mark): Fragment() {
    override val root = label(type.name)
}

class Board: View("Board") {
    override val root = tableview<Cell> {
    }
}

class Game: App(Board::class) {
}

fun main(args: Array<String>) {
    launch<Game>(args) }

