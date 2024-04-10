package com.github.rasskazovalexey.data.keyvalue

/**
 * Key value store component.
 */
object TransactionalKeyValueStoreComponent {
    /**
     * Note: For IoC with out usage of any external libraries, implements singleton pattern. In real world application
     * shall be done with DI framework.
     */
    private val transactionalKeyValueStore = TransactionalKeyValueStore()

    fun createKeyValueStore(): KeyValueStore = transactionalKeyValueStore

    fun createTransactionalStore(): TransactionalStore = transactionalKeyValueStore
}
