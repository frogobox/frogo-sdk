package com.frogobox.sdk.preference

import android.content.Context
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.ext.singleGetSharedPreferences

/*
 * Created by faisalamir on 26/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */

class FrogoPreference(
    private val context: Context,
    private val prefName: String
) : IFrogoPreference {

    companion object {
        val TAG: String = FrogoPreference::class.java.simpleName
    }

    private val sharedPreferences by lazy {
        context.singleGetSharedPreferences(prefName)
    }

    private val prefEditor by lazy {
        sharedPreferences.edit()
    }

    override fun savePrefFloat(key: String, value: Float) {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : savePrefFloat -> key   : $key")
        showLogDebug("${PreferenceDelegatesImpl.TAG} : savePrefFloat -> value : $value")
        prefEditor.putFloat(key, value).apply()
    }

    override fun savePrefInt(key: String, value: Int) {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : savePrefInt -> key   : $key")
        showLogDebug("${PreferenceDelegatesImpl.TAG} : savePrefInt -> value : $value")
        prefEditor.putInt(key, value).apply()
    }

    override fun savePrefString(key: String, value: String) {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : savePrefString -> key   : $key")
        showLogDebug("${PreferenceDelegatesImpl.TAG} : savePrefString -> value : $value")
        prefEditor.putString(key, value).apply()
    }

    override fun savePrefBoolean(key: String, value: Boolean) {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : savePrefBoolean -> key   : $key")
        showLogDebug("${PreferenceDelegatesImpl.TAG} : savePrefBoolean -> value : $value")
        prefEditor.putBoolean(key, value).apply()
    }

    override fun savePrefLong(key: String, value: Long) {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : savePrefLong -> key   : $key")
        showLogDebug("${PreferenceDelegatesImpl.TAG} : savePrefLong -> value : $value")
        prefEditor.putLong(key, value).apply()
    }

    override fun deletePref(key: String) {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : deletePref -> key : $key")
        prefEditor.remove(key).apply()
    }

    override fun nukePref() {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : nukePref")
        prefEditor.clear().apply()
    }

    override fun loadPrefFloat(key: String): Float {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : loadPrefFloat -> key   : $key")
        showLogDebug(
            "${PreferenceDelegatesImpl.TAG} : loadPrefFloat -> value : ${
                sharedPreferences.getFloat(
                    key,
                    0f
                )
            }"
        )
        return sharedPreferences.getFloat(key, 0f)
    }

    override fun loadPrefString(key: String): String {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : loadPrefString -> key   : $key")
        showLogDebug(
            "${PreferenceDelegatesImpl.TAG} : loadPrefString -> value : ${
                sharedPreferences.getString(
                    key,
                    ""
                )
            }"
        )
        return sharedPreferences.getString(key, "")!!
    }

    override fun loadPrefInt(key: String): Int {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : loadPrefInt -> key   : $key")
        showLogDebug(
            "${PreferenceDelegatesImpl.TAG} : loadPrefInt -> value : ${
                sharedPreferences.getInt(
                    key,
                    0
                )
            }"
        )
        return sharedPreferences.getInt(key, 0)
    }

    override fun loadPrefLong(key: String): Long {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : loadPrefLong -> key   : $key")
        showLogDebug(
            "${PreferenceDelegatesImpl.TAG} : loadPrefLong -> value : ${
                sharedPreferences.getLong(
                    key,
                    0
                )
            }"
        )
        return sharedPreferences.getLong(key, 0)
    }

    override fun loadPrefBoolean(key: String): Boolean {
        showLogDebug("${PreferenceDelegatesImpl.TAG} : loadPrefBoolean -> key   : $key")
        showLogDebug(
            "${PreferenceDelegatesImpl.TAG} : loadPrefBoolean -> value : ${
                sharedPreferences.getBoolean(
                    key,
                    false
                )
            }"
        )
        return sharedPreferences.getBoolean(key, false)
    }

    // Please Add Your Code Under This Line --------------------------------------------------------

}