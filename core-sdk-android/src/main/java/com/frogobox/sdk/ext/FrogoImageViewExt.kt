package com.frogobox.sdk.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


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

// -------------------------------------------------------------------------------------------------

fun ImageView.setImageExt(uri: Any?, placeHolder: Int? = null) {
    if (uri != null) {
        when (uri) {
            is String,
            is Int,
            is ByteArray,
            -> {
                if (placeHolder != null) {
                    Glide.with(context)
                        .load(uri)
                        .placeholder(placeHolder)
                        .into(this)
                } else {
                    Glide.with(context)
                        .load(uri)
                        .into(this)
                }
            }
        }
    } else {
        if (placeHolder != null) {
            Glide.with(context)
                .load("")
                .placeholder(placeHolder)
                .into(this)
        } else {
            Glide.with(context)
                .load("")
                .into(this)
        }
    }
}

fun ImageView.setImageCompressExt(uri: Any?, placeHolder: Int? = null) {
    if (uri != null) {
        when (uri) {
            is String,
            is Int,
            is ByteArray,
            -> {
                val option = RequestOptions()
                    .override(this.width, this.height)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)

                if (placeHolder != null) {
                    Glide.with(context)
                        .asBitmap()
                        .apply(option)
                        .placeholder(placeHolder)
                        .load(uri)
                        .into(this)
                } else {
                    Glide.with(context)
                        .asBitmap()
                        .apply(option)
                        .load(uri)
                        .into(this)
                }
            }

            else -> {
                Glide.with(context)
                    .asBitmap()
                    .load("")
                    .into(this)
            }
        }
    } else {
        if (placeHolder != null) {
            Glide.with(context)
                .load("")
                .placeholder(placeHolder)
                .into(this)
        } else {
            Glide.with(context)
                .load("")
                .into(this)
        }
    }
}