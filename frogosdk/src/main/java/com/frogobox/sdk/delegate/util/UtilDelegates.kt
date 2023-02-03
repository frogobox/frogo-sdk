package com.frogobox.sdk.delegate.util

import android.content.Context


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

interface UtilDelegates {

    fun setupUtilDelegates(context: Context)

    fun shareApp(packageName: String, appName: String)

    fun rateApp(packageName: String)

    fun openPlaystore(packageName: String)

}