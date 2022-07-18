package com.frogobox.sdk.util

import android.content.Context

/*
 * Created by faisalamir on 28/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
interface IFrogoFunc {

    fun createFolderPictureVideo()

    fun getVideoFilePath(): String

    fun createDialogDefault(
        context: Context,
        title: String,
        message: String,
        listenerYes: () -> Unit,
        listenerNo: () -> Unit
    )

    fun createDialogDefault(
        context: Context,
        title: String,
        message: String,
        listenerYes: () -> Unit
    )

    fun noAction(): Boolean

    fun randomNumber(start: Int, end: Int): Int

    fun isNetworkConnected(context: Context): Boolean

    fun <T> fetchRawData(mContext: Context, sourceRaw: Int): ArrayList<T>

    fun <T> fetchRawData(mContext: Context, sourceRaw: Int, shuffle: Boolean): ArrayList<T>

    fun getJsonFromAsset(context: Context, filename: String): String?

    fun getDrawableString(context: Context, nameResource: String): Int

    fun getRawString(context: Context, nameResource: String): Int

    fun waitingMoment(delay: Long, listener: () -> Unit)

}