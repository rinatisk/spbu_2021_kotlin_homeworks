@file:Suppress("ReturnCount")

package homework6

object Sorter {

    fun MutableList<Int>.sort(numberOfThreads: Int): List<Int> {
        val sortedList = MutableList(this.size) { 0 }
        this.multiThreadSort(sortedList, Borders(0, this.size - 1), 0, numberOfThreads)
        return sortedList
    }

    private fun MutableList<Int>.binarySearch(key: Int, borders: Borders): Int {
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

    private fun MutableList<Int>.multiThreadMerge(
        resultList: MutableList<Int>,
        leftBorders: Borders,
        rightBorders: Borders,
        start: Int,
        numberOfThreads: Int
    ) {

        if (kotlin.math.max(leftBorders.size, rightBorders.size) == 0) return

        if (leftBorders.size < rightBorders.size) {
            this.multiThreadMerge(resultList, rightBorders, leftBorders, start, numberOfThreads)
            return
        }

        val secondMiddle = this.binarySearch(this[leftBorders.middle], rightBorders)

        val listMiddle = start + (leftBorders.middle - leftBorders.left) + (secondMiddle - rightBorders.left)

        resultList[listMiddle] = this[leftBorders.middle]

        if (numberOfThreads < 1) {
            this.multiThreadMerge(
                resultList,
                Borders(leftBorders.left, leftBorders.middle - 1),
                Borders(rightBorders.left, secondMiddle - 1),
                start,
                numberOfThreads
            )
            this.multiThreadMerge(
                resultList,
                Borders(leftBorders.middle + 1, leftBorders.right),
                Borders(secondMiddle, rightBorders.right),
                listMiddle + 1,
                numberOfThreads
            )
        } else {

            val leftNumberOfThreads = numberOfThreads / 2
            val rightNumberOfThreads = numberOfThreads - leftNumberOfThreads

            val leftThread = Thread {
                this.multiThreadMerge(
                    resultList, Borders(leftBorders.left, leftBorders.middle - 1),
                    Borders(rightBorders.left, secondMiddle - 1),
                    start, leftNumberOfThreads
                )
            }
            val rightThread = Thread {
                this.multiThreadMerge(
                    resultList, Borders(leftBorders.middle + 1, leftBorders.right),
                    Borders(secondMiddle, rightBorders.right),
                    listMiddle + 1, rightNumberOfThreads
                )
            }

            leftThread.start()
            rightThread.start()
            leftThread.join()
            rightThread.join()
        }
    }

    private fun MutableList<Int>.multiThreadSort(
        resultList: MutableList<Int>,
        borders: Borders,
        start: Int,
        numberOfThreads: Int
    ) {
        when (borders.size) {
            0 -> return
            1 -> {
                resultList[start] = this[borders.left]
            }
            else -> {
                val newList = MutableList(borders.size) { 0 }
                val currentMiddle = borders.middle - borders.left
                if (numberOfThreads < 1) {
                    this.multiThreadSort(
                        newList, Borders(borders.left, borders.middle),
                        0, numberOfThreads
                    )
                    this.multiThreadSort(
                        newList, Borders(borders.middle + 1, borders.right),
                        currentMiddle + 1, numberOfThreads
                    )
                } else {
                    val leftThread = Thread {
                        this.multiThreadSort(
                            newList, Borders(borders.left, borders.middle),
                            0, numberOfThreads - 1
                        )
                    }
                    val rightThread = Thread {
                        this.multiThreadSort(
                            newList, Borders(borders.middle + 1, borders.right),
                            currentMiddle + 1, numberOfThreads - 1
                        )
                    }

                    leftThread.start()
                    rightThread.start()
                    leftThread.join()
                    rightThread.join()
                }
                newList.multiThreadMerge(
                    resultList,
                    Borders(0, currentMiddle),
                    Borders(currentMiddle + 1, borders.size - 1),
                    start, numberOfThreads
                )
            }
        }
    }
}
