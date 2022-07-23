package com.frogobox.sdk.view

import androidx.viewbinding.ViewBinding
import com.frogobox.sdk.ext.showLogD


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

    override fun setupContentView() {
        super.setupContentView()
        showLogD<FrogoBindActivity<VB>>("Binding : $binding")
        setContentView(binding.root)
    }

}