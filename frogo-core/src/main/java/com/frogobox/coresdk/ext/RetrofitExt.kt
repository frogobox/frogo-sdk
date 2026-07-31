package com.frogobox.coresdk.ext

import com.frogobox.coresdk.response.FrogoDataResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by faisalamir on 07/04/22
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

fun <T : Any> Call<T>.doApiRequest(callback: FrogoDataResponse<T>) {
    callback.onShowProgress()
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    callback.onSuccess(body)
                } else {
                    callback.onFailed(response.code(), "Response body is null")
                }
            } else {
                val errorMessage = response.message().ifEmpty { "HTTP Error ${response.code()}" }
                callback.onFailed(response.code(), errorMessage)
            }
            callback.onHideProgress()
            callback.onFinish()
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            callback.onFailed(500, t.localizedMessage ?: "Network Request Failed")
            callback.onHideProgress()
            callback.onFinish()
        }
    })
}