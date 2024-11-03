package com.frogobox.coreapi.news

import com.frogobox.coreutil.news.NewsUrl.URL_EVERYTHING
import com.frogobox.coreutil.news.NewsUrl.URL_SOURCES
import com.frogobox.coreutil.news.NewsUrl.URL_TOP_HEADLINE
import com.frogobox.coreutil.news.response.ArticleResponse
import com.frogobox.coreutil.news.response.SourceResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

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
 * com.frogobox.frogoconsumeapi.news.data.source
 *
 */
interface NewsApiService {

    // Get Top Headline
    @GET(URL_TOP_HEADLINE)
    fun getTopHeadline(
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_Q) q: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_SOURCES) sources: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_CATEGORY) category: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_COUNTRY) country: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_PAGE_SIZE) pageSize: Int?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.news.response.ArticleResponse>

    // Get Everythings
    @GET(URL_EVERYTHING)
    fun getEverythings(
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_Q) q: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_FROM) from: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_TO) to: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_Q_IN_TITLE) qInTitle: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_SOURCES) sources: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_DOMAINS) domains: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_EXCLUDE_DOMAINS) excludeDomains: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_SORT_BY) sortBy: String?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_PAGE_SIZE) pageSize: Int?,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.news.response.ArticleResponse>

    // Get Sources
    @GET(URL_SOURCES)
    fun getSources(
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_LANGUAGE) language: String,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_COUNTRY) country: String,
        @Query(com.frogobox.coreutil.news.NewsConstant.QUERY_CATEGORY) category: String
    ): Observable<com.frogobox.coreutil.news.response.SourceResponse>

}