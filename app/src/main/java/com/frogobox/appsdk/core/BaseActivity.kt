package com.frogobox.appsdk.core

import androidx.viewbinding.ViewBinding
import com.frogobox.appsdk.FrogoApp
import com.frogobox.sdk.delegate.preference.PreferenceDelegates
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.delegate.util.UtilDelegates
import com.frogobox.sdk.delegate.util.UtilDelegatesImpl
import com.frogobox.sdk.view.FrogoBindActivity

/*
 * Created by faisalamir on 02/08/21
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
abstract class BaseActivity<VB : ViewBinding> : FrogoBindActivity<VB>(),
    UtilDelegates by UtilDelegatesImpl(),
    PreferenceDelegates by PreferenceDelegatesImpl(FrogoApp.getContext(), "ANJAYY") {

    override fun setupDelegates() {
        super.setupDelegates()
        setupUtilDelegates(this)
    }

}