package com.frogobox.sdk.source

import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.coresdk.source.CoreDataSource
import com.frogobox.sdk.ext.rxJavaCompletableFromAction
import com.frogobox.sdk.ext.rxJavaObservableSingleJust
import com.frogobox.sdk.preference.FrogoPreference
import com.frogobox.sdk.util.AppExecutors


/*
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
    private val preferences: FrogoPreference
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

    override fun savePrefString(key: String, value: String, callback: FrogoStateResponse) {
        rxJavaCompletableFromAction(callback) {
            preferences.savePrefString(key, value)
        }
    }

    override fun savePrefLong(key: String, value: Long, callback: FrogoStateResponse) {
        rxJavaCompletableFromAction(callback) {
            preferences.savePrefLong(key, value)
        }
    }

    override fun savePrefFloat(key: String, value: Float, callback: FrogoStateResponse) {
        rxJavaCompletableFromAction(callback) {
            preferences.savePrefFloat(key, value)
        }
    }

    override fun savePrefInt(key: String, value: Int, callback: FrogoStateResponse) {
        rxJavaCompletableFromAction(callback) {
            preferences.savePrefInt(key, value)
        }
    }

    override fun savePrefBoolean(key: String, value: Boolean, callback: FrogoStateResponse) {
        rxJavaCompletableFromAction(callback) {
            preferences.savePrefBoolean(key, value)
        }
    }

    override fun deletePref(key: String) {
        preferences.deletePref(key)
    }

    override fun deletePref(key: String, callback: FrogoStateResponse) {
        rxJavaCompletableFromAction(callback) {
            preferences.deletePref(key)
        }
    }

    override fun nukePref() {
        preferences.nukePref()
    }

    override fun nukePref(callback: FrogoStateResponse) {
        rxJavaCompletableFromAction(callback) {
            preferences.nukePref()
        }
    }

    override fun getPrefString(key: String): String {
        return preferences.loadPrefString(key)
    }

    override fun getPrefLong(key: String): Long {
        return preferences.loadPrefLong(key)
    }

    override fun getPrefFloat(key: String): Float {
        return preferences.loadPrefFloat(key)
    }

    override fun getPrefInt(key: String): Int {
        return preferences.loadPrefInt(key)
    }

    override fun getPrefBoolean(key: String): Boolean {
        return preferences.loadPrefBoolean(key)
    }

    override fun getPrefString(key: String, callback: FrogoDataResponse<String>) {
        rxJavaObservableSingleJust(preferences.loadPrefString(key), callback)
    }

    override fun getPrefLong(key: String, callback: FrogoDataResponse<Long>) {
        rxJavaObservableSingleJust(preferences.loadPrefLong(key), callback)
    }

    override fun getPrefFloat(key: String, callback: FrogoDataResponse<Float>) {
        rxJavaObservableSingleJust(preferences.loadPrefFloat(key), callback)
    }

    override fun getPrefInt(key: String, callback: FrogoDataResponse<Int>) {
        rxJavaObservableSingleJust(preferences.loadPrefInt(key), callback)
    }

    override fun getPrefBoolean(key: String, callback: FrogoDataResponse<Boolean>) {
        rxJavaObservableSingleJust(preferences.loadPrefBoolean(key), callback)
    }

}