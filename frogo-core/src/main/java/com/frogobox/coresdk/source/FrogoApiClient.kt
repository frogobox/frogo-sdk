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

    fun clientWithInterceptor(
        timeout: Long? = 30L,
        chuckInterceptor: Interceptor? = null,
        isDebug: Boolean = false
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .readTimeout(timeout ?: 30L, TimeUnit.SECONDS)
            .connectTimeout(timeout ?: 30L, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

        return if (chuckInterceptor != null) {
            client.addInterceptor(chuckInterceptor).build()
        } else {
            client.build()
        }
    }

    // ---------------------------------------------------------------------------------------------

    inline fun <reified T> create(
        url: String,
        isDebug: Boolean = false,
        timeout: Long? = 30L,
        chuckInterceptor: Interceptor? = null,
    ): T {
        val okHttpClient = clientWithInterceptor(timeout, chuckInterceptor, isDebug)
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(T::class.java)
    }

}

/**

interface SampleApiService {

    /**
     *     @GET(URL_SEARCH_MEAL)
     *     suspend fun searchMeal(
     *         @Path(PATH_API_KEY) apiKey: String,
     *         @Query(QUERY_NAME) nameMeal: String,
     *     ): Response<MealResponse<MealModel>>
     */

}

 *
 */