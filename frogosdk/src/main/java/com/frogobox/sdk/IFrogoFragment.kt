package com.frogobox.sdk

import android.view.View
import androidx.fragment.app.Fragment

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
interface IFrogoFragment {

    fun setupChildFragment(frameId: Int, fragment: Fragment)

    fun checkArgument(argsKey: String): Boolean

    fun setupEmptyView(view: View, isEmpty: Boolean)

    fun setupProgressView(view: View, isProgress: Boolean)

    fun showToast(message: String)

    fun <Model> frogoNewInstance(argsKey: String, data: Model)

}