package com.frogobox.sdk.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.sdk.ui.FrogoImageViewActivity
import com.google.gson.Gson
import androidx.core.net.toUri


/**
 * Created by faisalamir on 07/04/22
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

inline fun <reified ClassActivity> Context.startActivityExt() {
    startActivity(Intent(this, ClassActivity::class.java))
}

inline fun <reified ClassActivity> Context.startActivityExt(onIntent: (intent: Intent) -> Unit) {
    startActivity(Intent(this, ClassActivity::class.java).apply {
        onIntent(this)
    })
}

fun Activity.hasExtraExt(extraKey: String): Boolean {
    return intent.hasExtra(extraKey)
}

inline fun <reified T> Activity.getExtraExt(params: String): T? {
    return intent.getExtraExt(params)
}

inline fun <reified T> Intent.getExtraExt(params: String): T? {
    return if (this.hasExtra(params)) {
        return when (T::class) {
            String::class -> getStringExtra(params) as T
            Int::class -> getIntExtra(params, 0) as T
            Boolean::class -> getBooleanExtra(params, false) as T
            Double::class -> getDoubleExtra(params, 0.0) as T
            Float::class -> getFloatExtra(params, 0.0f) as T
            Long::class -> getLongExtra(params, 0L) as T
            T::class -> @Suppress("DEPRECATION") this.getParcelableExtra(params)
            else -> throw Exception("Type not found")
        }
    } else {
        null
    }
}

fun Activity.openDetailImageUri(uri: String) {
    startActivityExt<FrogoImageViewActivity> {
        it.putExtra(FrogoImageViewActivity.IMAGE_URI, uri)
    }
}

fun Context.startActivityExtShareApp(subject: String, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    startActivity(Intent.createChooser(intent, subject))
}

fun Context.startActivityExtOpenApp(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
}

// -------------------------------------------------------------------------------------------------