package com.frogobox.sdk.util

import android.content.Context

/**
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

    fun randomNumber(start: Int, end: Int): Int

    fun isNetworkConnected(context: Context): Boolean

    fun waitingMoment(delay: Long, listener: () -> Unit)

}