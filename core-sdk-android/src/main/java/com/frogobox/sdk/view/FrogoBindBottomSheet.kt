package com.frogobox.sdk.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding


/**
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

abstract class FrogoBindBottomSheet<VB : ViewBinding> : FrogoBottomSheet() {

    companion object {
        val TAG: String = FrogoBindBottomSheet::class.java.simpleName
    }

    private var _binding: VB? = null

    protected val binding: VB get() = _binding!!

    abstract fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = setupViewBinding(inflater, container)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}