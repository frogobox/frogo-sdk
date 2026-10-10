package com.frogobox.coresdk.source

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by faisalamir on 26/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */

object FrogoApiClient {

    fun clientWithInterceptor(
        timeout: Long? = 30L,
        chuckInterceptor: Interceptor? = null,
        interceptors: List<Interceptor> = emptyList(),
        isDebug: Boolean = false
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .readTimeout(timeout ?: 30L, TimeUnit.SECONDS)
            .writeTimeout(timeout ?: 30L, TimeUnit.SECONDS)
            .connectTimeout(timeout ?: 30L, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

        if (chuckInterceptor != null) {
            client.addInterceptor(chuckInterceptor)
        }
        for (interceptor in interceptors) {
            client.addInterceptor(interceptor)
        }

        return client.build()
    }

    // ---------------------------------------------------------------------------------------------

    inline fun <reified T> create(
        url: String,
        isDebug: Boolean = false,
        timeout: Long? = 30L,
        chuckInterceptor: Interceptor? = null,
        interceptors: List<Interceptor> = emptyList(),
    ): T {
        val okHttpClient = clientWithInterceptor(
            timeout = timeout,
            chuckInterceptor = chuckInterceptor,
            interceptors = interceptors,
            isDebug = isDebug
        )
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(T::class.java)
    }

}