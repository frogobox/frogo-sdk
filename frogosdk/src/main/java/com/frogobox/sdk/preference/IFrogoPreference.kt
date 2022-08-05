package com.frogobox.sdk.preference


/*
 * Created by faisalamir on 07/04/22
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

interface IFrogoPreference {

    fun savePrefFloat(key: String, value: Float)

    fun savePrefInt(key: String, value: Int)

    fun savePrefString(key: String, value: String)

    fun savePrefBoolean(key: String, value: Boolean)

    fun savePrefLong(key: String, value: Long)

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

}