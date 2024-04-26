package com.frogobox.sdk.ext

import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Created by Faisal Amir on 24/10/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */


val String.isDigitOnly: Boolean
    get() = matches(Regex("^\\d*\$"))

val String.isAlphabeticOnly: Boolean
    get() = matches(Regex("^[a-zA-Z]*\$"))

val String.isAlphanumericOnly: Boolean
    get() = matches(Regex("^[a-zA-Z\\d]*\$"))

fun String.isEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Pair<Boolean, String?> {
    if (this.length < 8) {
        return false to "The password must be at least 8 characters."
    }
    if (!this.contains("[a-z]".toRegex())) {
        return false to "The password must contain at least one lowercase letter."
    }
    if (!this.contains("[A-Z]".toRegex())) {
        return false to "The password must contain at least one uppercase letter"
    }
    if (!this.contains("[0-9]".toRegex())) {
        return false to "The password must contain at least one number."
    }
    if (!this.contains("[#?!@$%^&*-]".toRegex())) {
        return false to "The password must contain at least one special character."
    }
    return true to null
}

fun String.isLessThan(count: Int): Boolean = this.length < count

fun String.toDate(
    dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this) as Date
}

fun String.getUserMention(mention: String): String? {
    val arrays = this.split("@").filter {
        return@filter this.contains("@$it")
    }

    arrays.forEach {
        if (it.contains(mention)) {
            return it
        }
    }

    return null
}

fun String.removeLastChar(): String {
    var str = this
    if (str.isNotEmpty()) {
        str = str.substring(0, str.length - 1)
    }
    return str
}