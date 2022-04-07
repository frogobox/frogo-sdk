package com.frogobox.coresdk.ext

import java.text.NumberFormat
import java.util.*


/*
 * Created by faisalamir on 07/04/22
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

fun Double.toRupiahCurrency(): String {
    return NumberFormat
        .getCurrencyInstance(Locale("in", "ID")).apply {
            maximumFractionDigits = 0
        }
        .format(this)
}