package com.frogobox.sdk.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.frogobox.sdk.ui.FrogoImageViewActivity
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

inline fun <reified Model> Activity.getExtraExt(extraKey: String): Model {
    return when (Model::class) {
        String::class -> intent.getStringExtra(extraKey) as Model
        Int::class -> intent.getIntExtra(extraKey, 0) as Model
        Boolean::class -> intent.getBooleanExtra(extraKey, false) as Model
        Double::class -> intent.getDoubleExtra(extraKey, 0.0) as Model
        Float::class -> intent.getFloatExtra(extraKey, 0.0f) as Model
        Long::class -> intent.getLongExtra(extraKey, 0L) as Model
        Model::class -> Gson().fromJson(intent.getStringExtra(extraKey), Model::class.java)
        else -> throw Exception("Type not found")
    }
}

fun Activity.hasExtraExt(extraKey: String): Boolean {
    return intent.hasExtra(extraKey)
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
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

// -------------------------------------------------------------------------------------------------