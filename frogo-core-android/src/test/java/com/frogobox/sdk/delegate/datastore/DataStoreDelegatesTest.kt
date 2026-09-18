package com.frogobox.sdk.delegate.datastore

import com.frogobox.sdk.test.FakeDataStoreDelegates
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for DataStoreDelegates operations using FakeDataStoreDelegates.
 */
class DataStoreDelegatesTest {

    private lateinit var dataStoreDelegates: DataStoreDelegates

    @Before
    fun setUp() {
        dataStoreDelegates = FakeDataStoreDelegates()
    }

    @Test
    fun `save and get string preference`() = runTest {
        val key = "user_name"
        val value = "Frogo Developer"

        dataStoreDelegates.saveString(key, value)
        val result = dataStoreDelegates.getString(key, defaultValue = "")

        assertEquals(value, result)
    }

    @Test
    fun `save and get int preference`() = runTest {
        val key = "user_age"
        val value = 25

        dataStoreDelegates.saveInt(key, value)
        val result = dataStoreDelegates.getInt(key, defaultValue = 0)

        assertEquals(value, result)
    }

    @Test
    fun `save and get boolean preference`() = runTest {
        val key = "is_dark_theme"

        dataStoreDelegates.saveBoolean(key, true)
        val result = dataStoreDelegates.getBoolean(key, defaultValue = false)

        assertTrue(result)
    }

    @Test
    fun `observe string preference as flow`() = runTest {
        val key = "app_theme"

        dataStoreDelegates.saveString(key, "Cyberpunk")
        val flowValue = dataStoreDelegates.getStringFlow(key).first()

        assertEquals("Cyberpunk", flowValue)
    }

    @Test
    fun `remove preference restores default`() = runTest {
        val key = "token"
        dataStoreDelegates.saveString(key, "secret-token-xyz")

        dataStoreDelegates.remove(key)
        val result = dataStoreDelegates.getString(key, defaultValue = "expired")

        assertEquals("expired", result)
    }

    @Test
    fun `clear all preferences`() = runTest {
        dataStoreDelegates.saveString("k1", "v1")
        dataStoreDelegates.saveInt("k2", 42)

        dataStoreDelegates.clearAllPreferences()

        assertEquals("", dataStoreDelegates.getString("k1", ""))
        assertEquals(0, dataStoreDelegates.getInt("k2", 0))
    }
}
