package com.frogobox.sdk.ext

import android.view.View
import com.frogobox.log.FLog


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
    FLog.d("$TAG : View Visible")
    visibility = View.VISIBLE
}

// -------------------------------------------------------------------------------------------------

fun View.gone() {
    FLog.d("$TAG : View Gone")
    visibility = View.GONE
}

// -------------------------------------------------------------------------------------------------

fun View.invisible() {
    FLog.d("$TAG : View Invisible")
    visibility = View.INVISIBLE
}

// -------------------------------------------------------------------------------------------------

fun View.progressViewHandle(isProgressState: Boolean) {
    FLog.d("$TAG : isProgressState >> $isProgressState")
    if (isProgressState) {
        visible()
    } else {
        gone()
    }
}

// -------------------------------------------------------------------------------------------------

fun View.emptyViewHandle(isEmptyState: Boolean) {
    FLog.d("$TAG : isEmptyState >> $isEmptyState")
    if (isEmptyState) {
        visible()
    } else {
        gone()
    }
}