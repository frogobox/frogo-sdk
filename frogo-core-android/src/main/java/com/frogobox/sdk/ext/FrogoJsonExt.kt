package com.frogobox.sdk.ext

import android.content.Context
import com.google.gson.reflect.TypeToken
import java.io.IOException

/**
 * Created by faisalamircs on 26/04/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 */


fun Context.fetchRawData(
    sourceRaw: Int,
    shuffle: Boolean = false,
): MutableList<String> {
    val data = runCatching {
        resources.openRawResource(sourceRaw).bufferedReader().use { reader ->
            reader.readLines().toMutableList()
        }
    }.getOrDefault(mutableListOf())

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