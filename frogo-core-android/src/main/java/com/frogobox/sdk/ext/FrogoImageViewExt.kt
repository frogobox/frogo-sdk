package com.frogobox.sdk.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


/**
 * Created by faisalamir on 06/04/22
 * FrogoUI
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.
 * All rights reserved
 *
 */

// -------------------------------------------------------------------------------------------------

fun ImageView.setImageExt(uri: Any?, placeHolder: Int? = null) {
    val isValidString = (uri as? String)?.isNotBlank() ?: true
    if (uri != null && isValidString) {
        val request = Glide.with(context).load(uri)
        if (placeHolder != null) {
            request.placeholder(placeHolder).error(placeHolder)
        }
        request.into(this)
    } else {
        if (placeHolder != null) {
            Glide.with(context)
                .load(placeHolder)
                .into(this)
        } else {
            Glide.with(context).clear(this)
        }
    }
}

fun ImageView.setImageCompressExt(
    uri: Any?,
    placeHolder: Int? = null,
    diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.AUTOMATIC
) {
    val isValidString = (uri as? String)?.isNotBlank() ?: true
    if (uri != null && isValidString) {
        val w = if (this.width > 0) this.width else Target.SIZE_ORIGINAL
        val h = if (this.height > 0) this.height else Target.SIZE_ORIGINAL
        val option = RequestOptions()
            .override(w, h)
            .diskCacheStrategy(diskCacheStrategy)

        val request = Glide.with(context)
            .asBitmap()
            .apply(option)
            .load(uri)

        if (placeHolder != null) {
            request.placeholder(placeHolder).error(placeHolder)
        }
        request.into(this)
    } else {
        if (placeHolder != null) {
            Glide.with(context)
                .load(placeHolder)
                .into(this)
        } else {
            Glide.with(context).clear(this)
        }
    }
}