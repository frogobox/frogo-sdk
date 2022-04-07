package com.frogobox.sdk.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.frogobox.log.FLog
import com.google.gson.Gson


/*
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

// -------------------------------------------------------------------------------------------------

inline fun <reified ClassActivity> Context.singleStartActivity() {
    FLog.d("Activity : ${ClassActivity::class.java.simpleName}")
    startActivity(Intent(this, ClassActivity::class.java))
}

// -------------------------------------------------------------------------------------------------

inline fun <reified ClassActivity, reified Model> Context.singleStartActivity(
    extraKey: String,
    data: Model
) {
    val intent = Intent(this, ClassActivity::class.java)
    val extraData = Gson().toJson(data)
    intent.putExtra(extraKey, extraData)
    FLog.d("Activity : ${ClassActivity::class.java.simpleName}")
    FLog.d("Data     : Extra Data (${Model::class.java.simpleName}) : $extraData")
    startActivity(intent)
}

// -------------------------------------------------------------------------------------------------

inline fun <reified Model> Activity.singleGetExtraData(extraKey: String): Model {
    val extraIntent = intent.getStringExtra(extraKey)
    return Gson().fromJson(extraIntent, Model::class.java)
}

// -------------------------------------------------------------------------------------------------

fun Context.singleStartActivityShareApp(subject: String, text: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    intent.putExtra(Intent.EXTRA_TEXT, text)
    FLog.d("$TAG : Subject Share App : $subject")
    FLog.d("$TAG : Message Share App : $text")
    startActivity(Intent.createChooser(intent, subject))
}

// -------------------------------------------------------------------------------------------------

fun Context.singleStartActivityOpenApp(url: String) {
    FLog.d("$TAG : Url : $url")
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}