package avl

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AVLNodeTest {

    @Test
    fun balance() {
        val tree = AVLTree<Int, Int>()

        tree.insert(4, 44)
        tree.insert(3, 33)
        tree.insert(2, 22)

        assertEquals(3, tree.root?.key)
    }

    @Test
    fun insertNode() {
        val tree = AVLTree<Int, Int>()

        tree.insert(4, 44)
        tree.insert(3, 33)
        tree.insert(5, 55)

        assertTrue(tree.root?.key == 4 && tree.size == 3)
    }

    @Test
    fun removeNode() {
        val tree = AVLTree<Int, Int>()

        tree.insert(4, 44)
        tree.insert(3, 33)
        tree.insert(5, 55)
        tree.remove(4)

        assertTrue(tree.root?.key == 5 && tree.size == 2)
    }

    @Test
    fun nodeFromKey() {
        val tree = AVLTree<Int, Int>()
        val expectedNode = AVLNode(5, 55)


        tree.insert(4, 44)
        tree.insert(3, 33)
        tree.insert(5, 55)

        assertEquals(expectedNode.toPair(), tree.root?.nodeFromKey(5)?.toPair())
    }

    @Test
    fun containsValueNode() {
        val tree = AVLTree<Int, Int>()

        tree.insert(4, 44)
        tree.insert(3, 33)
        tree.insert(5, 55)

        assertTrue(tree.root?.containsValueNode(33) ?: false)
    }

    @Test
    fun getKey() {
        val node = AVLNode(1, 11)

        val key = node.key

        assertEquals(1, key)
    }

    @Test
    fun getValue() {
        val node = AVLNode(1, 11)

        val key = node.value

        assertEquals(11, key)
    }
}