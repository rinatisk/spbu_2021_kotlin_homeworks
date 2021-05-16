package avl

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AVLTreeTest {

    @Test
    fun getEntriesString() {
        val tree = AVLTree<String, String>()
        val expected = setOf(Pair("a", "aboba"), Pair("b", "notaboba"))

        tree.insert("a", "aboba")
        tree.insert("b", "notaboba")
        val actual = tree.entries.map { it.toPair() }.toSet()

        assertEquals(expected, actual)
    }

    @Test
    fun getKeys() {
        val tree = AVLTree<String, String>()
        val expected = setOf("a", "b")

        tree.insert("a", "aboba")
        tree.insert("b", "notaboba")
        val actual = tree.keys

        assertEquals(expected, actual)
    }

    @Test
    fun getValues() {
        val tree = AVLTree<String, String>()
        val expected = listOf("aboba", "notaboba")

        tree.insert("a", "aboba")
        tree.insert("b", "notaboba")
        val actual = tree.values

        assertEquals(expected, actual)
    }

    @Test
    fun get() {
        val tree = AVLTree<String, String>()
        val expected = "aboba"

        tree.insert("a", "aboba")
        tree.insert("b", "notaboba")
        val actual = tree.get("a")

        assertEquals(expected, actual)
    }

    @Test
    fun containsValue() {
        val tree = AVLTree<String, String>()

        tree.insert("a", "aboba")
        tree.insert("b", "notaboba")

        assertEquals(tree.containsValue("aboba"), true)
    }

    @Test
    fun containsKey() {
        val tree = AVLTree<String, String>()

        tree.insert("a", "aboba")
        tree.insert("b", "notaboba")

        assertEquals(tree.containsKey("a"), true)
    }

    @Test
    fun isEmpty() {
        val tree = AVLTree<String, String>()

        tree.insert("a", "aboba")
        tree.insert("b", "notaboba")

        assertEquals(tree.isEmpty(), false)
    }

    @Test
    fun insert() {
        val tree = AVLTree<String, String>()

        tree.insert("a", "aboba")
        tree.insert("b", "notaboba")

        assertEquals(!tree.isEmpty(), true)
    }

    @Test
    fun remove() {
        val tree = AVLTree<String, String>()

        tree.insert("a", "aboba")
        tree.insert("b", "notaboba")
        tree.remove("b")

        assertEquals(tree.size, 1)
    }
}