package com.frogobox.appsdk.source

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.frogobox.appsdk.model.ArticleResponse
import com.frogobox.appsdk.model.SourceResponse
import com.frogobox.appsdk.util.NewsUrl
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.source.FrogoApiClient
import com.frogobox.sdk.ext.doApiRequest
import com.frogobox.sdk.source.FrogoRemoteDataSource


/*
 * Created by faisalamir on 08/04/22
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

class AppRemoteDataSource(private val context: Context) : FrogoRemoteDataSource(), AppDataSource {

    override fun getTopHeadline(
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        callback: FrogoDataResponse<ArticleResponse>
    ) {
        FrogoApiClient
            .create<AppApiService>(
                NewsUrl.BASE_URL,
                false,
                ChuckerInterceptor(context)
            )
            .getTopHeadline(NewsUrl.API_KEY, q, sources, category, country, pageSize, page)
            .doApiRequest(callback) {
                addSubscribe(it)
            }
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
        callback: FrogoDataResponse<ArticleResponse>
    ) {
        FrogoApiClient
            .create<AppApiService>(
                NewsUrl.BASE_URL,
                false,
                ChuckerInterceptor(context)
            )
            .getEverythings(
                NewsUrl.API_KEY,
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
                page
            )
            .doApiRequest(callback) {
                addSubscribe(it)
            }
    }

    override fun getSources(
        language: String,
        country: String,
        category: String,
        callback: FrogoDataResponse<SourceResponse>
    ) {
        FrogoApiClient
            .create<AppApiService>(
                NewsUrl.BASE_URL,
                false,
                ChuckerInterceptor(context)
            )
            .getSources(NewsUrl.API_KEY, language, country, category)
            .doApiRequest(callback) {
                addSubscribe(it)
            }
    }

}