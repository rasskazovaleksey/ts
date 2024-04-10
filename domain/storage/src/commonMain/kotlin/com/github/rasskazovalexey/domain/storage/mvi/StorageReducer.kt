package com.github.rasskazovalexey.domain.storage.mvi

import com.github.rasskazovalexey.domain.storage.mvi.StorageRedux.Effect
import com.github.rasskazovalexey.domain.storage.mvi.StorageRedux.Message
import com.github.rasskazovalexey.domain.storage.mvi.StorageRedux.State
import com.github.rasskazovalexey.domain.storage.mvi.core.ComplexReducer
import com.github.rasskazovalexey.domain.storage.mvi.core.Return
import com.github.rasskazovalexey.domain.storage.mvi.core.pure
import com.github.rasskazovalexey.domain.storage.mvi.core.withEffect
import com.github.rasskazovalexey.domain.storage.mvi.core.withEffects

class StorageReducer : ComplexReducer<State, Message, Effect> {
    override fun invoke(
        state: State,
        msg: Message,
    ): Return<State, Effect> {
        return when (msg) {
            Message.OnBeginTransaction -> state.withEffect(Effect.BeginTransaction)
            Message.OnCommitTransaction -> state.withEffect(Effect.CommitTransaction)
            Message.OnRollbackTransaction -> state.withEffect(Effect.RollbackTransaction)
            Message.OnCountValue -> state.withEffect(Effect.CountValue(state.value))
            Message.OnDeleteValue -> state.withEffect(Effect.DeleteValue(state.key))
            Message.OnGetValue -> state.withEffects(Effect.GetValue(state.key))
            Message.OnSetValue -> state.withEffects(Effect.SetValue(state.key, state.value))
            is Message.OnAppendLog ->
                state.copy(
                    log = listOf(State.LogEntry(message = msg.log, level = msg.level)) + state.log,
                ).pure()

            is Message.OnKeyChanged -> state.copy(key = msg.key).pure()
            is Message.OnValueChanged -> state.copy(value = msg.value).pure()
        }
    }
}
