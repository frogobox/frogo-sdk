package com.frogobox.sdk.source

/**
 * Created by Faisal Amir on 10/12/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */


sealed class FrogoResult<out T> {

    data class Success<out R>(
        val result: R,
    ) : FrogoResult<R>()

    data class Error(
        val code: Int? = 500,
        val message: String? = "",
        val t: Throwable? = null,
    ) : FrogoResult<Nothing>()
    
    data class Loading(
        val isLoading: Boolean,
    ) : FrogoResult<Nothing>()

    object Finish : FrogoResult<Nothing>()

}