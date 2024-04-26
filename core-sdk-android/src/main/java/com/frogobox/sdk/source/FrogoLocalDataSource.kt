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

    override fun savePrefString(key: String, value: String) {
        preferences.savePrefString(key, value)
    }

    override fun savePrefLong(key: String, value: Long) {
        preferences.savePrefLong(key, value)
    }

    override fun savePrefFloat(key: String, value: Float) {
        preferences.savePrefFloat(key, value)
    }

    override fun savePrefInt(key: String, value: Int) {
        preferences.savePrefInt(key, value)
    }

    override fun savePrefBoolean(key: String, value: Boolean) {
        preferences.savePrefBoolean(key, value)
    }

    override fun deletePref(key: String) {
        preferences.deletePref(key)
    }

    override fun nukePref() {
        preferences.nukePref()
    }

    override fun getPrefString(key: String): String {
        return preferences.getPrefString(key)
    }

    override fun getPrefLong(key: String): Long {
        return preferences.getPrefLong(key)
    }

    override fun getPrefFloat(key: String): Float {
        return preferences.getPrefFloat(key)
    }

    override fun getPrefInt(key: String): Int {
        return preferences.getPrefInt(key)
    }

    override fun getPrefBoolean(key: String): Boolean {
        return preferences.getPrefBoolean(key)
    }

    override fun getPrefString(key: String, defaultValue: String): String {
        return preferences.getPrefString(key, defaultValue)
    }

    override fun getPrefLong(key: String, defaultValue: Long): Long {
        return preferences.getPrefLong(key, defaultValue)
    }

    override fun getPrefFloat(key: String, defaultValue: Float): Float {
        return preferences.getPrefFloat(key, defaultValue)
    }

    override fun getPrefInt(key: String, defaultValue: Int): Int {
        return preferences.getPrefInt(key, defaultValue)
    }

    override fun getPrefBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getPrefBoolean(key, defaultValue)
    }
}