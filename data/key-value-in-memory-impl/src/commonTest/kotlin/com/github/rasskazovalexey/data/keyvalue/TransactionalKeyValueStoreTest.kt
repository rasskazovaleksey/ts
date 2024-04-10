package com.github.rasskazovalexey.data.keyvalue

import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TransactionalKeyValueStoreTest {
    private var store: TransactionalKeyValueStore? = null

    @BeforeTest
    fun setUp() {
        this.store = TransactionalKeyValueStore()
    }

    @Test
    fun givenSetValue_whenGetValue() =
        runTest {
            store?.set("foo", "123")

            assertEquals(expected = "123", actual = store?.get("foo"))
        }

    @Test
    fun givenNoValue_whenDeleteIt_thenNoValueExist() =
        runTest {
            store?.delete("foo")

            assertEquals(expected = null, actual = store?.get("foo"))
        }

    @Test
    fun givenMultipleValueSet_whenCount_thenCorrectCount() =
        runTest {
            store?.set("foo", "123")
            store?.set("bar", "456")
            store?.set("baz", "123")

            assertEquals(expected = 2, actual = store?.count("123"))
            assertEquals(expected = 1, actual = store?.count("456"))
            assertEquals(expected = 0, actual = store?.count("999"))
        }

    @Test
    fun givenTransaction_whenCommit_thenChangesApplied() =
        runTest {
            store?.set("bar", "123")
            assertEquals(expected = "123", actual = store?.get("bar"))

            store?.begin()
            store?.set("foo", "456")
            assertEquals(expected = "123", actual = store?.get("bar"))
            store?.delete("bar")
            store?.commit()
            assertEquals(expected = null, actual = store?.get("bar"))
            assertFailsWith<IllegalStateException> {
                store?.rollback()
            }
            assertEquals(expected = "456", actual = store?.get("foo"))
        }

    @Test
    fun givenTransaction_whenRollback_thenChangesDiscarded() =
        runTest {
            store?.set("foo", "123")
            store?.set("bar", "abc")
            assertEquals(expected = "123", actual = store?.get("foo"))
            assertEquals(expected = "abc", actual = store?.get("bar"))

            store?.begin()
            store?.set("foo", "456")
            assertEquals(expected = "456", actual = store?.get("foo"))
            store?.set("bar", "def")
            assertEquals(expected = "def", actual = store?.get("bar"))
            store?.rollback()
            assertEquals(expected = "123", actual = store?.get("foo"))
            assertEquals(expected = "abc", actual = store?.get("bar"))
            assertFailsWith<IllegalStateException> {
                store?.commit()
            }
        }

    @Test
    fun givenMultipleTransaction_whenRollback_thenChangesDiscarded() =
        runTest {
            store?.set("foo", "123")
            store?.set("bar", "456")
            assertEquals(expected = "123", actual = store?.get("foo"))
            assertEquals(expected = "456", actual = store?.get("bar"))

            store?.begin()
            store?.set("foo", "456")
            assertEquals(expected = "456", actual = store?.get("foo"))
            store?.begin()
            assertEquals(expected = 2, actual = store?.count("456"))
            store?.set("foo", "789")
            assertEquals(expected = "789", actual = store?.get("foo"))
            store?.rollback()
            assertEquals(expected = "456", actual = store?.get("foo"))
            store?.delete("foo")
            assertEquals(expected = null, actual = store?.get("foo"))
            store?.rollback()
            assertEquals(expected = "123", actual = store?.get("foo"))
        }
}
