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

open class FrogoRemoteDataSource : CoreDataSource() {

    override fun savePrefString(key: String, value: String) {}

    override fun savePrefLong(key: String, value: Long) {}

    override fun savePrefFloat(key: String, value: Float) {}

    override fun savePrefInt(key: String, value: Int) {}

    override fun savePrefBoolean(key: String, value: Boolean) {}

    override fun deletePref(key: String) {}

    override fun nukePref() {}

    override fun getPrefString(key: String): String {
        return ""
    }

    override fun getPrefLong(key: String): Long {
        return 0
    }

    override fun getPrefFloat(key: String): Float {
        return 0f
    }

    override fun getPrefInt(key: String): Int {
        return 0
    }

    override fun getPrefBoolean(key: String): Boolean {
        return false
    }

    override fun getPrefString(key: String, defaultValue: String): String {
        return defaultValue
    }

    override fun getPrefLong(key: String, defaultValue: Long): Long {
        return defaultValue
    }

    override fun getPrefFloat(key: String, defaultValue: Float): Float {
        return defaultValue
    }

    override fun getPrefInt(key: String, defaultValue: Int): Int {
        return defaultValue
    }

    override fun getPrefBoolean(key: String, defaultValue: Boolean): Boolean {
        return defaultValue
    }

}