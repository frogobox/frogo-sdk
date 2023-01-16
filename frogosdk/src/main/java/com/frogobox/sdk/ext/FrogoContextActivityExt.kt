package com.frogobox.sdk.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.gson.Gson


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

private const val TAG = "FrogoContextActivityExt"

inline fun <reified ClassActivity> Context.startActivityExt() {
    startActivity(Intent(this, ClassActivity::class.java))
}

inline fun <reified ClassActivity> Context.startActivityExt(onIntent: (intent: Intent) -> Unit) {
    startActivity(Intent(this, ClassActivity::class.java).apply {
        onIntent(this)
    })
}

inline fun <reified ClassActivity, reified Model> Context.startActivityExt(
    extraKey: String,
    data: Model
) {
    val intent = Intent(this, ClassActivity::class.java)
    val extraData = Gson().toJson(data)
    intent.putExtra(extraKey, extraData)
    startActivity(intent)
}

inline fun <reified Model> Activity.getExtraDataExt(extraKey: String): Model {
    val extraIntent = intent.getStringExtra(extraKey)
    return Gson().fromJson(extraIntent, Model::class.java)
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
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

// -------------------------------------------------------------------------------------------------