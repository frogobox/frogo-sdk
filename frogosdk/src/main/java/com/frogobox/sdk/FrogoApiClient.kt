package com.frogobox.sdk

import android.content.Context
import android.util.Log
import com.frogobox.coresdk.FrogoCoreApiClient
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
 * Created by faisalamir on 26/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */

object FrogoApiClient  {

    val TAG: String = FrogoApiClient::class.java.simpleName

    inline fun <reified T> create(url: String): T {
        Log.d(TAG, "Create Retrofit Request without Client")
        return FrogoCoreApiClient.create(url)
    }

    inline fun <reified T> create(url: String, context: Context): T {

        Log.d(TAG, "Create Retrofit Request with Client")

        return FrogoCoreApiClient.create(url, ChuckInterceptor(context))
    }

}