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


sealed class FrogoResult<T> {

    data class Success<T>(
        val result: T,
    ) : FrogoResult<T>()

    data class Error<T>(
        val code: Int? = 500,
        val message: String? = "",
        val t: Throwable? = null,
    ) : FrogoResult<T>()

    data class ShowLoading<T>(
        val isLoading: Boolean = true,
    ) : FrogoResult<T>()

    data class HideLoading<T>(
        val isLoading: Boolean = false,
    ) : FrogoResult<T>()

    data class Finish<T>(
        val isFinish: Boolean = true,
    ) : FrogoResult<T>()

}