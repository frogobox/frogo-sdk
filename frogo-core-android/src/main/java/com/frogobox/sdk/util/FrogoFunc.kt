package com.frogobox.sdk.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Environment
import android.os.Handler

/**
 * Created by faisalamir on 26/07/21
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

object FrogoFunc : IFrogoFunc {

    private const val BASE_FILE_NAME = "SPEECH_"
    private const val BASE_DIR_NAME = "BaseMusicPlayer"

    val DIR_NAME = "${Environment.DIRECTORY_PICTURES}/$BASE_DIR_NAME"

    fun generateVideoFileName(): String = "$BASE_FILE_NAME${System.currentTimeMillis()}.mp4"

    
    override fun createFolderPictureVideo() {
        val videoFolder = Environment.getExternalStoragePublicDirectory(DIR_NAME)
        if (!videoFolder.exists()) {
            videoFolder.mkdirs()
        }
    }

    
    override fun getVideoFilePath(): String {
        val fileName = generateVideoFileName()
        val dir = Environment.getExternalStoragePublicDirectory(DIR_NAME)
        return if (dir == null) {
            fileName
        } else {
            "${dir.absoluteFile}/$fileName"
        }
    }

    override fun randomNumber(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

    override fun waitingMoment(delay: Long, listener: () -> Unit) {
        Handler(android.os.Looper.getMainLooper()).postDelayed({ listener() }, delay)
    }

    override fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager ?: return false

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }



}