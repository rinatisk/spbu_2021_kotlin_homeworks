package avl

class AVLNode<K : Comparable<K>, V>(private val keyNode: K, private var valueNode: V) : Map.Entry<K, V> {
    private var left: AVLNode<K, V>? = null
    private var right: AVLNode<K, V>? = null

    override val key: K
        get() = keyNode
    override val value: V
        get() = valueNode

    private val height: Int
        get() = kotlin.math.max((left?.height ?: 0), (right?.height ?: 0)) + 1

    private val balanceFactor: Int
        get() = (right?.height ?: 0) - (left?.height ?: 0)

    private fun rotateLeft(): AVLNode<K, V>? {
        val toChangeNode = this.right
        this.right = toChangeNode?.left
        toChangeNode?.left = this
        return toChangeNode
    }

    fun insertNode(key: K, value: V): AVLNode<K, V>? = when {
        key == this.keyNode -> {
            this.valueNode = value
            this
        }
        key < this.keyNode -> if (this.left == null) {
            this.left = AVLNode(key, value)
            null
        } else {
            left?.insertNode(key, value)
        }
        key > this.keyNode -> if (this.right == null) {
            this.right = AVLNode(key, value)
            null
        } else {
            right?.insertNode(key, value)
        }
        else -> null
    }

    fun balance(): AVLNode<K, V>? {
        left = left?.balance()
        right = right?.balance()
        return when {
            this.balanceFactor > 1 -> {
                if (right?.balanceFactor ?: 0 < 0) {
                    right = right?.rotateRight()
                }
                this.rotateLeft()
            }
            this.balanceFactor < -1 -> {
                if (left?.balanceFactor ?: 0 > 0) {
                    left = left?.rotateLeft()
                }
                this.rotateRight()
            }
            else -> this
        }
    }

    fun removeNode(key: K): AVLNode<K, V>? = when {
        key < this.keyNode -> {
            left = left?.removeNode(key)
            this
        }
        key > this.keyNode -> {
            right = right?.removeNode(key)
            this
        }
        else -> {
            if (left == null) right
            if (right == null) left
            val minimalNode: AVLNode<K, V> = right?.getMinimalRecursive() ?: this
            minimalNode.left = this.left
            if (minimalNode != this.right) {
                this.right?.removeMinimal(minimalNode)
                minimalNode.right = this.right
            }
            minimalNode.balance()
        }
    }

    private fun rotateRight(): AVLNode<K, V>? {
        val toChangeNode = this.left
        this.left = toChangeNode?.right
        toChangeNode?.right = this
        return toChangeNode
    }

    fun nodeFromKey(key: K): AVLNode<K, V>? = when {
        key == this.keyNode -> this
        key > this.keyNode -> this.right?.nodeFromKey(key)
        key < this.keyNode -> this.left?.nodeFromKey(key)
        else -> null
    }

    private fun getMinimalRecursive(): AVLNode<K, V> = left?.getMinimalRecursive() ?: this

    private fun removeMinimal(minimalNode: AVLNode<K, V>?) {
        if (left == minimalNode) {
            left = minimalNode?.right
        } else {
            left?.removeMinimal(minimalNode)
        }
    }

    fun containsValueNode(value: V): Boolean =
        this.valueNode == value ||
                left?.containsValueNode(value) ?: false || right?.containsValueNode(value) ?: false

    fun entriesNode(entries: MutableSet<AVLNode<K, V>>) {
        entries += this
        left?.entriesNode(entries)
        right?.entriesNode(entries)
    }
}
