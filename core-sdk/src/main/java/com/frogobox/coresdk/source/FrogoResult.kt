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

    class Success<T>(val result: T) : FrogoResult<T>()

    class Error<T>(val code: Int? = 500, val message: String? = "") : FrogoResult<T>()

    class Loading<T> : FrogoResult<T>()

}

sealed class FrogoResultState {

    class Success : FrogoResultState()

    class Error(val code: Int? = 500, val message: String? = "") : FrogoResultState()

    class Loading : FrogoResultState()

}