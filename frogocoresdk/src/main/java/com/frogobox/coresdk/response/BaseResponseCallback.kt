package com.frogobox.coresdk.response


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

interface BaseResponseCallback {
    fun onFailed(statusCode: Int, errorMessage: String = "")
    fun onFinish()
    fun onHideProgress()
    fun onShowProgress()
}