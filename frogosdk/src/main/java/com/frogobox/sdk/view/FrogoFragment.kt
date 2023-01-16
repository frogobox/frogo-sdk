package com.frogobox.sdk.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.frogobox.sdk.R
import com.frogobox.sdk.delegate.piracy.PiracyDelegates
import com.frogobox.sdk.delegate.piracy.PiracyDelegatesImpl
import com.frogobox.sdk.delegate.util.DateDelegates
import com.frogobox.sdk.delegate.util.DateDelegatesImpl
import com.frogobox.sdk.delegate.util.UtilDelegates
import com.frogobox.sdk.delegate.util.UtilDelegatesImpl
import com.frogobox.sdk.delegate.view.ViewDelegates
import com.frogobox.sdk.delegate.view.ViewDelegatesImpl
import com.frogobox.sdk.ext.*
import java.util.*

/**
 * Created by faisalamir on 28/07/21
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

abstract class FrogoFragment : Fragment(),
    IFrogoFragment,
    ViewDelegates by ViewDelegatesImpl(),
    UtilDelegates by UtilDelegatesImpl(),
    DateDelegates by DateDelegatesImpl(),
    PiracyDelegates by PiracyDelegatesImpl() {

    companion object {
        val TAG: String = FrogoFragment::class.java.simpleName
    }

    protected val frogoActivity: FrogoActivity by lazy {
        (activity as FrogoActivity)
    }

    protected val textCopyright: String by lazy {
        "${getString(R.string.about_all_right_reserved)} ${getString(R.string.about_copyright)} ${
            Calendar.getInstance().get(Calendar.YEAR)
        }"
    }

    open fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        showLogD<FrogoFragment>("Call onViewCreatedExt()")
    }

    open fun setupViewModel() {
        showLogD<FrogoFragment>("Call setupViewModel()")
    }

    open fun setupDelegates() {
        showLogD<FrogoFragment>("Call setupDelegates()")
        setupViewDelegates(requireContext())
        setupUtilDelegates(requireContext())
        setupPiracyDelegate(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setupDelegates()
            showLogDebug("$TAG : Internet Status : ${requireContext().isNetworkConnected()}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        onViewCreatedExt(view, savedInstanceState)
    }

    // ---------------------------------------------------------------------------------------------

    override fun setupChildFragment(frameId: Int, fragment: Fragment) {
        childFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            commit()
        }
    }

    override fun checkArgument(argsKey: String): Boolean {
        return requireArguments().containsKey(argsKey)
    }

}