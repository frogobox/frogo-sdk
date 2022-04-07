package com.frogobox.sdk


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

    fun loadPrefString(key: String): String

    fun loadPrefInt(key: String): Int

    fun loadPrefLong(key: String): Long

    fun loadPrefBoolean(key: String): Boolean

}