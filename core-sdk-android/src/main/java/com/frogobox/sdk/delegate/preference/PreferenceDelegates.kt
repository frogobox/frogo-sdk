package com.frogobox.sdk.delegate.preference

import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse


/**
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

interface PreferenceDelegates {

    fun savePrefFloat(key: String, value: Float)

    fun savePrefInt(key: String, value: Int)

    fun savePrefString(key: String, value: String)

    fun savePrefBoolean(key: String, value: Boolean)

    fun savePrefLong(key: String, value: Long)

    fun savePrefString(key: String, value: String, callback: FrogoStateResponse)

    fun savePrefLong(key: String, value: Long, callback: FrogoStateResponse)

    fun savePrefFloat(key: String, value: Float, callback: FrogoStateResponse)

    fun savePrefInt(key: String, value: Int, callback: FrogoStateResponse)

    fun savePrefBoolean(key: String, value: Boolean, callback: FrogoStateResponse)

    fun deletePref(key: String)

    fun nukePref()

    fun loadPrefFloat(key: String): Float

    fun loadPrefFloat(key: String, defaultValue: Float): Float

    fun loadPrefString(key: String): String

    fun loadPrefString(key: String, defaultValue: String): String

    fun loadPrefInt(key: String): Int

    fun loadPrefInt(key: String, defaultValue: Int): Int

    fun loadPrefLong(key: String): Long

    fun loadPrefLong(key: String, defaultValue: Long): Long

    fun loadPrefBoolean(key: String): Boolean

    fun loadPrefBoolean(key: String, defaultValue: Boolean): Boolean

    fun loadPrefString(key: String, callback: FrogoDataResponse<String>)

    fun loadPrefLong(key: String, callback: FrogoDataResponse<Long>)

    fun loadPrefFloat(key: String, callback: FrogoDataResponse<Float>)

    fun loadPrefInt(key: String, callback: FrogoDataResponse<Int>)

    fun loadPrefBoolean(key: String, callback: FrogoDataResponse<Boolean>)

}