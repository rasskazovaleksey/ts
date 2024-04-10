package com.github.rasskazovalexey.data.keyvalue

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ConcurrentMapTest {
    @Test
    fun givenEntry_whenPutIntoMap_thenGetIt() {
        val map = ConcurrentMap<String, String>()
        val key = "key"
        val value = "value"

        map[key] = value

        assertEquals(expected = value, actual = map[key])
    }

    @Test
    fun givenEntry_whenPutIntoMap_thenRemoveIt() {
        val map = ConcurrentMap<String, String>()
        val key = "key"
        val value = "value"

        map[key] = value
        map.remove(key)

        assertEquals(expected = null, actual = map[key])
    }

    @Test
    fun givenEntry_whenPutIntoMap_thenUpdateIt() {
        val map = ConcurrentMap<String, String>()
        val key = "key"
        val value = "value"
        val updatedValue = "updatedValue"

        map[key] = value
        map[key] = updatedValue

        assertEquals(expected = updatedValue, actual = map[key])
    }

    @Test
    fun givenMap_whenPutAll_haveAllEntries() {
        val map = ConcurrentMap<String, String>()
        val initialMap = mapOf("key" to "value")

        map.putAll(initialMap)

        assertEquals(expected = initialMap["key"], actual = map["key"])
    }

    @Test
    fun givenMap_whenContainsKey_returnTrue() {
        val map = ConcurrentMap<String, String>()
        val initialMap = mapOf("key" to "value")

        map.putAll(initialMap)

        assertTrue { map.containsKey("key") }
    }

    @Test
    fun givenMap_whenContainsValue_returnTrue() {
        val map = ConcurrentMap<String, String>()
        val initialMap = mapOf("key" to "value")

        map.putAll(initialMap)

        assertTrue { map.containsValue("value") }
    }

    @Test
    fun givenIsEmptyMap_thenEmpty() {
        val map = ConcurrentMap<String, String>()

        assertTrue { map.isEmpty() }
    }

    @Test
    fun givenEntriesMap_whenAddSomeValues_thenReturnEntries() {
        val map = ConcurrentMap<String, String>()
        val initialMap = mapOf("key" to "value")

        map.putAll(initialMap)

        assertEquals(expected = initialMap.entries, actual = map.entries)
    }

    @Test
    fun givenClearMap_failsWithException() {
        val map = ConcurrentMap<String, String>()
        assertFailsWith<NotImplementedError> {
            map.clear()
        }
    }

    @Test
    fun givenKeysMap_failsWithException() {
        val map = ConcurrentMap<String, String>()
        assertFailsWith<NotImplementedError> {
            map.keys
        }
    }

    @Test
    fun givenEmptyMap_thenReturnEmpty() {
        val map = ConcurrentMap<String, String>()

        assertEquals(expected = 0, actual = map.size)
    }

    @Test
    fun givenValuesMap_failsWithException() {
        val map = ConcurrentMap<String, String>()
        assertFailsWith<NotImplementedError> {
            map.values
        }
    }
}
