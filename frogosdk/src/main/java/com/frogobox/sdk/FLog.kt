package com.frogobox.sdk

import android.content.Context
import android.widget.Toast
import com.frogobox.coresdk.Log

/*
 * Created by Faisal Amir on 14/02/2021
 * LogCat Source Code
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
object FLog : IFLog {

    // Constant Variable Simple Message
    const val SIMPLE_MESSSAGE = "SIMPLE DEBUG FOR MARK LINE OF CODE"

    // Tag for get class name this function
    private fun tag() : String {
        return this.javaClass.simpleName
    }
    
    // Function Log Simple Debug without message params
    override fun d(){
        Log.d(tag(), SIMPLE_MESSSAGE)
    }

    // Function Log Debug
    override fun d(msg: String?) {
        Log.d(tag(), "$msg")
    }

    // Function Log Verbose
    override fun v(msg: String?) {
        Log.v(tag(), "$msg")
    }

    // Function Log Info
    override fun i(msg: String?) {
        Log.i(tag(), "$msg")
    }

    // Function Log Warn
    override fun w(msg: String?) {
        Log.w(tag(), "$msg")
    }

    // Function Log Warn
    override fun w(e: Throwable?) {
        Log.w(tag(), "${e?.localizedMessage}")
    }

    // Function Log Warn
    override fun w(e: Exception?) {
        Log.w(tag(), "${e?.localizedMessage}")
    }

    // Function Log Error
    override fun e(msg: String?) {
        Log.e(tag(), "$msg")
    }

    // ---------------------------------------------------------------------------------------------

    // Function Log Simple Debug without message params
    override fun d(context: Context) {
        Log.d(tag(), SIMPLE_MESSSAGE)
        Toast.makeText(context, SIMPLE_MESSSAGE, Toast.LENGTH_LONG).show()
    }

    // Function Log Debug
    override fun d(msg: String?, context: Context) {
        Log.d(tag(), "$msg")
        Toast.makeText(context, "$msg", Toast.LENGTH_LONG).show()
    }

    // Function Log Verbose
    override fun v(msg: String?, context: Context) {
        Log.v(tag(), "$msg")
        Toast.makeText(context, "$msg", Toast.LENGTH_LONG).show()
    }

    // Function Log Info
    override fun i(msg: String?, context: Context) {
        Log.i(tag(), "$msg")
        Toast.makeText(context, "$msg", Toast.LENGTH_LONG).show()
    }

    // Function Log Warn
    override fun w(msg: String?, context: Context) {
        Log.w(tag(), "$msg")
        Toast.makeText(context, "$msg", Toast.LENGTH_LONG).show()
    }

    // Function Log Warn
    override fun w(e: Exception?, context: Context) {
        Log.w(tag(), "${e?.localizedMessage}")
        Toast.makeText(context, "${e?.localizedMessage}", Toast.LENGTH_LONG).show()
    }

    // Function Log Warn
    override fun w(e: Throwable?, context: Context) {
        Log.w(tag(), "${e?.localizedMessage}")
        Toast.makeText(context, "${e?.localizedMessage}", Toast.LENGTH_LONG).show()
    }

    // Function Log Error
    override fun e(msg: String?, context: Context) {
        Log.e(tag(), "$msg")
        Toast.makeText(context, "$msg", Toast.LENGTH_LONG).show()
    }
    
}