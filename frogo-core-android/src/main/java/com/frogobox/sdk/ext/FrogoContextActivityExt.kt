package com.frogobox.sdk.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.core.content.IntentCompat
import androidx.core.net.toUri
import com.frogobox.sdk.ui.FrogoImageViewActivity


/**
 * Created by faisalamir on 07/04/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

inline fun <reified ClassActivity> Context.startActivityExt() {
    val intent = Intent(this, ClassActivity::class.java).apply {
        if (this@startActivityExt !is Activity) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
    startActivity(intent)
}

inline fun <reified ClassActivity> Context.startActivityExt(onIntent: (intent: Intent) -> Unit) {
    val intent = Intent(this, ClassActivity::class.java).apply {
        if (this@startActivityExt !is Activity) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        onIntent(this)
    }
    startActivity(intent)
}

fun Activity.hasExtraExt(extraKey: String): Boolean {
    return intent.hasExtra(extraKey)
}

inline fun <reified T> Activity.getExtraExt(params: String): T? {
    return intent.getExtraExt(params)
}

inline fun <reified T> Intent.getExtraExt(params: String): T? {
    if (!this.hasExtra(params)) return null
    return when (T::class) {
        String::class -> getStringExtra(params) as? T
        Int::class -> getIntExtra(params, 0) as? T
        Boolean::class -> getBooleanExtra(params, false) as? T
        Double::class -> getDoubleExtra(params, 0.0) as? T
        Float::class -> getFloatExtra(params, 0.0f) as? T
        Long::class -> getLongExtra(params, 0L) as? T
        Short::class -> getShortExtra(params, 0) as? T
        Byte::class -> getByteExtra(params, 0) as? T
        Char::class -> getCharExtra(params, '\u0000') as? T
        CharSequence::class -> getCharSequenceExtra(params) as? T
        android.os.Bundle::class -> getBundleExtra(params) as? T
        ByteArray::class -> getByteArrayExtra(params) as? T
        IntArray::class -> getIntArrayExtra(params) as? T
        LongArray::class -> getLongArrayExtra(params) as? T
        FloatArray::class -> getFloatArrayExtra(params) as? T
        DoubleArray::class -> getDoubleArrayExtra(params) as? T
        BooleanArray::class -> getBooleanArrayExtra(params) as? T
        else -> {
            if (Parcelable::class.java.isAssignableFrom(T::class.java)) {
                @Suppress("UNCHECKED_CAST")
                IntentCompat.getParcelableExtra(this, params, T::class.java as Class<out Parcelable>) as? T
            } else if (java.io.Serializable::class.java.isAssignableFrom(T::class.java)) {
                @Suppress("UNCHECKED_CAST")
                IntentCompat.getSerializableExtra(this, params, T::class.java as Class<out java.io.Serializable>) as? T
            } else {
                null
            }
        }
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
    val chooser = Intent.createChooser(intent, subject).apply {
        if (this@startActivityExtShareApp !is Activity) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
    startActivity(chooser)
}

fun Context.startActivityExtOpenApp(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
        if (this@startActivityExtOpenApp !is Activity) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
    startActivity(intent)
}

// -------------------------------------------------------------------------------------------------