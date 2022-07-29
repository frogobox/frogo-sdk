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

/*
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

    // ---------------------------------------------------------------------------------------------

    @Deprecated("Use onViewCreatedExt instead", ReplaceWith("onViewCreatedExt"))
    open fun setupOnViewCreated(view: View, savedInstanceState: Bundle?) {
    }

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
            showLogDebug("$TAG : Internet Status : ${requireContext().isNetworkConnected()}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            showLogDebug("$TAG : Overriding on ViewCreated")
        }
        setupViewModel()
        setupOnViewCreated(view, savedInstanceState)
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

    override fun <Model> frogoNewInstance(argsKey: String, data: Model) {
        newInstanceExt(argsKey, data)
    }

    protected inline fun <reified Model> frogoGetInstance(argsKey: String): Model {
        return getInstanceExt(argsKey)
    }

    protected inline fun <reified ClassActivity> frogoStartActivity() {
        requireContext().startActivityExt<ClassActivity>()
    }

    protected inline fun <reified ClassActivity, reified Model> frogoStartActivity(
        extraKey: String,
        data: Model
    ) {
        requireContext().startActivityExt<ClassActivity, Model>(extraKey, data)
    }

}