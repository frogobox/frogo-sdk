package com.frogobox.appsdk.source

import com.frogobox.coreapi.news.response.ArticleResponse
import com.frogobox.coreapi.news.response.SourceResponse
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.sdk.preference.FrogoPreference
import com.frogobox.sdk.source.FrogoLocalDataSource
import com.frogobox.sdk.util.AppExecutors
import io.reactivex.rxjava3.core.Scheduler


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

class AppLocalDataSource(
    private val appExecutors: AppExecutors,
    private val preferences: FrogoPreference
) : FrogoLocalDataSource(appExecutors, preferences), AppDataSource {

    override fun getTopHeadline(
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        callback: FrogoDataResponse<ArticleResponse>
    ) {
        
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
        
    }

    override fun getSources(
        language: String,
        country: String,
        category: String,
        callback: FrogoDataResponse<SourceResponse>
    ) {
        
    }
}