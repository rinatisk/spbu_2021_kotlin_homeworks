package homework5

import java.io.File
import java.lang.NullPointerException

enum class Operator(val sign: String) {
    Plus("+"),
    Minus("-"),
    Times("*"),
    Div("/")
}

open class ArithmeticParseTreeNode(open val value: Int = 0) {
    override fun toString() = value.toString()
}

class ArithmeticParseTreeOperation(
    private val operator: Operator,
    private val leftChild: ArithmeticParseTreeNode,
    private val rightChild: ArithmeticParseTreeNode
) : ArithmeticParseTreeNode() {

    override val value: Int
        get() = when (operator) {
            Operator.Plus -> leftChild.value + rightChild.value
            Operator.Minus -> leftChild.value - rightChild.value
            Operator.Times -> leftChild.value * rightChild.value
            Operator.Div -> leftChild.value / rightChild.value
        }

    override fun toString(): String =
        ("$value = ($leftChild ${operator.sign} $rightChild)").toString()
}

data class ToParseExpression(val parseList: List<String>, val subtreeRoot: ArithmeticParseTreeNode)

class ArithmeticParseTree(file: String) {
    private val root: ArithmeticParseTreeNode

    init {
        val input = File(file).readText().replace("(", "( ").replace(")", "").split(" ")
        root = parse(input).subtreeRoot
    }

    val value = root.value

    override fun toString() = root.toString()
    private fun parse(list: List<String>): ToParseExpression {
        return when {

            (list.first() == "(") -> {
                val operator = when (list[1]) {
                    "+" -> Operator.Plus
                    "*" -> Operator.Times
                    "-" -> Operator.Minus
                    "/" -> Operator.Div
                    else -> throw ArithmeticException("Invalid operator")
                }
                val operationLeftChild = parse(list.drop(2))
                val leftNode = operationLeftChild.subtreeRoot

                val operationRightChild = parse(operationLeftChild.parseList)
                val rightNode = operationRightChild.subtreeRoot

                ToParseExpression(
                    operationRightChild.parseList,
                    ArithmeticParseTreeOperation(operator, leftNode, rightNode)
                )
            }

            (list.first().toIntOrNull() is Int) -> {

                // return leaf
                ToParseExpression(list.drop(1), ArithmeticParseTreeNode(list.first().toInt()))
            }

            else -> throw NullPointerException("Invalid expression")
        }
    }
}
