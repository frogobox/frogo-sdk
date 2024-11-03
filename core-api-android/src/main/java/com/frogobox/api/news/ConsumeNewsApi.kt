package com.frogobox.api.news

import android.content.Context
import com.frogobox.coreapi.news.INewsApi
import com.frogobox.coreapi.news.NewsApi
import com.frogobox.coreutil.news.response.ArticleResponse
import com.frogobox.coreutil.news.response.SourceResponse
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.sdk.ext.usingChuck
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import okhttp3.Interceptor

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * consumable-code-news-api
 * Copyright (C) 15/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.news
 *
 */
class ConsumeNewsApi(apiKey: String) : IConsumeNewsApi {

    private var newsApi = NewsApi(AndroidSchedulers.mainThread(), apiKey)

    override fun usingChuckInterceptor(isDebug: Boolean, context: Context): INewsApi {
        return usingChuckInterceptor(isDebug, context.usingChuck())
    }

    override fun usingChuckInterceptor(
        isDebug: Boolean,
        chuckerInterceptor: Interceptor
    ): INewsApi {
        return newsApi.usingChuckInterceptor(isDebug, chuckerInterceptor)
    }

    override fun getTopHeadline(
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.news.response.ArticleResponse>
    ) {
        newsApi.getTopHeadline(
            q,
            sources,
            category,
            country,
            pageSize,
            page,
            callback
        )
    }

    override fun getEverythings(
        q: String?,
        from: String?,
        to: String?,
        qInTitle: String?,
        sources: String?,
        domains: String?,
        excludeDomains: String?,
        language: String?,
        sortBy: String?,
        pageSize: Int?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.news.response.ArticleResponse>
    ) {
        newsApi.getEverythings(
            q,
            from,
            to,
            qInTitle,
            sources,
            domains,
            excludeDomains,
            language,
            sortBy,
            pageSize,
            page,
            callback
        )
    }

    override fun getSources(
        language: String,
        country: String,
        category: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.news.response.SourceResponse>
    ) {
        newsApi.getSources(
            language,
            country,
            category,
            callback
        )
    }

}