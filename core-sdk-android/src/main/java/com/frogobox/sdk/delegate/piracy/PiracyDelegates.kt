package com.frogobox.sdk.delegate.piracy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.sdk.delegate.piracy.util.PiracyCheckRootDelegates
import com.frogobox.sdk.delegate.piracy.util.PiracyMessage
import com.frogobox.sdk.delegate.piracy.util.PiracyVerifyDelegates


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

    fun connectPiracyChecker(callback: FrogoPiracyCallback? = null)

    fun showPiracedDialog(message: PiracyMessage, callback: FrogoPiracyDialogCallback? = null)

    fun piracyMessage(isEmulator: Boolean = false) : PiracyMessage

}