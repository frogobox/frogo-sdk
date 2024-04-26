package com.frogobox.sdk.ext

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

const val FROGO_SDK_TAG = "FrogoSDK"

fun Any?.showLogD(tag: String = FROGO_SDK_TAG) {
    if (this != null) {
        android.util.Log.d(tag, this.toString())
    }
}

fun Any?.showLogE(tag: String = FROGO_SDK_TAG) {
    if (this != null) {
        android.util.Log.e(tag, this.toString())
    }
}

fun Any?.showLogI(tag: String = FROGO_SDK_TAG) {
    if (this != null) {
        android.util.Log.i(tag, this.toString())
    }
}

fun Any?.showLogV(tag: String = FROGO_SDK_TAG) {
    if (this != null) {
        android.util.Log.v(tag, this.toString())
    }
}

fun Any?.showLogW(tag: String = FROGO_SDK_TAG) {
    if (this != null) {
        android.util.Log.w(tag, this.toString())
    }
}

fun Any?.showLogWTF(tag: String = FROGO_SDK_TAG) {
    if (this != null) {
        android.util.Log.wtf(tag, this.toString())
    }
}

fun Any?.showLogD(message: String, tag: String = FROGO_SDK_TAG) {
    if (this != null) {
        android.util.Log.d(tag, "$message : $this")
    }
}

fun Any?.showLogE(message: String, tag: String = FROGO_SDK_TAG) {
    if (this != null) {
        android.util.Log.e(tag, "$message : $this")
    }
}

fun Any?.showLogI(message: String, tag: String = FROGO_SDK_TAG) {
    if (this != null) {
        android.util.Log.i(tag, "$message : $this")
    }
}

fun Any?.showLogV(message: String, tag: String = FROGO_SDK_TAG) {
    if (this != null) {
        android.util.Log.v(tag, "$message : $this")
    }
}

fun Any?.showLogW(message: String, tag: String = FROGO_SDK_TAG) {
    if (this != null) {
        android.util.Log.w(tag, "$message : $this")
    }
}

fun Any?.toJson() : String {
    return Gson().toJson(this)
}

inline fun <reified T> String.toModel(typeToken: Type? = null): T {
    val gson = GsonBuilder().create()
    return if (typeToken == null) {
        gson.fromJson(this, T::class.java)
    } else {
        gson.fromJson(this, typeToken)
    }
}