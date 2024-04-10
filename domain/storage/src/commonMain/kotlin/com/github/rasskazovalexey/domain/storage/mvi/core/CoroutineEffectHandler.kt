package com.github.rasskazovalexey.domain.storage.mvi.core

interface CoroutineEffectHandler<in E, out M> {
    suspend fun handle(eff: E): M?
}
