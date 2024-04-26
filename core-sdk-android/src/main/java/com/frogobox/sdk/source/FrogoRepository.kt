package com.frogobox.sdk.source

import com.frogobox.coresdk.source.CoreDataSource


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

open class FrogoRepository(
    private val remoteDataSource: CoreDataSource,
    private val localDataSource: CoreDataSource
) : CoreDataSource() {

    override fun savePrefString(key: String, value: String) {
        localDataSource.savePrefString(key, value)
    }

    override fun savePrefLong(key: String, value: Long) {
        localDataSource.savePrefLong(key, value)
    }

    override fun savePrefFloat(key: String, value: Float) {
        localDataSource.savePrefFloat(key, value)
    }

    override fun savePrefInt(key: String, value: Int) {
        localDataSource.savePrefInt(key, value)
    }

    override fun savePrefBoolean(key: String, value: Boolean) {
        localDataSource.savePrefBoolean(key, value)
    }

    override fun deletePref(key: String) {
        localDataSource.deletePref(key)
    }

    override fun nukePref() {
        localDataSource.nukePref()
    }

    override fun getPrefString(key: String): String {
        return localDataSource.getPrefString(key)
    }

    override fun getPrefLong(key: String): Long {
        return localDataSource.getPrefLong(key)
    }

    override fun getPrefFloat(key: String): Float {
        return localDataSource.getPrefFloat(key)
    }

    override fun getPrefInt(key: String): Int {
        return localDataSource.getPrefInt(key)
    }

    override fun getPrefBoolean(key: String): Boolean {
        return localDataSource.getPrefBoolean(key)
    }

    override fun getPrefString(key: String, defaultValue: String): String {
        return localDataSource.getPrefString(key, defaultValue)
    }

    override fun getPrefLong(key: String, defaultValue: Long): Long {
        return localDataSource.getPrefLong(key, defaultValue)
    }

    override fun getPrefFloat(key: String, defaultValue: Float): Float {
        return localDataSource.getPrefFloat(key, defaultValue)
    }

    override fun getPrefInt(key: String, defaultValue: Int): Int {
        return localDataSource.getPrefInt(key, defaultValue)
    }

    override fun getPrefBoolean(key: String, defaultValue: Boolean): Boolean {
        return localDataSource.getPrefBoolean(key, defaultValue)
    }

}