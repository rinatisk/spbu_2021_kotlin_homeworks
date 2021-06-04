package test1

interface ArithmeticAvailable<T> {
    operator fun plus(i: T): T
    operator fun times(i: T): T
    operator fun minus(i: T): T
    fun isZero(): Boolean
    fun negative(): T
}

class SparseVector<T : ArithmeticAvailable<T>>(private val n: Int, private val pairs: MutableList<Pair<Int, T>>) {

    val data: List<Pair<Int, T>>
        get() = pairs

    fun put(value: T, index: Int) {
        if (index < 0 || index >= n) throw IllegalStateException("Invalid index to put")
        val currentElement = pairs.find { it.first == index }
        if (currentElement != null) pairs.remove(currentElement)
        if (!value.isZero()) pairs.add(Pair(index, value))
    }

    fun get(index: Int): T? {
        if (index < 0 || index >= n) throw IllegalStateException("Invalid index to get")
        return pairs.find { index == it.first }?.second
    }

    fun isNonZero() = pairs.size != 0

    operator fun plus(other: SparseVector<T>): SparseVector<T> {
        if (n != other.n) throw IllegalStateException("Vectors length is not equals")
        val result = this
        for (i in other.pairs) {
            val value = get(i.first)?.plus(i.second) ?: i.second
            if (get(i.first) != null) result.put(value, i.first) else result.put(i.second, i.first)
        }
        return result
    }

    operator fun minus(other: SparseVector<T>): SparseVector<T> {
        if (n != other.n) throw IllegalStateException("Vectors length is not equals")
        val result = this
        for (i in other.pairs) {
            val value = get(i.first)?.minus(i.second) ?: i.second
            if (get(i.first) != null) result.put(value, i.first) else result.put(i.second.negative(), i.first)
        }
        return result
    }

    operator fun times(scalar: T): SparseVector<T> {
        val resultPairs = mutableListOf<Pair<Int, T>>()
        val currentPairs = this.pairs
        currentPairs.forEach { resultPairs.add(Pair(it.first, it.second * scalar)) }
        return SparseVector(n, resultPairs)
    }
}
