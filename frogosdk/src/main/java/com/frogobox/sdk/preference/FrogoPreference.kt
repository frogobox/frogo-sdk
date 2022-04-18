package com.frogobox.sdk.preference

import android.content.Context
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
        showLogDebug("$TAG : savePrefFloat")
        prefEditor.putFloat(key, value).apply()
    }

    override fun savePrefInt(key: String, value: Int) {
        showLogDebug("$TAG : savePrefInt")
        prefEditor.putInt(key, value).apply()
    }

    override fun savePrefString(key: String, value: String) {
        showLogDebug("$TAG : savePrefString")
        prefEditor.putString(key, value).apply()
    }

    override fun savePrefBoolean(key: String, value: Boolean) {
        showLogDebug("$TAG : savePrefBoolean")
        prefEditor.putBoolean(key, value).apply()
    }

    override fun savePrefLong(key: String, value: Long) {
        showLogDebug("$TAG : savePrefLong")
        prefEditor.putLong(key, value).apply()
    }

    override fun deletePref(key: String) {
        showLogDebug("$TAG : deletePref")
        prefEditor.remove(key).apply()
    }

    override fun nukePref() {
        showLogDebug("$TAG : nukePref")
        prefEditor.clear().apply()
    }

    override fun loadPrefFloat(key: String): Float {
        showLogDebug("$TAG : loadPrefFloat")
        return sharedPreferences.getFloat(key, 0f)
    }

    override fun loadPrefString(key: String): String {
        showLogDebug("$TAG : loadPrefString")
        return sharedPreferences.getString(key, "")!!
    }

    override fun loadPrefInt(key: String): Int {
        showLogDebug("$TAG : loadPrefInt")
        return sharedPreferences.getInt(key, 0)
    }

    override fun loadPrefLong(key: String): Long {
        showLogDebug("$TAG : loadPrefLong")
        return sharedPreferences.getLong(key, 0)
    }

    override fun loadPrefBoolean(key: String): Boolean {
        showLogDebug("$TAG : loadPrefBoolean")
        return sharedPreferences.getBoolean(key, false)
    }

    // Please Add Your Code Under This Line --------------------------------------------------------

}