package com.frogobox.sdk.delegate.piracy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity


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

interface PiracyDelegates : PiracyCheckRoot {

    fun setupPiracyDelegate(context: Context, activity: AppCompatActivity)

    fun setupPiracyDelegate(context: Context)

    fun setupPiracyDelegatesDebug(isDebug: Boolean)

    fun connectPiracyChecker()

    fun connectPiracyChecker(doIsEmulator: () -> Unit)

    fun isEmulator(): Boolean

    fun readSignature()

    fun verifySignature()

    fun verifyInstallerId()

    fun verifyUnauthorizedApps()

    fun verifyStores()

    fun verifyDebug()

    fun verifyEmulator()

    fun showApkSignatures()

}