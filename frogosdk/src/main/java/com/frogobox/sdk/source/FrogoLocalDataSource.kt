package com.frogobox.sdk.source

import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.coresdk.source.CoreDataSource
import com.frogobox.sdk.ext.executePreference
import com.frogobox.sdk.ext.fetchPreference
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.preference.FrogoPreference
import com.frogobox.sdk.util.AppExecutors
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


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
        Completable
            .fromAction { preferences.savePrefString(key, value) }
            .executePreference(callback)
    }

    override fun savePrefLong(key: String, value: Long, callback: FrogoStateResponse) {
        Completable
            .fromAction { preferences.savePrefLong(key, value) }
            .executePreference(callback)
    }

    override fun savePrefFloat(key: String, value: Float, callback: FrogoStateResponse) {
        Completable
            .fromAction { preferences.savePrefFloat(key, value) }
            .executePreference(callback)
    }

    override fun savePrefInt(key: String, value: Int, callback: FrogoStateResponse) {
        Completable
            .fromAction { preferences.savePrefInt(key, value) }
            .executePreference(callback)
    }

    override fun savePrefBoolean(key: String, value: Boolean, callback: FrogoStateResponse) {
        Completable
            .fromAction { preferences.savePrefBoolean(key, value) }
            .executePreference(callback)
    }

    override fun deletePref(key: String) {
        preferences.deletePref(key)
    }

    override fun deletePref(key: String, callback: FrogoStateResponse) {
        Completable
            .fromAction { preferences.deletePref(key) }
            .executePreference(callback)
    }

    override fun nukePref() {
        preferences.nukePref()
    }

    override fun nukePref(callback: FrogoStateResponse) {
        Completable
            .fromAction { preferences.nukePref() }
            .executePreference(callback)
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
        Observable.just(preferences.loadPrefString(key)).fetchPreference(callback)
    }

    override fun getPrefLong(key: String, callback: FrogoDataResponse<Long>) {
        Observable.just(preferences.loadPrefLong(key)).fetchPreference(callback)
    }

    override fun getPrefFloat(key: String, callback: FrogoDataResponse<Float>) {
        Observable.just(preferences.loadPrefFloat(key)).fetchPreference(callback)
    }

    override fun getPrefInt(key: String, callback: FrogoDataResponse<Int>) {
        Observable.just(preferences.loadPrefInt(key)).fetchPreference(callback)
    }

    override fun getPrefBoolean(key: String, callback: FrogoDataResponse<Boolean>) {
        Observable.just(preferences.loadPrefBoolean(key)).fetchPreference(callback)
    }

}