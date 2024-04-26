package com.frogobox.coresdk.source

import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import io.reactivex.rxjava3.disposables.Disposable


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

interface ICoreDataSource {

    fun onClearDisposables()
    fun addSubscribe(disposable: Disposable)

    // ---------------------------------------------------------------------------------------------

    // Preference
    // Save
    fun savePrefString(key: String, value: String)
    fun savePrefLong(key: String, value: Long)
    fun savePrefFloat(key: String, value: Float)
    fun savePrefInt(key: String, value: Int)
    fun savePrefBoolean(key: String, value: Boolean)

    // Delete
    fun deletePref(key: String)

    // Nuke
    fun nukePref()

    // Get
    fun getPrefString(key: String): String
    fun getPrefLong(key: String): Long
    fun getPrefFloat(key: String): Float
    fun getPrefInt(key: String): Int
    fun getPrefBoolean(key: String): Boolean

    fun getPrefString(key: String, defaultValue: String): String
    fun getPrefLong(key: String, defaultValue: Long): Long
    fun getPrefFloat(key: String, defaultValue: Float): Float
    fun getPrefInt(key: String, defaultValue: Int): Int
    fun getPrefBoolean(key: String, defaultValue: Boolean): Boolean

}