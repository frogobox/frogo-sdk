package com.frogobox.sdk.util

import android.content.Context

/**
 * Created by faisalamir on 28/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */

interface IFrogoFunc {

    @Deprecated(
        "Direct access to public external storage is restricted on Android 10+ (API 29+). Use createAppSpecificFolderPictureVideo(context).",
        ReplaceWith("createAppSpecificFolderPictureVideo(context)")
    )
    fun createFolderPictureVideo()

    @Deprecated(
        "Direct access to public external storage is restricted on Android 10+ (API 29+). Use getAppSpecificVideoFilePath(context).",
        ReplaceWith("getAppSpecificVideoFilePath(context)")
    )
    fun getVideoFilePath(): String

    fun createAppSpecificFolderPictureVideo(context: Context)

    fun getAppSpecificVideoFilePath(context: Context): String


    fun randomNumber(start: Int, end: Int): Int

    fun isNetworkConnected(context: Context): Boolean

    fun waitingMoment(delay: Long, listener: () -> Unit)

}