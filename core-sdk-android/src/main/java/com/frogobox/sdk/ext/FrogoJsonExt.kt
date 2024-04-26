package com.frogobox.sdk.ext

import android.content.Context
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by faisalamircs on 26/04/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


fun <T> Context.fetchRawData(
    sourceRaw: Int,
    shuffle: Boolean = false,
): MutableList<T> {
    val data = mutableListOf<T>()
    val rawDict = resources.openRawResource(sourceRaw)
    val reader = BufferedReader(InputStreamReader(rawDict))
    try {
        var column: T
        while (reader.readLine().also { column = it as T } != null) {
            data.add(column)
        }
        reader.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    if (shuffle) {
        data.shuffle()
    }
    return data
}

fun Context.getStringJsonFromAsset(filename: String): String {
    var jsonString = ""
    try {
        jsonString = assets.open(filename).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return ""
    }
    return jsonString
}

inline fun <reified T> Context.getDataFromJsonAsset(fileName: String): List<T> {
    val typeToken = object : TypeToken<List<T>>() {}.type
    return getStringJsonFromAsset(fileName).toModel(typeToken)
}