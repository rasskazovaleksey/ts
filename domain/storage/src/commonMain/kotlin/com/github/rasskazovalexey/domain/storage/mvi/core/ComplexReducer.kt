package com.github.rasskazovalexey.domain.storage.mvi.core

interface ComplexReducer<State, Msg, Eff> {
    operator fun invoke(
        state: State,
        msg: Msg,
    ): Return<State, Eff>
}
