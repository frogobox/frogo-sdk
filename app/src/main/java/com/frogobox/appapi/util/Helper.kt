package com.frogobox.appapi.util

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type

/*
 * Created by faisalamir on 28/07/21
 * Consumable
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
object Helper {

    fun getJsonFromAssets(context: Context, filename: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    inline fun <reified T> parseArray(json: String?, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson<T>(json, typeToken)
    }

    inline fun <reified T> listJsonFromAssets(context: Context, filename: String): MutableList<T> {
        val listData = mutableListOf<T>()
        val rawJson = getJsonFromAssets(context, filename)
        val typeToken = object : TypeToken<List<T>>() {}.type
        val data: List<T> = parseArray(rawJson, typeToken)
        listData.addAll(data)
        return listData
    }

}