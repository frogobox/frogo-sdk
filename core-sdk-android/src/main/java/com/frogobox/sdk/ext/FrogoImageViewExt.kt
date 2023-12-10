package com.frogobox.sdk.ext

import android.widget.ImageView
import com.bumptech.glide.Glide


/**
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

fun ImageView.setImageExt(uri: Any?) {
    uri?.let { url ->
        when (url) {
            is String,
            is Int,
            is ByteArray -> {
                Glide.with(context)
                    .load(url)
                    .into(this)
            }

            else -> {}
        }
    }
}

fun ImageView.setImageExt(uri: Any?, placeHolder: Int) {
    if (uri != null) {
        when (uri) {
            is String,
            is Int,
            is ByteArray -> {
                Glide.with(context)
                    .load(uri)
                    .placeholder(placeHolder)
                    .into(this)
            }
        }
    } else {
        Glide.with(context)
            .load("")
            .placeholder(placeHolder)
            .into(this)
    }
}