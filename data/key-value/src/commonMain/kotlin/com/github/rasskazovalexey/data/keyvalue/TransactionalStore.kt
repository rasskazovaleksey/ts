package com.github.rasskazovalexey.data.keyvalue

/**
 * Transactional store interface.
 */
interface TransactionalStore {
    /**
     * Begin transaction.
     * */
    suspend fun begin()

    /**
     * Commit transaction.
     *
     * @throws IllegalStateException If there is no transaction.
     * */
    @Throws(IllegalStateException::class)
    suspend fun commit()

    /**
     * Rollback transaction.
     *
     * @throws IllegalStateException If there is no transaction.
     */
    @Throws(IllegalStateException::class)
    suspend fun rollback()
}
