package com.frogobox.sdk.test

import com.frogobox.sdk.delegate.preference.PreferenceDelegates

/**
 * Fast in-memory implementation of [PreferenceDelegates] for unit tests.
 *
 * Avoids Android framework SharedPreferences and Robolectric dependencies.
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */
class FakePreferenceDelegates : PreferenceDelegates {

    private val storage = mutableMapOf<String, Any>()

    override fun savePrefFloat(key: String, value: Float) {
        storage[key] = value
    }

    override fun savePrefInt(key: String, value: Int) {
        storage[key] = value
    }

    override fun savePrefString(key: String, value: String) {
        storage[key] = value
    }

    override fun savePrefBoolean(key: String, value: Boolean) {
        storage[key] = value
    }

    override fun savePrefLong(key: String, value: Long) {
        storage[key] = value
    }

    override fun deletePref(key: String) {
        storage.remove(key)
    }

    override fun nukePref() {
        storage.clear()
    }

    override fun getPrefFloat(key: String): Float = getPrefFloat(key, 0f)

    override fun getPrefFloat(key: String, defaultValue: Float): Float =
        (storage[key] as? Float) ?: defaultValue

    override fun getPrefString(key: String): String = getPrefString(key, "")

    override fun getPrefString(key: String, defaultValue: String): String =
        (storage[key] as? String) ?: defaultValue

    override fun getPrefInt(key: String): Int = getPrefInt(key, 0)

    override fun getPrefInt(key: String, defaultValue: Int): Int =
        (storage[key] as? Int) ?: defaultValue

    override fun getPrefLong(key: String): Long = getPrefLong(key, 0L)

    override fun getPrefLong(key: String, defaultValue: Long): Long =
        (storage[key] as? Long) ?: defaultValue

    override fun getPrefBoolean(key: String): Boolean = getPrefBoolean(key, false)

    override fun getPrefBoolean(key: String, defaultValue: Boolean): Boolean =
        (storage[key] as? Boolean) ?: defaultValue

    override fun <T> save(key: String, value: T) {
        if (value != null) {
            storage[key] = value as Any
        } else {
            storage.remove(key)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String, defaultValue: T): T =
        (storage[key] as? T) ?: defaultValue
}
