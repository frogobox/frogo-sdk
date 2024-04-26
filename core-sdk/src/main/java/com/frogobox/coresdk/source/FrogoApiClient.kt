package com.frogobox.coresdk.source

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
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

object FrogoApiClient {

    val TAG: String = FrogoApiClient::class.java.simpleName

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun client(timeout: Long? = 30L): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(timeout ?: 30L, TimeUnit.SECONDS)
            .connectTimeout(timeout ?: 30L, TimeUnit.SECONDS)
            .build()
    }

    fun clientWithInterceptor(timeout: Long? = 30L): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(timeout ?: 30L, TimeUnit.SECONDS)
            .connectTimeout(timeout ?: 30L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    fun clientWithInterceptor(chuckInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckInterceptor)
            .build()
    }

    // ---------------------------------------------------------------------------------------------

    inline fun <reified T> create(url: String, timeout: Long? = 30L): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client(timeout))
            .build().create(T::class.java)
    }

    inline fun <reified T> create(url: String, isDebug: Boolean, timeout: Long? = 30L): T {
        return if (isDebug) {
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientWithInterceptor(timeout))
                .build().create(T::class.java)
        } else {
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client(timeout))
                .build().create(T::class.java)
        }
    }

    inline fun <reified T> create(
        url: String,
        isDebug: Boolean,
        chuckInterceptor: Interceptor,
        timeout: Long? = 30L,
    ): T {
        return if (isDebug) {
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientWithInterceptor(chuckInterceptor))
                .build().create(T::class.java)
        } else {
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client(timeout))
                .build().create(T::class.java)
        }
    }

}