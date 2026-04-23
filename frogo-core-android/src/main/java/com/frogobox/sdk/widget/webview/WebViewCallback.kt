package com.frogobox.sdk.widget.webview


/**
 * Created by faisalamir on 19/07/22
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

interface WebViewCallback {

    fun onShowProgress()

    fun onHideProgress()

    fun onFinish()

    fun onFailed()

}