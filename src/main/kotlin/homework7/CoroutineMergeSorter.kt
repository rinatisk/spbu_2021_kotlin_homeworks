package homework7

import homework6.Application
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object CoroutineMergeSorter : MergeSorter() {

    override fun MutableList<Int>.parallelMerge(
        numberOfThreads: Int,
        leftBorders: Borders,
        rightBorders: Borders,
        leftStart: Int,
        resultList: MutableList<Int>
    ) {

        if (kotlin.math.max(leftBorders.size, rightBorders.size) == 0) return

        if (leftBorders.size < rightBorders.size) {
            this.parallelMerge(numberOfThreads, rightBorders, leftBorders, leftStart, resultList)
            return
        }

        val secondMiddle = this.binarySearch(this[leftBorders.middle], rightBorders)

        val listMiddle = leftStart + (leftBorders.middle - leftBorders.left) + (secondMiddle - rightBorders.left)

        resultList[listMiddle] = this[leftBorders.middle]

        if (numberOfThreads <= 1) {
            this.parallelMerge(
                numberOfThreads,
                Borders(leftBorders.left, leftBorders.middle - 1),
                Borders(rightBorders.left, secondMiddle - 1),
                leftStart,
                resultList
            )
            this.parallelMerge(
                numberOfThreads,
                Borders(leftBorders.middle + 1, leftBorders.right),
                Borders(secondMiddle, rightBorders.right),
                listMiddle + 1,
                resultList
            )
        } else {
            runBlocking {
                launch {
                    this@parallelMerge.parallelMerge(
                        leftBorders = Borders(leftBorders.left, leftBorders.middle - 1),
                        rightBorders = Borders(rightBorders.left, secondMiddle - 1),
                        leftStart = leftStart,
                        resultList = resultList
                    )
                }
                launch {
                    this@parallelMerge.parallelMerge(
                        leftBorders = Borders(leftBorders.middle + 1, leftBorders.right),
                        rightBorders = Borders(secondMiddle, rightBorders.right),
                        leftStart = listMiddle + 1,
                        resultList = resultList
                    )
                }
            }
        }
    }

    override fun MutableList<Int>.parallelSort(
        numberOfThreads: Int,
        borders: Borders,
        leftStart: Int,
        resultList: MutableList<Int>
    ) {
        when (borders.size) {
            0 -> return
            1 -> {
                resultList[leftStart] = this[borders.left]
            }
            else -> {
                val newList = MutableList(borders.size) { 0 }
                val currentMiddle = borders.middle - borders.left
                if (numberOfThreads <= 1) {
                    this.parallelSort(
                        numberOfThreads, Borders(borders.left, borders.middle),
                        0, newList
                    )
                    this.parallelSort(
                        numberOfThreads, Borders(borders.middle + 1, borders.right),
                        currentMiddle + 1, newList
                    )
                } else {

                    runBlocking {
                        launch {
                            this@parallelSort.parallelSort(
                                borders = Borders(borders.left, borders.middle), leftStart = 0,
                                resultList = newList
                            )
                        }
                        launch {
                            this@parallelSort.parallelSort(
                                borders = Borders(borders.middle + 1, borders.right), leftStart = currentMiddle + 1,
                                resultList = newList
                            )
                        }
                    }
                }
                newList.parallelMerge(
                    numberOfThreads,
                    Borders(0, currentMiddle),
                    Borders(currentMiddle + 1, borders.size - 1),
                    leftStart, resultList
                )
            }
        }
    }
}
fun main() {
    Application(16, 1000, 1000, 50000, CoroutineMergeSorter)
}
