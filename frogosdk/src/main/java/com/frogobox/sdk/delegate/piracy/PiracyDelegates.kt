package com.frogobox.sdk.delegate.piracy

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

interface PiracyDelegates {

    fun setupPiracyDelegate(context: Context)

    fun verifySignature()

    fun readSignature()

    fun verifyInstallerId()

    fun verifyUnauthorizedApps()

    fun verifyStores()

    fun verifyDebug()

    fun verifyEmulator()

    fun showApkSignatures()

}