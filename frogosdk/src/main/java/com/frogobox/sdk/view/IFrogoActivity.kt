package com.frogobox.sdk.view

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
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
interface IFrogoActivity {

    fun setupDetailActivity(title: String)

    fun setupDetailActivity(
        title: String,
        @DrawableRes actionBackIcon: Int?
    )

    fun setupDetailActivity(
        title: String,
        @DrawableRes actionBackIcon: Int?,
        @ColorRes backgroundColor: Int?
    )

    fun setupChildFragment(frameId: Int, fragment: Fragment)

    fun checkExtra(extraKey: String): Boolean

    fun <Model> frogoFragmentNewInstance(
        fragment: FrogoFragment,
        argumentKey: String,
        extraDataResult: Model
    )

    fun setupFullScreen()

    fun setupHideSystemUI()

}