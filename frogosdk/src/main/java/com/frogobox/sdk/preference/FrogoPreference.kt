package com.frogobox.sdk.preference

import android.content.Context
import com.frogobox.coresdk.response.FrogoStateResponse
import io.reactivex.rxjava3.core.Completable

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

class FrogoPreference(
    private val context: Context,
    private val prefName: String
) : IFrogoPreference {

    companion object {
        val TAG: String = FrogoPreference::class.java.simpleName
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    private val prefEditor by lazy {
        sharedPreferences.edit()
    }

    override fun savePrefFloat(
        key: String,
        value: Float
    ) {
        prefEditor.putFloat(key, value).apply()
    }

    override fun savePrefInt(key: String, value: Int) {
        prefEditor.putInt(key, value).apply()
    }

    override fun savePrefString(
        key: String,
        value: String
    ) {
        prefEditor.putString(key, value).apply()
    }

    override fun savePrefBoolean(
        key: String,
        value: Boolean
    ) {
        prefEditor.putBoolean(key, value).apply()
    }

    override fun savePrefLong(key: String, value: Long) {
        prefEditor.putLong(key, value).apply()
    }

    override fun deletePref(key: String) {
        prefEditor.remove(key).apply()
    }

    override fun nukePref() {
        prefEditor.clear().apply()
    }

    override fun loadPrefFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0f)
    }

    override fun loadPrefString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    override fun loadPrefInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    override fun loadPrefLong(key: String): Long {
        return sharedPreferences.getLong(key, 0)
    }

    override fun loadPrefBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    // Please Add Your Code Under This Line --------------------------------------------------------

}