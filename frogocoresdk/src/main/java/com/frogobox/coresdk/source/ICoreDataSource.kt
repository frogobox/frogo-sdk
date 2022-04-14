package com.frogobox.coresdk.source

import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.Disposable


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

    fun savePrefString(key: String, value: String, callback: FrogoStateResponse)
    fun savePrefLong(key: String, value: Long, callback: FrogoStateResponse)
    fun savePrefFloat(key: String, value: Float, callback: FrogoStateResponse)
    fun savePrefInt(key: String, value: Int, callback: FrogoStateResponse)
    fun savePrefBoolean(key: String, value: Boolean, callback: FrogoStateResponse)

    // Delete
    fun deletePref(key: String)
    fun deletePref(key: String, callback: FrogoStateResponse)

    // Nuke
    fun nukePref()
    fun nukePref(callback: FrogoStateResponse)

    // Get
    fun getPrefString(key: String): String
    fun getPrefLong(key: String): Long
    fun getPrefFloat(key: String): Float
    fun getPrefInt(key: String): Int
    fun getPrefBoolean(key: String): Boolean

    fun getPrefString(key: String, callback: FrogoDataResponse<String>)
    fun getPrefLong(key: String, callback: FrogoDataResponse<Long>)
    fun getPrefFloat(key: String, callback: FrogoDataResponse<Float>)
    fun getPrefInt(key: String, callback: FrogoDataResponse<Int>)
    fun getPrefBoolean(key: String, callback: FrogoDataResponse<Boolean>)



}