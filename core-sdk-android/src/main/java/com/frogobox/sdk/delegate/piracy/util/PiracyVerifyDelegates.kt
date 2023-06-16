package com.frogobox.sdk.delegate.piracy.util

import com.frogobox.sdk.delegate.piracy.FrogoPiracyCallback

/**
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


interface PiracyVerifyDelegates {

    fun readSignature()

    fun showApkSignatures()

    fun verifySignature(callback: FrogoPiracyCallback? = null)

    fun verifyInstallerId(callback: FrogoPiracyCallback? = null)

    fun verifyUnauthorizedApps(callback: FrogoPiracyCallback? = null)

    fun verifyStores(callback: FrogoPiracyCallback? = null)

    fun verifyDebug(callback: FrogoPiracyCallback? = null)

    fun verifyEmulator(callback: FrogoPiracyCallback? = null)

}