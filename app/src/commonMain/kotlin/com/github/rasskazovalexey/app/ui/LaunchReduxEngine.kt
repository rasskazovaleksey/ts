package com.github.rasskazovalexey.app.ui

import com.github.rasskazovalexey.domain.storage.mvi.core.ComplexReducer
import com.github.rasskazovalexey.domain.storage.mvi.core.CoroutineEffectHandler
import com.github.rasskazovalexey.domain.storage.mvi.core.Return
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <S, M, E> LaunchReduxEngine(
    scope: CoroutineScope,
    initial: Return<S, E>,
    reducer: ComplexReducer<S, in M, E>,
    effectHandler: CoroutineEffectHandler<E, M>,
): ReduxEngine<S, M> {
    val messages = Channel<M>(Channel.UNLIMITED)
    val states = MutableStateFlow(initial.state)
    scope.launch {
        initial.effects.forEach {
            effectHandler.handle(it)?.let { messages.trySend(it) }
        }
    }
    scope.launch {
        for (msg in messages) {
            try {
                val prevState = states.value
                val (state, effects) = reducer.invoke(prevState, msg)
                states.value = state
                effects.forEach { effectHandler.handle(it)?.let { messages.trySend(it) } }
            } catch (e: Throwable) {
                Unit
            }
        }
    }
    return ReduxEngine(messages, states)
}

class ReduxEngine<S, M>(
    val input: SendChannel<M>,
    val output: StateFlow<S>,
) {
    infix fun send(msg: M) {
        input.trySend(msg).isSuccess
    }
}
