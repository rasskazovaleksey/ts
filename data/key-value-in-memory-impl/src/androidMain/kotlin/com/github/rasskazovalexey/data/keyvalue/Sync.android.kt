package com.github.rasskazovalexey.data.keyvalue

actual typealias Synchronizable = Any

internal actual inline fun <R> Any.sync(noinline block: () -> R): R = synchronized(this, block)
