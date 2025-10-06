package com.frogobox.sdk.source

import com.frogobox.coresdk.source.CoreDataSource
import com.frogobox.sdk.delegate.preference.PreferenceDelegates
import com.frogobox.sdk.util.AppExecutors


/**
 * Created by faisalamir on 08/04/22
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

open class FrogoLocalDataSource(
    private val appExecutors: AppExecutors,
    private val preferences: PreferenceDelegates
) : CoreDataSource() {

     fun savePrefString(key: String, value: String) {
        preferences.savePrefString(key, value)
    }

     fun savePrefLong(key: String, value: Long) {
        preferences.savePrefLong(key, value)
    }

     fun savePrefFloat(key: String, value: Float) {
        preferences.savePrefFloat(key, value)
    }

     fun savePrefInt(key: String, value: Int) {
        preferences.savePrefInt(key, value)
    }

     fun savePrefBoolean(key: String, value: Boolean) {
        preferences.savePrefBoolean(key, value)
    }

     fun deletePref(key: String) {
        preferences.deletePref(key)
    }

     fun nukePref() {
        preferences.nukePref()
    }

     fun getPrefString(key: String): String {
        return preferences.getPrefString(key)
    }

     fun getPrefLong(key: String): Long {
        return preferences.getPrefLong(key)
    }

     fun getPrefFloat(key: String): Float {
        return preferences.getPrefFloat(key)
    }

     fun getPrefInt(key: String): Int {
        return preferences.getPrefInt(key)
    }

     fun getPrefBoolean(key: String): Boolean {
        return preferences.getPrefBoolean(key)
    }

     fun getPrefString(key: String, defaultValue: String): String {
        return preferences.getPrefString(key, defaultValue)
    }

     fun getPrefLong(key: String, defaultValue: Long): Long {
        return preferences.getPrefLong(key, defaultValue)
    }

     fun getPrefFloat(key: String, defaultValue: Float): Float {
        return preferences.getPrefFloat(key, defaultValue)
    }

     fun getPrefInt(key: String, defaultValue: Int): Int {
        return preferences.getPrefInt(key, defaultValue)
    }

     fun getPrefBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getPrefBoolean(key, defaultValue)
    }
}