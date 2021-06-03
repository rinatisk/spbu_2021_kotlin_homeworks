package homework6

import homework7.MergeSorter

object MultiThreadingMergeSorter: MergeSorter() {

    override fun MutableList<Int>.parallelMerge(
        resultList: MutableList<Int>,
        leftBorders: Borders,
        rightBorders: Borders,
        leftStart: Int,
        numberOfThreads: Int
    ) {

        if (kotlin.math.max(leftBorders.size, rightBorders.size) == 0) return

        if (leftBorders.size < rightBorders.size) {
            this.parallelMerge(resultList, rightBorders, leftBorders, leftStart, numberOfThreads)
            return
        }

        val secondMiddle = this.binarySearch(this[leftBorders.middle], rightBorders)

        val listMiddle = leftStart + (leftBorders.middle - leftBorders.left) + (secondMiddle - rightBorders.left)

        resultList[listMiddle] = this[leftBorders.middle]

        if (numberOfThreads <= 1) {
            this.parallelMerge(
                resultList,
                Borders(leftBorders.left, leftBorders.middle - 1),
                Borders(rightBorders.left, secondMiddle - 1),
                leftStart,
                numberOfThreads
            )
            this.parallelMerge(
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
                this.parallelMerge(
                    resultList, Borders(leftBorders.left, leftBorders.middle - 1),
                    Borders(rightBorders.left, secondMiddle - 1),
                    leftStart, leftNumberOfThreads
                )
            }
            val rightThread = Thread {
                this.parallelMerge(
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

    override fun MutableList<Int>.parallelSort(
        resultList: MutableList<Int>,
        borders: Borders,
        leftStart: Int,
        numberOfThreads: Int
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
                        newList, Borders(borders.left, borders.middle),
                        0, numberOfThreads
                    )
                    this.parallelSort(
                        newList, Borders(borders.middle + 1, borders.right),
                        currentMiddle + 1, numberOfThreads
                    )
                } else {

                    val leftNumberOfThreads = numberOfThreads / 2
                    val rightNumberOfThreads = numberOfThreads - leftNumberOfThreads

                    val leftThread = Thread {
                        this.parallelSort(
                            newList, Borders(borders.left, borders.middle),
                            0, leftNumberOfThreads
                        )
                    }
                    val rightThread = Thread {
                        this.parallelSort(
                            newList, Borders(borders.middle + 1, borders.right),
                            currentMiddle + 1, rightNumberOfThreads
                        )
                    }

                    leftThread.start()
                    rightThread.start()
                    leftThread.join()
                    rightThread.join()
                }
                newList.parallelMerge(
                    resultList,
                    Borders(0, currentMiddle),
                    Borders(currentMiddle + 1, borders.size - 1),
                    leftStart, numberOfThreads
                )
            }
        }
    }
}
