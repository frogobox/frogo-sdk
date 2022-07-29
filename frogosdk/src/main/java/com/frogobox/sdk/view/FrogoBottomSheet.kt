package com.frogobox.sdk.view

import android.os.Bundle
import android.view.View
import com.frogobox.sdk.delegate.piracy.PiracyDelegates
import com.frogobox.sdk.delegate.piracy.PiracyDelegatesImpl
import com.frogobox.sdk.delegate.util.DateDelegates
import com.frogobox.sdk.delegate.util.DateDelegatesImpl
import com.frogobox.sdk.delegate.util.UtilDelegates
import com.frogobox.sdk.delegate.util.UtilDelegatesImpl
import com.frogobox.sdk.delegate.view.ViewDelegates
import com.frogobox.sdk.delegate.view.ViewDelegatesImpl
import com.frogobox.sdk.ext.isNetworkConnected
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.ext.showLogDebug
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/*
 * Created by faisalamir on 19/07/22
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

abstract class FrogoBottomSheet : BottomSheetDialogFragment(),
    ViewDelegates by ViewDelegatesImpl(),
    UtilDelegates by UtilDelegatesImpl(),
    DateDelegates by DateDelegatesImpl(),
    PiracyDelegates by PiracyDelegatesImpl() {

    open fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        showLogD<FrogoFragment>("Call onViewCreatedExt()")
    }

    open fun setupViewModel() {
        showLogD<FrogoFragment>("Call setupViewModel()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setupPiracyDelegate(requireContext())
            setupViewDelegates(requireContext())
            setupUtilDelegates(requireContext())
            showLogDebug("${FrogoFragment.TAG} : Internet Status : ${requireContext().isNetworkConnected()}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            showLogDebug("${FrogoFragment.TAG} : Overriding on ViewCreated")
        }
        setupViewModel()
        onViewCreatedExt(view, savedInstanceState)
    }

}
