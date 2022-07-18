package com.frogobox.sdk.util

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Environment
import android.os.Handler
import com.frogobox.coresdk.util.FrogoConstant
import com.frogobox.sdk.R
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type

/*
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

    val TAG = FrogoFunc::class.java.simpleName

    private const val BASE_FILE_NAME = "SPEECH_"
    private const val BASE_DIR_NAME = "BaseMusicPlayer"

    val DIR_NAME = "${Environment.DIRECTORY_PICTURES}/$BASE_DIR_NAME"
    val VIDEO_FILE_NAME = "$BASE_FILE_NAME${System.currentTimeMillis()}${FrogoConstant.Ext.MP4}"

    override fun createFolderPictureVideo() {
        val videoFolder = Environment.getExternalStoragePublicDirectory(DIR_NAME)
        if (!videoFolder.exists()) {
            videoFolder.mkdirs()
        }
    }

    override fun getVideoFilePath(): String {
        val dir = Environment.getExternalStoragePublicDirectory(DIR_NAME)
        return if (dir == null) {
            VIDEO_FILE_NAME
        } else {
            "${dir.absoluteFile}/$VIDEO_FILE_NAME"
        }
    }

    override fun createDialogDefault(
        context: Context,
        title: String,
        message: String,
        listenerYes: () -> Unit,
        listenerNo: () -> Unit
    ) {
        val dialogBuilder = AlertDialog.Builder(context)
        // set message of alert dialog
        dialogBuilder.setMessage(message)
            // if the dialog is cancelable
            .setCancelable(false)
            .setPositiveButton(context.getText(R.string.dialog_button_yes)) { dialog, id ->
                // positive button text and action
                listenerYes()
            }
            .setNegativeButton(context.getText(R.string.dialog_button_no)) { dialog, id ->
                // negative button text and action
                dialog.cancel()
                listenerNo()
            }
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(title)
        // show alert dialog
        alert.show()
    }

    override fun createDialogDefault(
        context: Context,
        title: String,
        message: String,
        listenerYes: () -> Unit
    ) {
        val dialogBuilder = AlertDialog.Builder(context)
        // set message of alert dialog
        dialogBuilder.setMessage(message)
            // if the dialog is cancelable
            .setCancelable(false)
            .setPositiveButton("Close") { dialog, id ->
                // positive button text and action
                listenerYes()
                dialog.dismiss()
            }
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(title)
        // show alert dialog
        alert.show()
    }

    override fun noAction(): Boolean {
        return false
    }

    override fun isNetworkConnected(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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

    override fun <T> fetchRawData(mContext: Context, sourceRaw: Int): ArrayList<T> {
        val dataArrayList = ArrayList<T>()
        val rawDict = mContext.resources.openRawResource(sourceRaw)
        val reader = BufferedReader(InputStreamReader(rawDict))
        try {
            var column: T
            while (reader.readLine().also { column = it as T } != null) {
                dataArrayList.add(column)
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataArrayList
    }

    override fun <T> fetchRawData(
        mContext: Context,
        sourceRaw: Int,
        shuffle: Boolean
    ): ArrayList<T> {
        val dataArrayList = ArrayList<T>()
        val rawDict = mContext.resources.openRawResource(sourceRaw)
        val reader = BufferedReader(InputStreamReader(rawDict))
        try {
            var column: T
            while (reader.readLine().also { column = it as T } != null) {
                dataArrayList.add(column)
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        dataArrayList.shuffle()
        return dataArrayList
    }

    override fun getJsonFromAsset(context: Context, filename: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    inline fun <reified T> getParseArray(json: String?, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson(json, typeToken)
    }

    inline fun <reified T> getArrayFromJsonAsset(
        context: Context,
        filename: String
    ): MutableList<T> {
        val listData = mutableListOf<T>()
        val rawJson = getJsonFromAsset(context, filename)
        val typeToken = object : TypeToken<List<T>>() {}.type
        val data: List<T> = getParseArray(rawJson, typeToken)
        listData.addAll(data)
        return listData
    }

    override fun getDrawableString(context: Context, nameResource: String): Int {
        return context.resources.getIdentifier(nameResource, "drawable", context.packageName)
    }

    override fun getRawString(context: Context, nameResource: String): Int {
        return context.resources.getIdentifier(nameResource, "raw", context.packageName)
    }

    override fun randomNumber(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

    override fun waitingMoment(delay: Long, listener: () -> Unit) {
        Handler().postDelayed({ listener() }, delay)
    }

}