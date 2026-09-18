package com.frogobox.sdk.delegate.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

/**
 * Modern reactive data persistence delegate backed by Jetpack Preferences DataStore.
 *
 * Provides transactional, thread-safe asynchronous storage operations with
 * Kotlin Coroutines Flow and suspend functions.
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */
interface DataStoreDelegates {

    /**
     * Observes a preference value as a reactive [Flow].
     * Emits the updated value whenever the preference changes.
     */
    fun <T> getPreferenceFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T>

    /**
     * Reads a preference value once asynchronously.
     */
    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): T

    /**
     * Persists a preference value asynchronously.
     */
    suspend fun <T> savePreference(key: Preferences.Key<T>, value: T)

    /**
     * Removes a specific preference entry.
     */
    suspend fun <T> removePreference(key: Preferences.Key<T>)

    /**
     * Clears all stored preferences in this DataStore instance.
     */
    suspend fun clearAllPreferences()

    // ---------------------------------------------------------------------------------------------
    // Type-Specific Convenience Methods (String Key)
    // ---------------------------------------------------------------------------------------------

    fun getStringFlow(key: String, defaultValue: String = ""): Flow<String>
    fun getIntFlow(key: String, defaultValue: Int = 0): Flow<Int>
    fun getBooleanFlow(key: String, defaultValue: Boolean = false): Flow<Boolean>
    fun getFloatFlow(key: String, defaultValue: Float = 0f): Flow<Float>
    fun getLongFlow(key: String, defaultValue: Long = 0L): Flow<Long>

    suspend fun getString(key: String, defaultValue: String = ""): String
    suspend fun getInt(key: String, defaultValue: Int = 0): Int
    suspend fun getBoolean(key: String, defaultValue: Boolean = false): Boolean
    suspend fun getFloat(key: String, defaultValue: Float = 0f): Float
    suspend fun getLong(key: String, defaultValue: Long = 0L): Long

    suspend fun saveString(key: String, value: String)
    suspend fun saveInt(key: String, value: Int)
    suspend fun saveBoolean(key: String, value: Boolean)
    suspend fun saveFloat(key: String, value: Float)
    suspend fun saveLong(key: String, value: Long)

    suspend fun remove(key: String)
}
