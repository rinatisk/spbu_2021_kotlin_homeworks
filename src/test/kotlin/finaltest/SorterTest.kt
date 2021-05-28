package finaltest

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SorterTest {
    @Test
    fun sortString() {
        val toSort = listOf<String>("aaaaaaa", "aa", "aa", "aaa", "a")

        val comparator = compareBy<String> { it.length }
        val actual = Sorter.sort(toSort, comparator)

        assertEquals(listOf("a", "aa", "aa", "aaa", "aaaaaaa"), actual)
    }

    @Test
    fun sortInt() {
        val toSort = listOf<Int>(1, 2, 13, 10, 2, 0, -1, 50)

        val actual = Sorter.sort(toSort, Comparator.naturalOrder())

        assertEquals(listOf(-1, 0, 1, 2, 2, 10, 13, 50), actual)
    }

    @Test
    fun sortEmpty() {
        val toSort = listOf<Int>()

        assertThrows(IndexOutOfBoundsException::class.java) {
            Sorter.sort(toSort, Comparator.naturalOrder())
        }
    }
}