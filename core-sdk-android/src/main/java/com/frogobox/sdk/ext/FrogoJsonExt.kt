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


fun Context.fetchRawData(
    sourceRaw: Int,
    shuffle: Boolean = false,
): MutableList<String> {
    val data = mutableListOf<String>()
    val rawDict = resources.openRawResource(sourceRaw)
    val reader = BufferedReader(InputStreamReader(rawDict))
    try {
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            line?.let { data.add(it) }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try { reader.close() } catch (e: IOException) { e.printStackTrace() }
    }
    if (shuffle) {
        data.shuffle()
    }
    return data
}

fun Context.getStringJsonFromAsset(filename: String): String {
    return try {
        assets.open(filename).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        ""
    }
}

inline fun <reified T> Context.getDataFromJsonAsset(fileName: String): List<T> {
    val json = getStringJsonFromAsset(fileName)
    if (json.isBlank()) return emptyList()
    val typeToken = object : TypeToken<List<T>>() {}.type
    return runCatching { json.toModel<List<T>>(typeToken) }.getOrDefault(emptyList())
}