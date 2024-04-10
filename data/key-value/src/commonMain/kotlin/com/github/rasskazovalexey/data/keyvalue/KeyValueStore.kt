package com.github.rasskazovalexey.data.keyvalue

/**
 * Key-value store interface.
 */
interface KeyValueStore {
    /**
     * Get value by key.
     *
     * @param key Key.
     * @return Value or null if not found.
     */
    suspend fun get(key: String): String?

    /**
     * Set value by key.
     * If key already exists, it will be overwritten.
     *
     * @param key Key.
     * @param value Value.
     */
    suspend fun set(
        key: String,
        value: String,
    )

    /**
     * Delete value by key.
     * If key not exists, nothing will happen.
     *
     * @param key Key.
     */
    suspend fun delete(key: String)

    /**
     * Count values by value.
     *
     * @param value Value.
     * @return Count of values.
     */
    suspend fun count(value: String): Int
}
