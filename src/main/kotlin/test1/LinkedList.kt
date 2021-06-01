package test1

class LinkedList<E>(
    private var first: Node<E>? = null,
    private var last: Node<E>? = null,
) {

    private fun node(index: Int): Node<E>? {
        if (index < 0) throw IndexOutOfBoundsException("Invalid index to find node in list")
        var currentNode = this.first
        for (i in 0 until index) {
            currentNode = currentNode?.next ?: throw IndexOutOfBoundsException("Invalid index to find node in list")
        }
        return currentNode
    }

    fun isEmpty() = first == null

    fun get() = first?.data ?: throw IndexOutOfBoundsException("Empty list")

    fun get(position: Int) = node(position)?.data ?: IndexOutOfBoundsException("Invalid index to find node")

    fun add(data: E): Node<E> {
        val currentLast = last
        val newNode = Node(prev = currentLast, data = data)
        last = newNode
        if (currentLast == null) first = newNode else currentLast.next = newNode
        return newNode
    }

    fun add(data: E, position: Int): Node<E> {
        val currentNode = node(position)
        val prevNode = currentNode?.prev
        val newNode = Node(data = data, prev = prevNode, next = currentNode)
        currentNode?.prev = newNode
        if (prevNode == null) {
            first = newNode
        } else prevNode.next = newNode
        return newNode
    }

    fun remove(position: Int) {
        val toRemoveNode = node(position)
        val prev = toRemoveNode?.prev
        val next = toRemoveNode?.next

        if (prev == null) {
            first = next
        } else {
            prev.next = next
            toRemoveNode.prev = null
        }

        if (next == null) {
            last = prev
        } else {
            next.prev = prev
            toRemoveNode.next = null
        }
    }

    class Node<E>(
        var prev: Node<E>? = null,
        var next: Node<E>? = null,
        val data: E
    )
}
