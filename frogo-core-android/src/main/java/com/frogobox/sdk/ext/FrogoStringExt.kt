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
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */


private const val PASSWORD_SPECIAL_CHARS = "#?!@$%^&*-"

val String.isDigitOnly: Boolean
    get() = isNotEmpty() && all { it.isDigit() }

val String.isAlphabeticOnly: Boolean
    get() = isNotEmpty() && all { it.isLetter() }

val String.isAlphanumericOnly: Boolean
    get() = isNotEmpty() && all { it.isLetterOrDigit() }

fun String.isEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Pair<Boolean, String?> {
    if (this.length < 8) {
        return false to "The password must be at least 8 characters."
    }
    if (!this.any { it.isLowerCase() }) {
        return false to "The password must contain at least one lowercase letter."
    }
    if (!this.any { it.isUpperCase() }) {
        return false to "The password must contain at least one uppercase letter"
    }
    if (!this.any { it.isDigit() }) {
        return false to "The password must contain at least one number."
    }
    if (!this.any { it in PASSWORD_SPECIAL_CHARS }) {
        return false to "The password must contain at least one special character."
    }
    return true to null
}

fun String.isLessThan(count: Int): Boolean = this.length < count

fun String.toDate(
    dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC"),
    locale: Locale = Locale.US
): Date {
    val parser = SimpleDateFormat(dateFormat, locale)
    parser.timeZone = timeZone
    return parser.parse(this)
        ?: throw IllegalArgumentException("Cannot parse date string: '$this' with format '$dateFormat'")
}

fun String.getUserMention(mention: String): String? {
    val parts = this.split(" ")
    return parts.firstOrNull { it.startsWith("@") && it.contains(mention, ignoreCase = true) }
        ?.removePrefix("@")
}

fun String.removeLastChar(): String = dropLast(1)