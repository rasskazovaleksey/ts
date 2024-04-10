package com.github.rasskazovalexey.data.keyvalue

import platform.Foundation.NSRecursiveLock

actual open class Synchronizable {

    private val lock: NSRecursiveLock = NSRecursiveLock()
    fun <R> runSynchronized(block: () -> R): R {
        lock.lock()
        try {
            return block()
        } finally {
            lock.unlock()
        }
    }
}

internal actual inline fun <R> Synchronizable.sync(noinline block: () -> R): R = this.runSynchronized(block)
