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

fun ImageView.glideLoad(data: Any?) {
    when (data) {
        is String -> {
            Glide.with(context)
              .load(data)
              .into(this)
        }
        is Int -> {
            Glide.with(context)
              .load(data)
              .into(this)
        }
        is ByteArray -> {
            Glide.with(context)
              .load(data)
              .into(this)
        }
        else -> {
            throw Exception("Error Data Type")
        }
    }
}

fun ImageView.glideLoad(data: Any?, placeHolder: Int) {
    when (data) {
        is String -> {
            Glide.with(context)
             .load(data)
             .placeholder(placeHolder)
             .into(this)
        }
        is Int -> {
            Glide.with(context)
             .load(data)
             .placeholder(placeHolder)
             .into(this)
        }
        is ByteArray -> {
            Glide.with(context)
             .load(data)
             .placeholder(placeHolder)
             .into(this)
        }
        else -> {
            throw Exception("Error Data Type")
        }
    }
}