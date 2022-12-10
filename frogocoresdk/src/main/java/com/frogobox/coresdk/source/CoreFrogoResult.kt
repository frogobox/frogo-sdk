package com.frogobox.coresdk.source

/**
 * Created by Faisal Amir on 10/12/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */


sealed class CoreFrogoResult<out T> {
    
    data class Success<out R>(
        val result: R
    ) : CoreFrogoResult<R>()

    data class Failure(
        val message: String? = "",
        val t: Throwable
    ) : CoreFrogoResult<Nothing>()

    object Loading : CoreFrogoResult<Nothing>()

}