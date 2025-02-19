package com.frogobox.sdk.util

import android.util.Patterns


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

object FrogoValidator {

    fun isValidText(username: String): Boolean {
        return username.length > 3
    }

    fun isValidEmail(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            false
        }
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    fun isValidPasswordConfirm(password: String, password_conf: String): Boolean {
        return password == password_conf
    }

    fun onlyCharacter(str: String): Boolean {
        val symbols = "0123456789/?!:;%()+-@#$%^&*"
        return str.any { it in symbols }
    }

}