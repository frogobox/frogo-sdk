package com.frogobox.appsdk.core

import androidx.viewbinding.ViewBinding
import com.frogobox.sdk.delegate.preference.PreferenceDelegates
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.view.FrogoBindActivity
import org.koin.android.ext.android.inject

/**
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

abstract class BaseActivity<VB : ViewBinding> : FrogoBindActivity<VB>() {

    protected val singlePref: PreferenceDelegates by inject<PreferenceDelegatesImpl>()

}