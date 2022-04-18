package com.frogobox.sdk.view

import android.os.Bundle
import com.frogobox.sdk.ext.isNetworkConnected
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.view.piracycheck.FrogoComponentPiracyActivity

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
abstract class FrogoComposeActivity : FrogoComponentPiracyActivity(), IFrogoComposeActivity {

    companion object {
        val TAG: String = FrogoComposeActivity::class.java.simpleName
    }

    open fun setupViewModel() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        if (savedInstanceState == null) {
            showLogDebug("$TAG Internet Status : ${isNetworkConnected()}")
        }
    }

}