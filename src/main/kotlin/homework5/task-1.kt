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
    open fun getString(height: Int = 0) = "....".repeat(height) + value.toString() + "\n"
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

    override fun getString(height: Int) = "....".repeat(height) + operator.sign + "\n" +
            leftChild.getString(height + 1) +
            rightChild.getString(height + 1)
}

data class TreeNodeWithUpdatedList(val parseList: List<String>, val subtreeRoot: ArithmeticParseTreeNode)

class ArithmeticParseTree(file: String) {
    private val root: ArithmeticParseTreeNode

    init {
        val parseList = File(file).readText().replace("(", "( ").replace(")", "").split(" ")
        root = parse(parseList).subtreeRoot
    }

    val value = root.value

    override fun toString() = root.getString(0)
    private fun parse(parseList: List<String>): TreeNodeWithUpdatedList {
        return when {

            (parseList.first() == "(") -> {
                val operator = when (parseList[1]) {
                    "+" -> Operator.Plus
                    "*" -> Operator.Times
                    "-" -> Operator.Minus
                    "/" -> Operator.Div
                    else -> throw ArithmeticException("Invalid operator")
                }
                val operationLeftChild = parse(parseList.drop(2))
                val leftNode = operationLeftChild.subtreeRoot

                val operationRightChild = parse(operationLeftChild.parseList)
                val rightNode = operationRightChild.subtreeRoot

                TreeNodeWithUpdatedList(
                    operationRightChild.parseList,
                    ArithmeticParseTreeOperation(operator, leftNode, rightNode)
                )
            }

            (parseList.first().toIntOrNull() is Int) -> {

                // return leaf
                TreeNodeWithUpdatedList(parseList.drop(1), ArithmeticParseTreeNode(parseList.first().toInt()))
            }

            else -> throw NullPointerException("Invalid expression")
        }
    }
}
