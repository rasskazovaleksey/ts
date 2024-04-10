package com.github.rasskazovalexey.domain.storage.mvi.core

fun interface ViewStateConverter<S, V> {
    fun convert(state: S): V
}
