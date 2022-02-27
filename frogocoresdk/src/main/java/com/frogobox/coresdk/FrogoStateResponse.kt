package com.frogobox.coresdk


/*
 * Created by faisalamir on 27/02/22
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

interface FrogoStateResponse {
    fun onSuccess()
    fun onFailed(statusCode: Int, errorMessage: String? = "")
    fun onShowProgress()
    fun onHideProgress()
}