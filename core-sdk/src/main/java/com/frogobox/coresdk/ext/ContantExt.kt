package com.frogobox.coresdk.ext


/**
 * Created by faisalamir on 11/04/22
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

fun showPrintLog(message: String) {
    println("FrogoCoreSDK : $message")
}

fun Any?.println() {
    showPrintLog(this.toString())
}