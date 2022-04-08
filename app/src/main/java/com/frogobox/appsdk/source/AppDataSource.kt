package com.frogobox.appsdk.source

import com.frogobox.coreapi.news.response.ArticleResponse
import com.frogobox.coreapi.news.response.SourceResponse
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.source.ICoreDataSource


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

interface AppDataSource : ICoreDataSource {

    // Get Top Headline
    fun getTopHeadline(
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        callback: FrogoDataResponse<ArticleResponse>
    )

    // Get Everythings
    fun getEverythings(
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
    )

    // Get Sources
    fun getSources(
        language: String,
        country: String,
        category: String,
        callback: FrogoDataResponse<SourceResponse>
    )

}