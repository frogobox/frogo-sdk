package com.frogobox.sdk.delegate.preference


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

    fun deletePref(key: String)

    fun nukePref()

    fun getPrefFloat(key: String): Float

    fun getPrefFloat(key: String, defaultValue: Float): Float

    fun getPrefString(key: String): String

    fun getPrefString(key: String, defaultValue: String): String

    fun getPrefInt(key: String): Int

    fun getPrefInt(key: String, defaultValue: Int): Int

    fun getPrefLong(key: String): Long

    fun getPrefLong(key: String, defaultValue: Long): Long

    fun getPrefBoolean(key: String): Boolean

    fun getPrefBoolean(key: String, defaultValue: Boolean): Boolean

}