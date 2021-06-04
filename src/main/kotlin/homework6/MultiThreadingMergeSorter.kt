package homework6

import homework7.MergeSorter

object MultiThreadingMergeSorter : MergeSorter() {

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

            val leftNumberOfThreads = numberOfThreads / 2
            val rightNumberOfThreads = numberOfThreads - leftNumberOfThreads

            val leftThread = Thread {
                this.parallelMerge(
                    leftNumberOfThreads, Borders(leftBorders.left, leftBorders.middle - 1),
                    Borders(rightBorders.left, secondMiddle - 1),
                    leftStart, resultList
                )
            }
            val rightThread = Thread {
                this.parallelMerge(
                    rightNumberOfThreads, Borders(leftBorders.middle + 1, leftBorders.right),
                    Borders(secondMiddle, rightBorders.right),
                    listMiddle + 1, resultList
                )
            }

            leftThread.start()
            rightThread.start()
            leftThread.join()
            rightThread.join()
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

                    val leftNumberOfThreads = numberOfThreads / 2
                    val rightNumberOfThreads = numberOfThreads - leftNumberOfThreads

                    val leftThread = Thread {
                        this.parallelSort(
                            leftNumberOfThreads, Borders(borders.left, borders.middle),
                            0, newList
                        )
                    }
                    val rightThread = Thread {
                        this.parallelSort(
                            rightNumberOfThreads, Borders(borders.middle + 1, borders.right),
                            currentMiddle + 1, newList
                        )
                    }

                    leftThread.start()
                    rightThread.start()
                    leftThread.join()
                    rightThread.join()
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
