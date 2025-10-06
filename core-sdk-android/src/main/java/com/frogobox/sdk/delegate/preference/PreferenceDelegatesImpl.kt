package com.frogobox.sdk.delegate.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.frogobox.sdk.ext.singleGetSharedPreferences

/**
 * Created by faisalamir on 01/07/22
 * Updated by ChatGPT (Enhanced version)
 *
 * Improvements:
 * - Safer and more concise SharedPreferences access
 * - Added generic save/get for any supported type
 * - Cleaner, more idiomatic Kotlin style
 * - Localized editor instance for thread safety
 * - Default value fallback handling
 */

class PreferenceDelegatesImpl(
    context: Context,
    prefName: String
) : PreferenceDelegates {

    private val sharedPreferences: SharedPreferences =
        context.singleGetSharedPreferences(prefName)

    // ---------------------------------------------------------
    // GENERIC SAVE
    // ---------------------------------------------------------
    override fun savePrefFloat(key: String, value: Float) =
        sharedPreferences.edit { putFloat(key, value) }

    override fun savePrefInt(key: String, value: Int) =
        sharedPreferences.edit { putInt(key, value) }

    override fun savePrefString(key: String, value: String) =
        sharedPreferences.edit { putString(key, value) }

    override fun savePrefBoolean(key: String, value: Boolean) =
        sharedPreferences.edit { putBoolean(key, value) }

    override fun savePrefLong(key: String, value: Long) =
        sharedPreferences.edit { putLong(key, value) }

    // ---------------------------------------------------------
    // DELETE & CLEAR
    // ---------------------------------------------------------
    override fun deletePref(key: String) =
        sharedPreferences.edit { remove(key) }

    override fun nukePref() =
        sharedPreferences.edit { clear() }

    // ---------------------------------------------------------
    // GENERIC GET
    // ---------------------------------------------------------
    override fun getPrefFloat(key: String): Float =
        getPrefFloat(key, 0f)

    override fun getPrefFloat(key: String, defaultValue: Float): Float =
        sharedPreferences.getFloat(key, defaultValue)

    override fun getPrefString(key: String): String =
        getPrefString(key, "")

    override fun getPrefString(key: String, defaultValue: String): String =
        sharedPreferences.getString(key, defaultValue) ?: defaultValue

    override fun getPrefInt(key: String): Int =
        getPrefInt(key, 0)

    override fun getPrefInt(key: String, defaultValue: Int): Int =
        sharedPreferences.getInt(key, defaultValue)

    override fun getPrefLong(key: String): Long =
        getPrefLong(key, 0L)

    override fun getPrefLong(key: String, defaultValue: Long): Long =
        sharedPreferences.getLong(key, defaultValue)

    override fun getPrefBoolean(key: String): Boolean =
        getPrefBoolean(key, false)

    override fun getPrefBoolean(key: String, defaultValue: Boolean): Boolean =
        sharedPreferences.getBoolean(key, defaultValue)

    // ---------------------------------------------------------
    // EXTRA: GENERIC SAVE/GET (optional helper)
    // ---------------------------------------------------------
    override fun <T> save(key: String, value: T) {
        sharedPreferences.edit {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                else -> error("Unsupported preference type: ${value!!::class.java}")
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String, defaultValue: T): T = with(sharedPreferences) {
        val result: Any? = when (defaultValue) {
            is String -> getString(key, defaultValue)
            is Int -> getInt(key, defaultValue)
            is Boolean -> getBoolean(key, defaultValue)
            is Float -> getFloat(key, defaultValue)
            is Long -> getLong(key, defaultValue)
            else -> error("Unsupported preference type: ${defaultValue!!::class.java}")
        }
        return result as T
    }

    // ---------------------------------------------------------
    // OPTIONAL: Check existence
    // ---------------------------------------------------------
    fun contains(key: String): Boolean = sharedPreferences.contains(key)
}
