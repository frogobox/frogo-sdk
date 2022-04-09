package com.frogobox.sdk.source

import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.coresdk.source.CoreDataSource


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

open class FrogoRemoteDataSource : CoreDataSource() {

    override fun savePrefString(key: String, value: String) {}

    override fun savePrefString(key: String, value: String, callback: FrogoStateResponse) {}

    override fun savePrefLong(key: String, value: Long) {}

    override fun savePrefLong(key: String, value: Long, callback: FrogoStateResponse) {}

    override fun savePrefFloat(key: String, value: Float) {}

    override fun savePrefFloat(key: String, value: Float, callback: FrogoStateResponse) {}

    override fun savePrefInt(key: String, value: Int) {}

    override fun savePrefInt(key: String, value: Int, callback: FrogoStateResponse) {}

    override fun savePrefBoolean(key: String, value: Boolean) {}

    override fun savePrefBoolean(key: String, value: Boolean, callback: FrogoStateResponse) {}

    override fun deletePref(key: String) {}

    override fun deletePref(key: String, callback: FrogoStateResponse) {}

    override fun nukePref() {}

    override fun nukePref(callback: FrogoStateResponse) {}

    override fun getPrefString(key: String): String {
        return ""
    }

    override fun getPrefString(key: String, callback: FrogoDataResponse<String>) {}

    override fun getPrefLong(key: String): Long {
        return 0
    }

    override fun getPrefLong(key: String, callback: FrogoDataResponse<Long>) {}

    override fun getPrefFloat(key: String): Float {
        return 0f
    }

    override fun getPrefFloat(key: String, callback: FrogoDataResponse<Float>) {}

    override fun getPrefInt(key: String): Int {
        return 0
    }

    override fun getPrefInt(key: String, callback: FrogoDataResponse<Int>) {}

    override fun getPrefBoolean(key: String): Boolean {
        return false
    }

    override fun getPrefBoolean(key: String, callback: FrogoDataResponse<Boolean>) {}

}