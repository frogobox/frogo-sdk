package com.frogobox.sdk.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.frogobox.sdk.delegate.piracy.PiracyDelegates
import com.frogobox.sdk.delegate.piracy.PiracyDelegatesImpl
import com.frogobox.sdk.delegate.preference.PreferenceDelegates
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.delegate.util.UtilDelegates
import com.frogobox.sdk.delegate.util.UtilDelegatesImpl
import com.frogobox.sdk.delegate.view.ViewDelegates
import com.frogobox.sdk.delegate.view.ViewDelegatesImpl
import com.frogobox.sdk.ext.*

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
abstract class FrogoFragment<VB : ViewBinding> : Fragment(),
    IFrogoFragment,
    PiracyDelegates by PiracyDelegatesImpl(),
    PreferenceDelegates by PreferenceDelegatesImpl(),
    ViewDelegates by ViewDelegatesImpl(),
    UtilDelegates by UtilDelegatesImpl() {

    companion object {
        val TAG: String = FrogoFragment::class.java.simpleName
    }

    private var _binding: VB? = null

    protected val binding: VB get() = _binding!!

    protected val frogoActivity: FrogoActivity<*> by lazy {
        (activity as FrogoActivity<*>)
    }

    // ---------------------------------------------------------------------------------------------

    abstract fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun setupOnViewCreated(view: View, savedInstanceState: Bundle?)

    // ---------------------------------------------------------------------------------------------

    open fun setupViewModel() {}

    // ---------------------------------------------------------------------------------------------

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = setupViewBinding(inflater, container)
        if (savedInstanceState == null) {
            setupPreferenceDelegates(requireContext())
            setupPiracyDelegate(requireContext())
            setupViewDelegates(requireContext())
            setupUtilDelegates(requireContext())
            showLogDebug("$TAG : View Binding : ${binding::class.java.simpleName}")
            showLogDebug("$TAG : Internet Status : ${context?.isNetworkConnected()}")
        }
        setupViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            showLogDebug("$TAG : Overriding on ViewCreated")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        showLogDebug("$TAG : Destroying View Binding")
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
        singleNewInstance(argsKey, data)
    }

    protected inline fun <reified Model> frogoGetInstance(argsKey: String): Model {
        return singleGetInstance(argsKey)
    }

    protected inline fun <reified ClassActivity> frogoStartActivity() {
        context?.singleStartActivity<ClassActivity>()
    }

    protected inline fun <reified ClassActivity, reified Model> frogoStartActivity(
        extraKey: String,
        data: Model
    ) {
        context?.singleStartActivity<ClassActivity, Model>(extraKey, data)
    }

}