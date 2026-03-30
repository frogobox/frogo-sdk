package com.frogobox.sdk.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

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

abstract class FrogoFragment : Fragment() {

    companion object {
        val TAG: String = FrogoFragment::class.java.simpleName
    }

    protected val frogoActivity: FrogoActivity?
        get() = activity as? FrogoActivity

    open fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {}
    open fun setupViewModel() {}
    open fun setupDelegates() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDelegates()
        setupViewModel()
        onViewCreatedExt(view, savedInstanceState)
    }

    // ---------------------------------------------------------------------------------------------

    fun setupChildFragment(frameId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
        childFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            if (addToBackStack) addToBackStack(fragment::class.java.simpleName)
            commit()
        }
    }

    fun checkArgument(argsKey: String): Boolean {
        return arguments?.containsKey(argsKey) == true
    }

}