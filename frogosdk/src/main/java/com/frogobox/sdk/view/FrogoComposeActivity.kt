package com.frogobox.sdk.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.frogobox.sdk.delegate.piracy.PiracyDelegates
import com.frogobox.sdk.delegate.piracy.PiracyDelegatesImpl
import com.frogobox.sdk.delegate.util.UtilDelegates
import com.frogobox.sdk.delegate.util.UtilDelegatesImpl
import com.frogobox.sdk.ext.showLogDebug

/*
 * Created by faisalamir on 23/08/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */
abstract class FrogoComposeActivity : ComponentActivity(),
    IFrogoComposeActivity,
    UtilDelegates by UtilDelegatesImpl(),
    PiracyDelegates by PiracyDelegatesImpl() {

    companion object {
        val TAG: String = FrogoComposeActivity::class.java.simpleName
    }

    open fun setupViewModel() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        if (savedInstanceState == null) {
            setupPiracyDelegate(this)
            showLogDebug("$TAG Internet Status : ${isNetworkConnected()}")
        }
    }

}

