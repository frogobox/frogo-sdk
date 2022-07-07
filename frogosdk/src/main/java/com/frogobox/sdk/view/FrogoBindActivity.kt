package com.frogobox.sdk.view

import android.os.Bundle
import androidx.viewbinding.ViewBinding


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
abstract class FrogoBindActivity<VB : ViewBinding> : FrogoActivity() {

    companion object {
        val TAG: String = FrogoBindActivity::class.java.simpleName
    }

    protected val binding: VB by lazy { setupViewBinding() }

    abstract fun setupViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}