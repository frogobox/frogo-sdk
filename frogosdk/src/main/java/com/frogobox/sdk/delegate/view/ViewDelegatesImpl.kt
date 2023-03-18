package com.frogobox.sdk.delegate.view

import android.content.Context
import android.view.View
import android.widget.Toast
import com.frogobox.sdk.ext.emptyViewHandle
import com.frogobox.sdk.ext.progressViewHandle
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.ext.showToast


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

class ViewDelegatesImpl : ViewDelegates {

    companion object {
        val TAG: String = ViewDelegatesImpl::class.java.simpleName
    }

    private lateinit var viewDelegates: Context

    override fun setupViewDelegates(context: Context) {
        viewDelegates = context
    }

    override fun setupEmptyView(view: View, isEmpty: Boolean) {
        view.emptyViewHandle(isEmpty)
    }

    override fun setupProgressView(view: View, isProgress: Boolean) {
        view.progressViewHandle(isProgress)
    }

    override fun showToast(message: String) {
        viewDelegates.showToast(message, Toast.LENGTH_SHORT)
    }

}