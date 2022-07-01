package com.frogobox.sdk.delegate.view

import android.content.Context
import android.view.View


/*
 * Created by faisalamir on 02/07/22
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

interface ViewDelegates {

    fun setupViewDelegates(context: Context)

    fun setupEmptyView(view: View, isEmpty: Boolean)

    fun setupProgressView(view: View, isProgress: Boolean)

    fun showToast(message: String)

}