package com.frogobox.sdk.delegate.piracy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity


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

interface PiracyDelegates : PiracyVerifyDelegates, PiracyCheckRootDelegates {

    fun isEmulator(): Boolean

    fun setupPiracyDelegate(context: Context, activity: AppCompatActivity? = null)

    fun setupPiracyDelegatesDebug(isDebug: Boolean)

    fun connectPiracyChecker(callback: PiracyCallback? = null)

    fun showPiracedDialog(message: PiracyMessage, callback: PiracyCallback? = null)

    fun piracyMessage(isEmulator: Boolean = false) : PiracyMessage

}