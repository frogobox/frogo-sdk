package com.frogobox.coresdk.source

/**
 * Created by faisalamircs on 10/12/2023
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


sealed class FrogoResource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : FrogoResource<T>(data)
    class Loading<T>(data: T? = null) : FrogoResource<T>(data)
    class Error<T>(message: String, data: T? = null) : FrogoResource<T>(data, message)
}
