package com.frogobox.sdk.ext

import android.view.View
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.frogobox.sdk.util.FrogoPagerHelper2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


/*
 * Created by faisalamir on 06/04/22
 * FrogoUI
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

private const val TAG = "FrogoViewExt"

// -------------------------------------------------------------------------------------------------

fun View.visible() {
    showLogDebug("$TAG : View Visible")
    visibility = View.VISIBLE
}

// -------------------------------------------------------------------------------------------------

fun View.gone() {
    showLogDebug("$TAG : View Gone")
    visibility = View.GONE
}

// -------------------------------------------------------------------------------------------------

fun View.invisible() {
    showLogDebug("$TAG : View Invisible")
    visibility = View.INVISIBLE
}

// -------------------------------------------------------------------------------------------------

fun View.progressViewHandle(isProgressState: Boolean) {
    showLogDebug("$TAG : isProgressState >> $isProgressState")
    if (isProgressState) {
        visible()
    } else {
        gone()
    }
}

// -------------------------------------------------------------------------------------------------

fun View.emptyViewHandle(isEmptyState: Boolean) {
    showLogDebug("$TAG : isEmptyState >> $isEmptyState")
    if (isEmptyState) {
        visible()
    } else {
        gone()
    }
}

// -------------------------------------------------------------------------------------------------

fun View.errorViewHandle(isErrorState: Boolean) {
    showLogDebug("$TAG : isErrorState >> $isErrorState")
    if (isErrorState) {
        visible()
    } else {
        gone()
    }
}

// -------------------------------------------------------------------------------------------------

fun View.networkViewHandle() {
    showLogDebug("$TAG : isNetworkState >> ${this.context.isNetworkConnected()}")
    if (this.context.isNetworkConnected()) {
        gone()
    } else {
        visible()
    }
}