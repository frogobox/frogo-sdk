package com.frogobox.sdk.ext

import com.frogobox.coresdk.response.FrogoDataResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/*
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

// Single Api Request
fun <T : Any> Call<T>.doApiRequest(callback: FrogoDataResponse<T>) {
    showLogDebug("doApiRequest : onShowProgress")
    callback.onShowProgress()
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            showLogDebug("doApiRequest : onSuccess")
            response.body()?.let { callback.onSuccess(it) }

            showLogDebug("doApiRequest : onHideProgress")
            callback.onHideProgress()
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            showLogDebug("doApiRequest : onFailed")
            t.localizedMessage?.let { callback.onFailed(500, it) }

            showLogDebug("doApiRequest : onHideProgress")
            callback.onHideProgress()
        }
    })
}