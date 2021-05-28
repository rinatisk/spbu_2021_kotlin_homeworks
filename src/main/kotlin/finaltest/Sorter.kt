package finaltest

/**
 * Public object to use generic bubble sort
 */
object Sorter {

    /**
     * Function to bubble sort with custom *comparator*
     * @property toSort object which contains elements to sort
     * @property comparator your custom comparator
     */
    fun <E> sort(toSort: Iterable<E>, comparator: Comparator<E>): MutableList<E> {
        val array = toSort.toMutableList()
        if (array.isNullOrEmpty()) throw ArrayIndexOutOfBoundsException("You write empty object to sort")
        for (pass in 0 until (array.size - 1)) {
            for (currentPosition in 0 until (array.size - pass - 1)) {

                // because compare return positive number if first obj greater
                if (comparator.compare(array[currentPosition], array[currentPosition + 1]) > 0) {
                    array.swap(currentPosition, currentPosition + 1)
                }
            }
        }
        return array
    }

    /**
     * Function to safe swap in generic mutable list
     * @property from index of first element
     * @property to index of second element
     */
    private fun <E> MutableList<E>.swap(from: Int, to: Int) {
        if (from !in this.indices || to !in this.indices) throw IndexOutOfBoundsException("Invalid index")
        val tmp = this[from]
        this[from] = this[to]
        this[from + 1] = tmp
    }
}

fun main() {
    val list = listOf<String>("aaaaaaa", "aa", "aa")
    val comparator = compareBy<String> { it.length }
    val list2 = Sorter.sort(list, comparator)
    println(list2)
}
