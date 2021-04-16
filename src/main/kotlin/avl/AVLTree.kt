package avl

class AVLTree<K : Comparable<K>, V> : Map<K, V> {

    var root: AVLNode<K, V>? = null
        private set

    override var size: Int = 0

    override val entries: Set<Map.Entry<K, V>>
        get() {
            val entries = mutableSetOf<AVLNode<K, V>>()
            root?.entriesNode(entries) ?: emptySet<AVLNode<K, V>>()
            return entries
        }

    override val keys: Set<K>
        get() = this.entries.map { it.key }.toSet()

    override val values: Collection<V>
        get() = this.entries.map { it.value }

    override fun get(key: K) = root?.nodeFromKey(key)?.value

    override fun containsValue(value: V) = root?.containsValueNode(value) ?: false
    override fun containsKey(key: K) = get(key) != null

    override fun isEmpty() = (size == 0)

    fun insert(key: K, value: V): AVLNode<K, V>? =
        when {
            this.isEmpty() -> {
                root = AVLNode(key, value)
                size++
                null
            }
            root?.insertNode(key, value) == null -> {
                size++
                root = root?.balance()
                null
            }
            else -> root?.insertNode(key, value)
        }

    fun remove(key: K): V? {
        val toRemove: V? = get(key)

        if (toRemove != null) size--

        root = root?.removeNode(key)

        return toRemove
    }
}
