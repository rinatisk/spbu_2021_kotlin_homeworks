package test1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LinkedListTest {

    @Test
    fun isEmpty() {
        val emptyList = LinkedList<Int>()
        val notEmptyList = LinkedList<Int>()
        notEmptyList.add(10)

        assertEquals(emptyList.isEmpty(), true)
        assertEquals(notEmptyList.isEmpty(), false)

        notEmptyList.remove(0)

        assertEquals(notEmptyList.isEmpty(), true)
    }

    @Test
    fun get() {
        val list = LinkedList<Int>()
        for (i in 0..10) list.add(i)

        assertEquals(list.get(), 0)
        assertEquals(list.get(10), 10)

        assertThrows(IndexOutOfBoundsException::class.java) {
            list.get(11)
        }
        assertThrows(IndexOutOfBoundsException::class.java) {
            list.get(-1)
        }

    }


    @Test
    fun add() {
        val list = LinkedList<Int>()
        for (i in 0..10) list.add(i)

        list.add(100)
        assertEquals(list.get(11), 100)

        list.add(200, 10)
        assertEquals(list.get(10), 200)

        assertThrows(IndexOutOfBoundsException::class.java) {
            list.add(300, 15)
        }
    }


    @Test
    fun remove() {
        val list = LinkedList<Int>()
        for (i in 0..10) list.add(i)

        list.remove(5)
        assertEquals(list.get(5), 6)

        list.remove(8)
        assertEquals(list.get(8), 10)

        list.remove(7)
        assertEquals(list.get(6), 7)

        assertThrows(IndexOutOfBoundsException::class.java) {
            list.remove(15)
        }

        assertThrows(IndexOutOfBoundsException::class.java) {
            list.remove(7)
            list.get(7)
        }
    }
}