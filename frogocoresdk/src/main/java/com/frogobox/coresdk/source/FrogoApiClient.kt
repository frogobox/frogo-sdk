package com.frogobox.coresdk.source

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
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

object FrogoApiClient {

    val TAG: String = FrogoApiClient::class.java.simpleName

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun client(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun clientWithInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
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

    @Deprecated(
        "createWithInterceptor is to complicated",
        ReplaceWith("create(url: String, isDebug: Boolean, chuckInterceptor: Interceptor) for simple implementation")
    )
    inline fun <reified T> createWithInterceptor(url: String): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(clientWithInterceptor())
            .build().create(T::class.java)
    }

    @Deprecated(
        "createWithInterceptor is to complicated",
        ReplaceWith("create(url: String, isDebug: Boolean, chuckInterceptor: Interceptor) for simple implementation")
    )
    inline fun <reified T> createWithInterceptor(url: String, chuckInterceptor: Interceptor): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(clientWithInterceptor(chuckInterceptor))
            .build().create(T::class.java)
    }

    // ---------------------------------------------------------------------------------------------

    inline fun <reified T> create(url: String): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client())
            .build().create(T::class.java)
    }

    inline fun <reified T> create(url: String, isDebug: Boolean): T {
        return if (isDebug) {
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientWithInterceptor())
                .build().create(T::class.java)
        } else {
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client())
                .build().create(T::class.java)
        }
    }

    inline fun <reified T> create(url: String, isDebug: Boolean, chuckInterceptor: Interceptor): T {
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
                .client(client())
                .build().create(T::class.java)
        }
    }

}