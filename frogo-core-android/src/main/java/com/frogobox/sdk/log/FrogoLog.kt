package com.frogobox.sdk.log

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.frogobox.sdk.log.LogConstant.SIMPLE_MESSAGE

/**
 * Created by Faisal Amir on 14/01/2021
 * LogCat Source Code
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
object FrogoLog : ILog {

    var isLineNumberEnabled: Boolean = true

    // Tag for get class name this function
    private fun tag(): String {
        return this.javaClass.simpleName
    }

    // Function for get line number of code
    private fun lineNumber(): String {
        if (!isLineNumberEnabled) return ""
        val stackTrace = Thread.currentThread().stackTrace
        val index = 4
        if (index >= stackTrace.size) return "unknown"
        return stackTrace[index].let {
            "${it.className.substringAfterLast(".")}.${it.methodName}(${it.fileName}:${it.lineNumber})"
        }
    }

    private fun showToastSafe(context: Context, message: String) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        } else {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context.applicationContext, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    // Function Log Simple Debug without message params
    override fun d() {
        Log.d(tag(), "${lineNumber()}: $SIMPLE_MESSAGE")
    }

    // Function Log Debug
    override fun d(msg: String?) {
        Log.d(tag(), "${lineNumber()}: $msg")
    }

    // Function Log Simple Verbose without message params
    override fun v() {
        Log.v(tag(), "${lineNumber()}: $SIMPLE_MESSAGE")
    }

    // Function Log Verbose
    override fun v(msg: String?) {
        Log.v(tag(), "${lineNumber()}: $msg")
    }

    // Function Log Simple Info without message params
    override fun i() {
        Log.i(tag(), "${lineNumber()}: $SIMPLE_MESSAGE")
    }

    // Function Log Info
    override fun i(msg: String?) {
        Log.i(tag(), "${lineNumber()}: $msg")
    }

    // Function Log Simple Warn without message params
    override fun w() {
        Log.w(tag(), "${lineNumber()}: $SIMPLE_MESSAGE")
    }

    // Function Log Warn
    override fun w(msg: String?) {
        Log.w(tag(), "${lineNumber()}: $msg")
    }

    // Function Log Warn
    override fun w(e: Throwable?) {
        Log.w(tag(), "${lineNumber()}: ${e?.localizedMessage}")
    }

    // Function Log Warn
    override fun w(e: Exception?) {
        Log.w(tag(), "${lineNumber()}: ${e?.localizedMessage}")
    }

    // Function Log Simple Error without message params
    override fun e() {
        Log.e(tag(), "${lineNumber()}: $SIMPLE_MESSAGE")
    }

    // Function Log Error
    override fun e(msg: String?) {
        Log.e(tag(), "${lineNumber()}: $msg")
    }

    // ---------------------------------------------------------------------------------------------

    // Function Log Simple Debug without message params
    override fun d(context: Context) {
        Log.d(tag(), SIMPLE_MESSAGE)
        showToastSafe(context, SIMPLE_MESSAGE)
    }

    // Function Log Debug
    override fun d(msg: String?, context: Context) {
        Log.d(tag(), "${lineNumber()}: $msg")
        showToastSafe(context, "${lineNumber()}: $msg")
    }

    // Function Log Simple Verbose without message params
    override fun v(context: Context) {
        Log.v(tag(), SIMPLE_MESSAGE)
        showToastSafe(context, SIMPLE_MESSAGE)
    }

    // Function Log Verbose
    override fun v(msg: String?, context: Context) {
        Log.v(tag(), "${lineNumber()}: $msg")
        showToastSafe(context, "${lineNumber()}: $msg")
    }

    // Function Log Simple Info without message params
    override fun i(context: Context) {
        Log.i(tag(), SIMPLE_MESSAGE)
        showToastSafe(context, SIMPLE_MESSAGE)
    }

    // Function Log Info
    override fun i(msg: String?, context: Context) {
        Log.i(tag(), "${lineNumber()}: $msg")
        showToastSafe(context, "${lineNumber()}: $msg")
    }

    // Function Log Simple Warn without message params
    override fun w(context: Context) {
        Log.w(tag(), SIMPLE_MESSAGE)
        showToastSafe(context, SIMPLE_MESSAGE)
    }

    // Function Log Warn
    override fun w(msg: String?, context: Context) {
        Log.w(tag(), "${lineNumber()}: $msg")
        showToastSafe(context, "${lineNumber()}: $msg")
    }

    // Function Log Warn
    override fun w(e: Exception?, context: Context) {
        Log.w(tag(), "${lineNumber()}: ${e?.localizedMessage}")
        showToastSafe(context, "${lineNumber()}: ${e?.localizedMessage}")
    }

    // Function Log Warn
    override fun w(e: Throwable?, context: Context) {
        Log.w(tag(), "${lineNumber()}: ${e?.localizedMessage}")
        showToastSafe(context, "${lineNumber()}: ${e?.localizedMessage}")
    }

    // Function Log Simple Error without message params
    override fun e(context: Context) {
        Log.e(tag(), SIMPLE_MESSAGE)
        showToastSafe(context, SIMPLE_MESSAGE)
    }

    // Function Log Error
    override fun e(msg: String?, context: Context) {
        Log.e(tag(), "${lineNumber()}: $msg")
        showToastSafe(context, "${lineNumber()}: $msg")
    }

}