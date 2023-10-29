package com.frogobox.sdk.ext

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Build
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.pm.PackageInfoCompat
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.frogobox.coresdk.util.FrogoConstant
import com.frogobox.sdk.log.FLog
import com.frogobox.sdk.util.FrogoFunc
import okhttp3.Interceptor


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

private const val TAG = "FrogoContextExt"

// -------------------------------------------------------------------------------------------------

fun Context.getAppVersionCode(): Int? {
    try {
        return packageManager?.getPackageInfo(packageName, 0)
            ?.let { PackageInfoCompat.getLongVersionCode(it).toInt() }
    } catch (ex: Exception) {
        FLog.e("$TAG : ${ex.message.orEmpty()}")
    }

    return null
}

// -------------------------------------------------------------------------------------------------

fun Context.showToast(
    message: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(this, message, duration).show()
}

// -------------------------------------------------------------------------------------------------

fun Context.hasCameraPermission(): Boolean =
    ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

// -------------------------------------------------------------------------------------------------

fun Context.hasReadExtStoragePermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
}

fun Context.hasWriteExtStoragePermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
}

// -------------------------------------------------------------------------------------------------

fun Context.usingChuck(): Interceptor {
    return ChuckerInterceptor.Builder(this)
        .collector(ChuckerCollector(this))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(false)
        .build()
}

// -------------------------------------------------------------------------------------------------


inline fun <reified ClassActivity> showLogD(message: String) {
    FLog.d("${ClassActivity::class.java.simpleName} : $message")
}

inline fun <reified ClassActivity> showLogE(message: String) {
    FLog.e("${ClassActivity::class.java.simpleName} : $message")
}

inline fun <reified ClassActivity> showLogI(message: String) {
    FLog.i("${ClassActivity::class.java.simpleName} : $message")
}

fun showLogDebug(message: String) {
    FLog.d("$FROGO_SDK_TAG : $message")
}

fun showLogError(message: String) {
    FLog.e("$FROGO_SDK_TAG : $message")
}

fun showLogInfo(message: String) {
    FLog.i("$FROGO_SDK_TAG : $message")
}

// -------------------------------------------------------------------------------------------------

fun Context.isNetworkConnected(): Boolean {
    return FrogoFunc.isNetworkConnected(this)
}

// -------------------------------------------------------------------------------------------------

fun Context.getResColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

// -------------------------------------------------------------------------------------------------

fun Context.getResDrawable(@DrawableRes drawable: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawable)
}

fun Context.singleGetSharedPreferences(name: String): SharedPreferences {
    return getSharedPreferences(name, Context.MODE_PRIVATE)
}

fun Context.getInstallerId(): String? {
    return packageManager.getInstallerPackageName(packageName)
}

fun Context.createMediaPlayer(resId: Int): MediaPlayer {
    return MediaPlayer.create(this, resId)
}

fun Context.openPlayStore(packageName: String) {
    startActivityExtOpenApp("${FrogoConstant.Url.BASE_PLAY_STORE_URL}$packageName")
}

fun Context.shareApp(packageName: String, text: String) {
    startActivityExtShareApp(packageName, text)
}

fun Context.getColorExt(resId: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.getColor(resId)
    } else {
        ContextCompat.getColor(this, resId)
    }
}