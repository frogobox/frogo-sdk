package com.frogobox.sdk.delegate.preference

import android.content.Context


/*
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

    fun setupPreferenceDelegates(context: Context, prefName: String)

    fun setupPreferenceDelegates(context: Context)

    fun setupPreferenceDelegates(prefName: String)

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