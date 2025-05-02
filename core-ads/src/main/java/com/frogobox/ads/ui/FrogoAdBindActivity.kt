package com.frogobox.ads.ui

import androidx.viewbinding.ViewBinding
import com.frogobox.ads.delegate.AdmobDelegates
import com.frogobox.ads.delegate.AdmobDelegatesImpl
import com.frogobox.ads.delegate.FrogoAdDelegates
import com.frogobox.ads.delegate.FrogoAdDelegatesImpl
import com.frogobox.ads.delegate.UnityAdDelegates
import com.frogobox.ads.delegate.UnityAdDelegatesImpl
import com.frogobox.sdk.view.FrogoBindActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * ImplementationAdmob
 * Copyright (C) 31/10/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.admobhelper
 *
 */


abstract class FrogoAdBindActivity<VB : ViewBinding> : FrogoBindActivity<VB>(),
    AdmobDelegates by AdmobDelegatesImpl(),
    UnityAdDelegates by UnityAdDelegatesImpl(),
    FrogoAdDelegates by FrogoAdDelegatesImpl() {

    companion object {
        val TAG: String = FrogoAdBindActivity::class.java.simpleName
    }

    override fun setupDelegates() {
        super.setupDelegates()
        setupAdmobDelegates(this)
        setupUnityAdDelegates(this)
        setupFrogoAdDelegates(this)
    }

    override fun setupMonetized() {
        super.setupMonetized()
        CoroutineScope(Dispatchers.IO).launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            setupAdmobApp()
        }
    }

}

