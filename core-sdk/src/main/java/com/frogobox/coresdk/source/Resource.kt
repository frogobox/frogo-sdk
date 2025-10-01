package com.frogobox.coresdk.source

import com.google.errorprone.annotations.Keep

/**
 * Created by Faisal Amir on 10/12/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */


sealed class Resource<T> {
    class Success<T>(val result: T) : Resource<T>()
    class Error<T>(val code: Int? = 500, val message: String? = "") : Resource<T>()
    class Loading<T> : Resource<T>()
}

sealed class ResourceState {
    class Success : ResourceState()
    class Error(val code: Int? = 500, val message: String? = "") : ResourceState()
    class Loading : ResourceState()
}

sealed class DownloadResource {
    @Keep
    data class Success(val localPath: String) : DownloadResource()

    @Keep
    data class Loading(val progress: Int? = 0) : DownloadResource()

    @Keep
    data class Error(val message: String) : DownloadResource()
}