package com.frogobox.appsdk.source

import com.frogobox.appsdk.model.ArticleResponse
import com.frogobox.appsdk.model.SourceResponse
import com.frogobox.appsdk.util.NewsConstant
import com.frogobox.appsdk.util.NewsUrl
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


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

interface AppApiService {

    // Get Top Headline
    @GET(NewsUrl.URL_TOP_HEADLINE)
    fun getTopHeadline(
        @Query(NewsConstant.QUERY_API_KEY) apiKey: String,
        @Query(NewsConstant.QUERY_Q) q: String?,
        @Query(NewsConstant.QUERY_SOURCES) sources: String?,
        @Query(NewsConstant.QUERY_CATEGORY) category: String?,
        @Query(NewsConstant.QUERY_COUNTRY) country: String?,
        @Query(NewsConstant.QUERY_PAGE_SIZE) pageSize: Int?,
        @Query(NewsConstant.QUERY_PAGE) page: Int?,
    ): Observable<ArticleResponse>

    // Get Everythings
    @GET(NewsUrl.URL_EVERYTHING)
    fun getEverythings(
        @Query(NewsConstant.QUERY_API_KEY) apiKey: String,
        @Query(NewsConstant.QUERY_Q) q: String?,
        @Query(NewsConstant.QUERY_FROM) from: String?,
        @Query(NewsConstant.QUERY_TO) to: String?,
        @Query(NewsConstant.QUERY_Q_IN_TITLE) qInTitle: String?,
        @Query(NewsConstant.QUERY_SOURCES) sources: String?,
        @Query(NewsConstant.QUERY_DOMAINS) domains: String?,
        @Query(NewsConstant.QUERY_EXCLUDE_DOMAINS) excludeDomains: String?,
        @Query(NewsConstant.QUERY_LANGUAGE) language: String?,
        @Query(NewsConstant.QUERY_SORT_BY) sortBy: String?,
        @Query(NewsConstant.QUERY_PAGE_SIZE) pageSize: Int?,
        @Query(NewsConstant.QUERY_PAGE) page: Int?,
    ): Observable<ArticleResponse>

    // Get Sources
    @GET(NewsUrl.URL_SOURCES)
    fun getSources(
        @Query(NewsConstant.QUERY_API_KEY) apiKey: String,
        @Query(NewsConstant.QUERY_LANGUAGE) language: String,
        @Query(NewsConstant.QUERY_COUNTRY) country: String,
        @Query(NewsConstant.QUERY_CATEGORY) category: String,
    ): Observable<SourceResponse>

}