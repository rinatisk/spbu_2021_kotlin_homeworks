package homework6

import homework6.MergeSorter.mergeSort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MergeSorterTest {
    companion object {

        private fun getRandomList(size: Int): MutableList<Int> = MutableList(size) { kotlin.random.Random.nextInt() }

        @JvmStatic
        fun testSort(): List<Arguments> {
            val argumentsList = mutableListOf<Arguments>()
            var threadNumber = 1
            while (threadNumber < 64) {
                for (size in 0..5000 step 2500) {
                    val list = getRandomList(size)
                    argumentsList.add(Arguments.of(threadNumber, list, list.sorted()))
                }
                threadNumber *= 4
            }
            return argumentsList
        }
    }

    @MethodSource("testSort")
    @ParameterizedTest(name = "sort{index}, {1}")
    fun sort(numberOfThreads: Int, toSortList: MutableList<Int>, expectedList: MutableList<Int>) {
        val actual = toSortList.mergeSort(numberOfThreads)

        assertEquals(expectedList, actual)
    }

}