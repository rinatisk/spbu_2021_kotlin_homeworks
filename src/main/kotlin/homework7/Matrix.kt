package homework7

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.IllegalStateException

class Matrix(private val rows: Array<IntArray>) {

    private val size = rows.size

    init {
        rows.forEach { if (it.size != rows.size) throw IllegalStateException("Matrix not square") }
    }

    operator fun times(operand: Matrix): Matrix {

        if (this.size != operand.size) throw IllegalStateException("Matrices have different sizes")

        val jobsList = mutableListOf<Job>()

        val resultRows = Array(rows.size) { IntArray(rows.size) { 0 } }
        runBlocking {
            for (i in rows.indices) {
                for (j in rows.indices) {
                    for (k in rows.indices) {
                        jobsList.add(launch {
                            resultRows[i][j] += rows[i][k] * operand.rows[k][j]
                        })
                    }
                }
            }
            jobsList.forEach { it.join() }
        }
        return Matrix(resultRows)
    }
    override fun equals(other: Any?): Boolean {
        if (this.javaClass != other?.javaClass) return false

        return this.rows.contentDeepEquals((other as Matrix).rows)
    }

    override fun hashCode(): Int = rows.contentDeepHashCode()
}
