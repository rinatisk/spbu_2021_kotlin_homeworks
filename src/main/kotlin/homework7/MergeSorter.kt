package homework7

abstract class MergeSorter {

    fun mergeSort(toSortList: MutableList<Int>, numberOfThreads: Int): List<Int> {
        val sortedList = MutableList(toSortList.size) { 0 }
        toSortList.parallelSort(numberOfThreads, Borders(0, toSortList.size - 1), 0, sortedList)
        return sortedList
    }

    abstract fun MutableList<Int>.parallelSort(
        numberOfThreads: Int = 0,
        borders: Borders,
        leftStart: Int,
        resultList: MutableList<Int>
    )

    abstract fun MutableList<Int>.parallelMerge(
        numberOfThreads: Int = 0,
        leftBorders: Borders,
        rightBorders: Borders,
        leftStart: Int,
        resultList: MutableList<Int>
    )

    protected fun MutableList<Int>.binarySearch(key: Int, borders: Borders): Int {
        var left = borders.left
        var right = borders.right + 1
        while (left < right) {
            val middle = (left + right) / 2
            if (key <= this[middle]) right = middle else left = middle + 1
        }
        return right
    }

    data class Borders(val left: Int, val right: Int) {
        val size = right - left + 1
        val middle = (left + right) / 2
    }
}
