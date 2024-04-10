package com.github.rasskazovalexey.data.keyvalue

expect open class Synchronizable()

internal expect inline fun <R> Synchronizable.sync(noinline block: () -> R): R
