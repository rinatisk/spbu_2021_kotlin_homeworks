package finaltest

fun <E> sort(toSort: Iterable<E>, comparator: Comparator<E> ): MutableList<E> {
    val array = toSort.toMutableList()
    for (pass in 0 until (array.size - 1)) {
        for (currentPosition in 0 until (array.size - pass - 1)) {

            // because compare return positive number if first obj greater
            if (comparator.compare(array[currentPosition], array[currentPosition + 1]) > 0) {
                val tmp = array[currentPosition]
                array[currentPosition] = array[currentPosition + 1]
                array[currentPosition + 1] = tmp
            }
        }
    }
    return array
}


fun main() {
    val list = listOf("aa", "aaaa", "aaaaaaaa", "aaa", "a")
    val comparator = compareBy<String> { it.length }
    val list2 = sort(list, comparator)
    println(list2)
}
