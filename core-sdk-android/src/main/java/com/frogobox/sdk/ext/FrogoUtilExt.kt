package com.frogobox.sdk.ext

import android.util.Log
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


/**
 * Created by faisalamir on 19/07/22
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

fun Any.decimalFormat(): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault())
    symbols.decimalSeparator = ','
    symbols.groupingSeparator = '.'
    val decimalFormat = DecimalFormat("##,###,###", symbols)
    decimalFormat.decimalFormatSymbols = symbols
    return when (this) {
        is Long -> decimalFormat.format(this)
        is Int -> decimalFormat.format(toLong())
        is Double -> decimalFormat.format(toLong())
        else -> "Wrong format".also { Log.e("decimalFormat", "value = $this") }
    }
}

fun Any.formatToRupiah(): String {
    return this.decimalFormat()
}

fun Any.formatToIdr(): String {
    return "IDR ${decimalFormat()}"
}

fun Any.formatToKm(): String {
    val decimalFormat = DecimalFormat("#,###.##")
    return decimalFormat.format(this)
}