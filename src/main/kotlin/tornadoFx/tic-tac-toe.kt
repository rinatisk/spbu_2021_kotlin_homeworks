package tornadoFx
import tornadofx.Fragment
import tornadofx.label
import tornadofx.View
import tornadofx.tableview
import tornadofx.App
import tornadofx.launch

enum class Mark {
    O, X
}

class Cell(type: Mark) : Fragment() {
    override val root = label(type.name)
}

class Board : View("Board") {
    override val root = tableview<Cell> {
    }
}

class Game : App(Board::class)

fun main(args: Array<String>) {
    launch<Game>(args)
}
