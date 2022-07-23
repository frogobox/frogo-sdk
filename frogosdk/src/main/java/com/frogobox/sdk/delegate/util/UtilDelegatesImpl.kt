package com.frogobox.sdk.delegate.util

import android.content.Context
import com.frogobox.coresdk.util.FrogoConstant
import com.frogobox.sdk.ext.isNetworkConnected
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.ext.startActivityExtOpenApp
import com.frogobox.sdk.ext.startActivityExtShareApp


/*
 * Created by faisalamir on 02/07/22
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

class UtilDelegatesImpl : UtilDelegates {

    companion object {
        val TAG: String = UtilDelegatesImpl::class.java.simpleName
    }

    private lateinit var utilDelegatesContext: Context

    override fun setupUtilDelegates(context: Context) {
        showLogD<UtilDelegatesImpl>("Context : $context")
        utilDelegatesContext = context
    }

    override fun isNetworkConnected(): Boolean {
        return utilDelegatesContext.isNetworkConnected()
    }

    override fun shareApp(packageName: String, appName: String) {
        utilDelegatesContext.startActivityExtShareApp(
            packageName,
            "Hi, let's play $appName. Download now on Google Play! ${FrogoConstant.Url.BASE_PLAY_STORE_URL}$packageName"
        )
    }

    override fun rateApp(packageName: String) {
        utilDelegatesContext.startActivityExtOpenApp("${FrogoConstant.Url.BASE_PLAY_STORE_URL}$packageName")
    }

    override fun openPlaystore(packageName: String) {
        utilDelegatesContext.startActivityExtOpenApp("${FrogoConstant.Url.BASE_PLAY_STORE_URL}$packageName")
    }

}