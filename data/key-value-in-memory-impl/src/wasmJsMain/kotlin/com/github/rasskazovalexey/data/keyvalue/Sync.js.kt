package com.github.rasskazovalexey.data.keyvalue

actual typealias Synchronizable = Any

internal actual inline fun <R> Synchronizable.sync(noinline block: () -> R): R = block()
