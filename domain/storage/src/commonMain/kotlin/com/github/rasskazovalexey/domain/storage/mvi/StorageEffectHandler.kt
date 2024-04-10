package com.github.rasskazovalexey.domain.storage.mvi

import com.github.rasskazovalexey.data.keyvalue.KeyValueStore
import com.github.rasskazovalexey.data.keyvalue.TransactionalStore
import com.github.rasskazovalexey.domain.storage.mvi.StorageRedux.Effect
import com.github.rasskazovalexey.domain.storage.mvi.StorageRedux.Message
import com.github.rasskazovalexey.domain.storage.mvi.core.CoroutineEffectHandler

@Suppress("SwallowedException")
class StorageEffectHandler(
    private val keyValueStore: KeyValueStore,
    private val transactionalStore: TransactionalStore,
) : CoroutineEffectHandler<Effect, Message> {
    override suspend fun handle(eff: Effect): Message {
        return when (eff) {
            Effect.BeginTransaction -> {
                transactionalStore.begin()
                Message.OnAppendLog("Transaction started", Message.OnAppendLog.LogLevel.INFO)
            }

            Effect.CommitTransaction -> {
                try {
                    transactionalStore.commit()
                    Message.OnAppendLog("Transaction committed", Message.OnAppendLog.LogLevel.WARNING)
                } catch (e: IllegalStateException) {
                    Message.OnAppendLog("Nothing to commit", Message.OnAppendLog.LogLevel.ERROR)
                }
            }

            Effect.RollbackTransaction -> {
                try {
                    transactionalStore.rollback()
                    Message.OnAppendLog("Transaction rollback", Message.OnAppendLog.LogLevel.WARNING)
                } catch (e: IllegalStateException) {
                    Message.OnAppendLog("Nothing to rollback", Message.OnAppendLog.LogLevel.ERROR)
                }
            }

            is Effect.CountValue -> {
                val count = keyValueStore.count(eff.value)
                Message.OnAppendLog("Count value of '${eff.value}': $count", Message.OnAppendLog.LogLevel.INFO)
            }

            is Effect.DeleteValue -> {
                keyValueStore.delete(eff.key)
                Message.OnAppendLog("Delete value by key '${eff.key}'", Message.OnAppendLog.LogLevel.INFO)
            }

            is Effect.GetValue -> {
                val value = keyValueStore.get(eff.key)
                Message.OnAppendLog("Get value by key '${eff.key}': $value", Message.OnAppendLog.LogLevel.INFO)
            }

            is Effect.SetValue -> {
                keyValueStore.set(eff.key, eff.value)
                Message.OnAppendLog("Set value by key '${eff.key}': ${eff.value}", Message.OnAppendLog.LogLevel.INFO)
            }
        }
    }
}
