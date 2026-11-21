package com.frogobox.sdk.delegate.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

// Global top-level delegate property per preference name
private val Context.frogoInternalDataStore: DataStore<Preferences> by preferencesDataStore(name = "frogo_default_datastore")

/**
 * Concrete implementation of [DataStoreDelegates] using Jetpack Preferences DataStore.
 *
 * Catches IOExceptions gracefully (e.g. corrupted files) and emits empty preferences as fallback.
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */
class DataStoreDelegatesImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreDelegates {

    /** Secondary constructor that uses default application dataStore */
    constructor(context: Context) : this(context.applicationContext.frogoInternalDataStore)

    override fun <T> getPreferenceFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[key] ?: defaultValue
            }
    }

    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): T {
        return getPreferenceFlow(key, defaultValue).first()
    }

    override suspend fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    override suspend fun clearAllPreferences() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Type-Specific Helpers
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
        dataStore.edit { preferences ->
            // Try removing string key across possible preference key types
            preferences.remove(stringPreferencesKey(key))
            preferences.remove(intPreferencesKey(key))
            preferences.remove(booleanPreferencesKey(key))
            preferences.remove(floatPreferencesKey(key))
            preferences.remove(longPreferencesKey(key))
        }
    }
}
