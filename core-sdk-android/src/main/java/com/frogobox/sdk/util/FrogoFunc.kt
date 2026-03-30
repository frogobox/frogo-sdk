package com.frogobox.sdk.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Environment
import android.os.Handler

/**
 * Created by faisalamir on 26/07/21
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

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // Indicates this network uses an Ethernet transport
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }



}