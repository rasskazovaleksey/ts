package com.github.rasskazovalexey.data.keyvalue

import kotlin.random.Random

internal class TransactionalKeyValueStore : Synchronizable(), KeyValueStore, TransactionalStore {
    private val internal = ConcurrentMap<String, String>()
    private val transactions = LinkedHashSet<Transaction>()

    override suspend fun get(key: String): String? {
        val transactionalOperation: Transaction.Operation? =
            this.sync {
                for (i in transactions.size - 1 downTo 0) {
                    val transaction = transactions.elementAt(i)
                    transaction[key]?.let { return@sync it }
                }
                null
            }
        return if (transactions.isEmpty()) {
            internal[key]
        } else {
            when (transactionalOperation?.action) {
                Transaction.Action.SET -> transactionalOperation.value
                Transaction.Action.DELETE -> null
                null -> internal[key]
            }
        }
    }

    override suspend fun set(
        key: String,
        value: String,
    ) {
        this.sync {
            if (transactions.isEmpty()) {
                internal[key] = value
            } else {
                transactions.last()[key, value] = Transaction.Action.SET
            }
        }
    }

    override suspend fun delete(key: String) {
        val prevValue = get(key) ?: return
        this.sync {
            if (transactions.isEmpty()) {
                internal.remove(key)
            } else {
                transactions.last()[key, prevValue] = Transaction.Action.DELETE
            }
        }
    }

    override suspend fun count(value: String): Int {
        return this.sync {
            var count = internal.count { it.value == value }
            transactions.forEach { transaction ->
                count += transaction.operations.count { it.value == value }
            }
            count
        }
    }

    override suspend fun begin() {
        this.sync {
            transactions.add(Transaction())
        }
    }

    override suspend fun commit() {
        this.sync {
            if (transactions.isNotEmpty()) {
                val lastTransaction = transactions.last()
                lastTransaction.operations.forEach { operation ->
                    when (operation.action) {
                        Transaction.Action.SET -> internal[operation.key] = requireNotNull(operation.value)
                        Transaction.Action.DELETE -> internal.remove(operation.key)
                    }
                }
                transactions.removeAll { it == lastTransaction }
            } else {
                error("no transaction")
            }
        }
    }

    override suspend fun rollback() {
        this.sync {
            if (transactions.isNotEmpty()) {
                transactions.removeAll { it == transactions.last() }
            } else {
                error("no transaction")
            }
        }
    }

    private data class Transaction(
        val id: Int = Random.nextInt(),
        val operations: LinkedHashSet<Operation> = LinkedHashSet(),
    ) {
        operator fun get(key: String): Operation? {
            return operations.lastOrNull { it.key == key }
        }

        operator fun set(
            key: String,
            value: String,
            action: Action,
        ) {
            operations.add(Operation(key, value, action))
        }

        data class Operation(
            val key: String,
            val value: String,
            val action: Action,
        )

        enum class Action {
            SET,
            DELETE,
        }
    }
}
