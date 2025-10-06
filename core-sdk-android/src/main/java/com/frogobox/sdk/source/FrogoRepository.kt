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
    private val remoteDataSource: FrogoRemoteDataSource,
    private val localDataSource: FrogoLocalDataSource
) : CoreDataSource() {

     fun savePrefString(key: String, value: String) {
        localDataSource.savePrefString(key, value)
    }

     fun savePrefLong(key: String, value: Long) {
        localDataSource.savePrefLong(key, value)
    }

     fun savePrefFloat(key: String, value: Float) {
        localDataSource.savePrefFloat(key, value)
    }

     fun savePrefInt(key: String, value: Int) {
        localDataSource.savePrefInt(key, value)
    }

     fun savePrefBoolean(key: String, value: Boolean) {
        localDataSource.savePrefBoolean(key, value)
    }

     fun deletePref(key: String) {
        localDataSource.deletePref(key)
    }

     fun nukePref() {
        localDataSource.nukePref()
    }

     fun getPrefString(key: String): String {
        return localDataSource.getPrefString(key)
    }

     fun getPrefLong(key: String): Long {
        return localDataSource.getPrefLong(key)
    }

     fun getPrefFloat(key: String): Float {
        return localDataSource.getPrefFloat(key)
    }

     fun getPrefInt(key: String): Int {
        return localDataSource.getPrefInt(key)
    }

     fun getPrefBoolean(key: String): Boolean {
        return localDataSource.getPrefBoolean(key)
    }

     fun getPrefString(key: String, defaultValue: String): String {
        return localDataSource.getPrefString(key, defaultValue)
    }

     fun getPrefLong(key: String, defaultValue: Long): Long {
        return localDataSource.getPrefLong(key, defaultValue)
    }

     fun getPrefFloat(key: String, defaultValue: Float): Float {
        return localDataSource.getPrefFloat(key, defaultValue)
    }

     fun getPrefInt(key: String, defaultValue: Int): Int {
        return localDataSource.getPrefInt(key, defaultValue)
    }

     fun getPrefBoolean(key: String, defaultValue: Boolean): Boolean {
        return localDataSource.getPrefBoolean(key, defaultValue)
    }

}