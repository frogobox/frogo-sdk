package com.frogobox.sdk.view

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding


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
abstract class FrogoBindActivity<VB : ViewBinding> : FrogoActivity() {

    companion object {
        val TAG: String = FrogoBindActivity::class.java.simpleName
    }

    protected val binding: VB by lazy { setupViewBinding() }

    abstract fun setupViewBinding(): VB

    override fun setupContentView() {
        super.setupContentView()
        setContentView(binding.root)
    }

    override fun setupSetOnApplyWindowInsetsListener() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}