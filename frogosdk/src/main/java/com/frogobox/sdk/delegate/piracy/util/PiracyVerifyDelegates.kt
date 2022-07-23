package com.frogobox.sdk.delegate.piracy.util

import com.frogobox.sdk.delegate.piracy.FrogoPiracyCallback

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


interface PiracyVerifyDelegates {

    fun readSignature()

    fun showApkSignatures()

    fun verifySignature()

    fun verifySignature(callback: FrogoPiracyCallback)

    fun verifyInstallerId()

    fun verifyInstallerId(callback: FrogoPiracyCallback)

    fun verifyUnauthorizedApps()

    fun verifyUnauthorizedApps(callback: FrogoPiracyCallback)

    fun verifyStores()

    fun verifyStores(callback: FrogoPiracyCallback)

    fun verifyDebug()

    fun verifyDebug(callback: FrogoPiracyCallback)

    fun verifyEmulator()

    fun verifyEmulator(callback: FrogoPiracyCallback)

}