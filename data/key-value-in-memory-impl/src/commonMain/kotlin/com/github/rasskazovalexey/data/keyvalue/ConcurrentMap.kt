package com.github.rasskazovalexey.data.keyvalue

internal class ConcurrentMap<K, V> : Synchronizable(), MutableMap<K, V> {
    private val internal = mutableMapOf<K, V>()

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = this.sync { internal.entries }
    override val keys: MutableSet<K>
        get() = throw NotImplementedError("Shall only be called internally in in memory storage")
    override val size: Int
        get() = this.sync { internal.size }
    override val values: MutableCollection<V>
        get() = throw NotImplementedError("Shall only be called internally in in memory storage")

    override fun clear() {
        throw NotImplementedError("Shall only be called internally in in memory storage")
    }

    override fun isEmpty(): Boolean {
        return this.sync { internal.isEmpty() }
    }

    override fun remove(key: K): V? {
        return this.sync { internal.remove(key) }
    }

    override fun putAll(from: Map<out K, V>) {
        return this.sync { internal.putAll(from) }
    }

    override fun put(
        key: K,
        value: V,
    ): V? {
        return this.sync {
            internal.put(key, value)
        }
    }

    override fun get(key: K): V? {
        return this.sync { internal.get(key) }
    }

    override fun containsValue(value: V): Boolean {
        return this.sync { internal.containsValue(value) }
    }

    override fun containsKey(key: K): Boolean {
        return this.sync { internal.containsKey(key) }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ConcurrentMap<*, *>

        return internal == other.internal
    }

    override fun hashCode(): Int {
        return internal.hashCode()
    }
}
