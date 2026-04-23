package com.frogobox.ads.ui

import com.frogobox.ads.delegate.UnityAdDelegates
import com.frogobox.ads.delegate.UnityAdDelegatesImpl
import com.frogobox.sdk.view.FrogoActivity

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


abstract class FrogoUnityAdActivity : FrogoActivity(),
    UnityAdDelegates by UnityAdDelegatesImpl() {

    companion object {
        val TAG: String = FrogoUnityAdActivity::class.java.simpleName
    }

    override fun setupMonetized() {
        super.setupMonetized()
        setupUnityAdDelegates(this)
    }

}