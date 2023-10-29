package com.frogobox.sdk.ext

import android.content.ClipData
import android.content.ClipboardManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


/**
 * Created by faisalamir on 18/04/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

// -------------------------------------------------------------------------------------------------

fun TextView.toText(): String {
    return this.text.toString()
}

fun TextView.copyToClipboard() {
    val cm: ClipboardManager = this.context.getSystemService(AppCompatActivity.CLIPBOARD_SERVICE) as ClipboardManager
    cm.setPrimaryClip(
        ClipData(
            "text",
            arrayOf("text/plain"),
            ClipData.Item(this.text)
        )
    )
}