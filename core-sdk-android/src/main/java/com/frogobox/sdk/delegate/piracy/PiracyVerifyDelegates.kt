package com.frogobox.sdk.delegate.piracy

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

    fun verifySignature(callback: PiracyCallback? = null)

    fun verifyInstallerId(callback: PiracyCallback? = null)

    fun verifyUnauthorizedApps(callback: PiracyCallback? = null)

    fun verifyStores(callback: PiracyCallback? = null)

    fun verifyDebug(callback: PiracyCallback? = null)

    fun verifyEmulator(callback: PiracyCallback? = null)

}