package com.frogobox.sdk.source

import androidx.annotation.Keep

/**
 * Created by Faisal Amir on 10/12/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */

@Keep
sealed class FrogoResult<out T> {

    @Keep
    data class Success<out R>(
        val result: R
    ) : FrogoResult<R>()

    @Keep
    data class Failure(
        val message: String? = "",
        val t: Throwable
    ) : FrogoResult<Nothing>()

    object Loading : FrogoResult<Nothing>()

}