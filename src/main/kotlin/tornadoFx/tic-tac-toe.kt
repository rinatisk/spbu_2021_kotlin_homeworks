package tornadoFx
import javafx.scene.Parent
import tornadofx.*

enum class Mark() {
    O, X
}

class Cell(val type: Mark): Fragment() {
    override val root = label(type.name)
}

class Board: View("Board") {
    override val root = tableview<Cell> {
        button("Play")
    }
}

fun main() {
    val a = Board()
}

