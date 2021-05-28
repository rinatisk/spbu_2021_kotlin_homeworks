@file:Suppress("ReturnCount")

package homework6

object Sorter {
    fun MutableList<Int>.sort(numberOfThreads: Int) =
        this.mergeSortingMultiThread(Borders(0, this.lastIndex), this, numberOfThreads)

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

    private fun MutableList<Int>.multiThreadingMerge(
        resultList: MutableList<Int>,
        leftBorders: Borders,
        rightBorders: Borders,
        leftStart: Int,
        numberOfThreads: Int
    ) {
        if (kotlin.math.max(leftBorders.size, rightBorders.size) == 0) return

        if (leftBorders.size < rightBorders.size) {
            this.multiThreadingMerge(
                resultList,
                rightBorders,
                leftBorders,
                leftStart,
                numberOfThreads
            )
            return
        }

        println(resultList)

        val secondMiddle = if (rightBorders.left > rightBorders.right) rightBorders.left else {
            this.binarySearch(this[leftBorders.middle], rightBorders)
        }

        val listMiddle = leftStart + (leftBorders.middle - leftBorders.left) + (secondMiddle - rightBorders.left)

        resultList[listMiddle] = this[leftBorders.middle]

        if (numberOfThreads < 1) {
            this.multiThreadingMerge(
                resultList, Borders(leftBorders.left, leftBorders.middle),
                Borders(rightBorders.left, secondMiddle - 1), leftStart, 1
            )
            this.multiThreadingMerge(
                resultList, Borders(leftBorders.middle + 1, leftBorders.right),
                Borders(secondMiddle, rightBorders.right),
                listMiddle + 1, 1
            )
        } else {

            val leftNumberOfThreads = numberOfThreads / 2
            val rightNumberOfThreads = numberOfThreads - leftNumberOfThreads

            val leftThread = Thread {
                this.multiThreadingMerge(
                    resultList, Borders(leftBorders.left, leftBorders.middle - 1),
                    Borders(rightBorders.left, secondMiddle - 1), leftStart, leftNumberOfThreads
                )
            }
            val rightThread = Thread {
                this.multiThreadingMerge(
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

    private fun MutableList<Int>.mergeSortingMultiThread(
        toSortBorders: Borders,
        list: MutableList<Int>,
        numberOfThreads: Int,
        leftStart: Int = 0
    ) {

        when (toSortBorders.size) {
            0 -> return
            1 -> list[leftStart] = this[toSortBorders.left]
            else -> {

                val currentList = MutableList(toSortBorders.size) { 0 }
                val currentMiddle = toSortBorders.middle - toSortBorders.left

                if (numberOfThreads < 1) {
                    this.mergeSortingMultiThread(
                        Borders(toSortBorders.left, toSortBorders.middle),
                        currentList, 1,
                        0
                    )

                    this.mergeSortingMultiThread(
                        Borders(toSortBorders.middle + 1, toSortBorders.right),
                        currentList, 1,
                        currentMiddle + 1
                    )
                } else {

                    val leftNumberOfThreads = numberOfThreads / 2
                    val rightNumberOfThreads = numberOfThreads - leftNumberOfThreads

                    val leftThread =
                        Thread {
                            this.mergeSortingMultiThread(
                                Borders(toSortBorders.left, toSortBorders.middle),
                                currentList, leftNumberOfThreads,
                                0
                            )
                        }
                    val rightThread =
                        Thread {
                            this.mergeSortingMultiThread(
                                Borders(toSortBorders.middle + 1, toSortBorders.right),
                                currentList, rightNumberOfThreads,
                                currentMiddle + 1
                            )
                        }
                    leftThread.start()
                    rightThread.start()
                    leftThread.join()
                    rightThread.join()
                }

                currentList.multiThreadingMerge(
                    list,
                    Borders(0, currentMiddle),
                    Borders(currentMiddle + 1, toSortBorders.size - 1),
                    leftStart,
                    numberOfThreads
                )
            }
        }
    }
}
