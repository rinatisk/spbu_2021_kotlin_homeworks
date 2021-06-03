package homework7

abstract class MergeSorter{

    fun mergeSort(toSortList: MutableList<Int>, numberOfThreads: Int): List<Int> {
        val sortedList = MutableList(toSortList.size) { 0 }
        toSortList.parallelSort(sortedList, Borders(0, toSortList.size - 1), 0, numberOfThreads)
        return sortedList
    }

    abstract fun MutableList<Int>.parallelSort(resultList: MutableList<Int>,
                           borders: Borders,
                           leftStart: Int,
                           numberOfThreads: Int)

    abstract fun MutableList<Int>.parallelMerge(resultList: MutableList<Int>,
                                                leftBorders: Borders,
                                                rightBorders: Borders,
                                                leftStart: Int,
                                                numberOfThreads: Int)

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