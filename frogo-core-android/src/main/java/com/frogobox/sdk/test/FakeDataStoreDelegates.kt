package com.frogobox.sdk.test

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.frogobox.sdk.delegate.datastore.DataStoreDelegates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

/**
 * Fast in-memory implementation of [DataStoreDelegates] for unit tests.
 *
 * Avoids disk I/O, Robolectric, and filesystem dependencies, enabling blazing fast tests.
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */
class FakeDataStoreDelegates : DataStoreDelegates {

    private val inMemoryStorage = MutableStateFlow<Map<String, Any>>(emptyMap())

    @Suppress("UNCHECKED_CAST")
    override fun <T> getPreferenceFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return inMemoryStorage.map { map ->
            (map[key.name] as? T) ?: defaultValue
        }
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): T {
        return (inMemoryStorage.value[key.name] as? T) ?: defaultValue
    }

    override suspend fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        val updated = inMemoryStorage.value.toMutableMap()
        if (value != null) {
            updated[key.name] = value as Any
        } else {
            updated.remove(key.name)
        }
        inMemoryStorage.value = updated
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        val updated = inMemoryStorage.value.toMutableMap()
        updated.remove(key.name)
        inMemoryStorage.value = updated
    }

    override suspend fun clearAllPreferences() {
        inMemoryStorage.value = emptyMap()
    }

    // ---------------------------------------------------------------------------------------------
    // Convenience String Key Overrides
    // ---------------------------------------------------------------------------------------------

    override fun getStringFlow(key: String, defaultValue: String): Flow<String> =
        getPreferenceFlow(stringPreferencesKey(key), defaultValue)

    override fun getIntFlow(key: String, defaultValue: Int): Flow<Int> =
        getPreferenceFlow(intPreferencesKey(key), defaultValue)

    override fun getBooleanFlow(key: String, defaultValue: Boolean): Flow<Boolean> =
        getPreferenceFlow(booleanPreferencesKey(key), defaultValue)

    override fun getFloatFlow(key: String, defaultValue: Float): Flow<Float> =
        getPreferenceFlow(floatPreferencesKey(key), defaultValue)

    override fun getLongFlow(key: String, defaultValue: Long): Flow<Long> =
        getPreferenceFlow(longPreferencesKey(key), defaultValue)

    override suspend fun getString(key: String, defaultValue: String): String =
        getPreference(stringPreferencesKey(key), defaultValue)

    override suspend fun getInt(key: String, defaultValue: Int): Int =
        getPreference(intPreferencesKey(key), defaultValue)

    override suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        getPreference(booleanPreferencesKey(key), defaultValue)

    override suspend fun getFloat(key: String, defaultValue: Float): Float =
        getPreference(floatPreferencesKey(key), defaultValue)

    override suspend fun getLong(key: String, defaultValue: Long): Long =
        getPreference(longPreferencesKey(key), defaultValue)

    override suspend fun saveString(key: String, value: String) =
        savePreference(stringPreferencesKey(key), value)

    override suspend fun saveInt(key: String, value: Int) =
        savePreference(intPreferencesKey(key), value)

    override suspend fun saveBoolean(key: String, value: Boolean) =
        savePreference(booleanPreferencesKey(key), value)

    override suspend fun saveFloat(key: String, value: Float) =
        savePreference(floatPreferencesKey(key), value)

    override suspend fun saveLong(key: String, value: Long) =
        savePreference(longPreferencesKey(key), value)

    override suspend fun remove(key: String) {
        val updated = inMemoryStorage.value.toMutableMap()
        updated.remove(key)
        inMemoryStorage.value = updated
    }
}
