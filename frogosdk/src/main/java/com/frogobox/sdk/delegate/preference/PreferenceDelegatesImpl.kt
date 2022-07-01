package com.frogobox.sdk.delegate.preference

import android.content.Context
import android.content.SharedPreferences
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.ext.singleGetSharedPreferences
import com.frogobox.sdk.preference.FrogoPreference


/*
 * Created by faisalamir on 01/07/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

class PreferenceDelegatesImpl : PreferenceDelegates {

    companion object {
        val TAG: String = FrogoPreference::class.java.simpleName
    }

    private lateinit var preferenceDelegatesContext: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var prefEditor : SharedPreferences.Editor

    override fun setupPreferenceDelegates(context: Context, prefName: String) {
        showLogD<PreferenceDelegatesImpl>("Context : $context")
        showLogD<PreferenceDelegates>("Pref Name : $prefName")
        preferenceDelegatesContext = context
        preferenceDelegatesContext.singleGetSharedPreferences(prefName)
        prefEditor = sharedPreferences.edit()
    }

    override fun savePrefFloat(key: String, value: Float) {
        showLogDebug("$TAG : savePrefFloat -> key   : $key")
        showLogDebug("$TAG : savePrefFloat -> value : $value")
        prefEditor.putFloat(key, value).apply()
    }

    override fun savePrefInt(key: String, value: Int) {
        showLogDebug("$TAG : savePrefInt -> key   : $key")
        showLogDebug("$TAG : savePrefInt -> value : $value")
        prefEditor.putInt(key, value).apply()
    }

    override fun savePrefString(key: String, value: String) {
        showLogDebug("$TAG : savePrefString -> key   : $key")
        showLogDebug("$TAG : savePrefString -> value : $value")
        prefEditor.putString(key, value).apply()
    }

    override fun savePrefBoolean(key: String, value: Boolean) {
        showLogDebug("$TAG : savePrefBoolean -> key   : $key")
        showLogDebug("$TAG : savePrefBoolean -> value : $value")
        prefEditor.putBoolean(key, value).apply()
    }

    override fun savePrefLong(key: String, value: Long) {
        showLogDebug("$TAG : savePrefLong -> key   : $key")
        showLogDebug("$TAG : savePrefLong -> value : $value")
        prefEditor.putLong(key, value).apply()
    }

    override fun deletePref(key: String) {
        showLogDebug("$TAG : deletePref -> key : $key")
        prefEditor.remove(key).apply()
    }

    override fun nukePref() {
        showLogDebug("$TAG : nukePref")
        prefEditor.clear().apply()
    }

    override fun loadPrefFloat(key: String): Float {
        showLogDebug("$TAG : loadPrefFloat -> key   : $key")
        showLogDebug("$TAG : loadPrefFloat -> value : ${sharedPreferences.getFloat(key, 0f)}")
        return sharedPreferences.getFloat(key, 0f)
    }

    override fun loadPrefString(key: String): String {
        showLogDebug("$TAG : loadPrefString -> key   : $key")
        showLogDebug("$TAG : loadPrefString -> value : ${sharedPreferences.getString(key, "")}")
        return sharedPreferences.getString(key, "")!!
    }

    override fun loadPrefInt(key: String): Int {
        showLogDebug("$TAG : loadPrefInt -> key   : $key")
        showLogDebug("$TAG : loadPrefInt -> value : ${sharedPreferences.getInt(key, 0)}")
        return sharedPreferences.getInt(key, 0)
    }

    override fun loadPrefLong(key: String): Long {
        showLogDebug("$TAG : loadPrefLong -> key   : $key")
        showLogDebug("$TAG : loadPrefLong -> value : ${sharedPreferences.getLong(key, 0)}")
        return sharedPreferences.getLong(key, 0)
    }

    override fun loadPrefBoolean(key: String): Boolean {
        showLogDebug("$TAG : loadPrefBoolean -> key   : $key")
        showLogDebug("$TAG : loadPrefBoolean -> value : ${sharedPreferences.getBoolean(key, false)}")
        return sharedPreferences.getBoolean(key, false)
    }

}