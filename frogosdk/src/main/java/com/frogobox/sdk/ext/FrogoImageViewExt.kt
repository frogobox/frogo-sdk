package com.frogobox.sdk.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
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

private const val TAG = "FrogoImageViewExt"

// -------------------------------------------------------------------------------------------------

fun ImageView.glideLoad(data: Any?) {
    showLogDebug("$TAG : Params : $data")
    showLogDebug("$TAG : Glide Load Ext")
    Glide.with(context).load(data).into(this)
}