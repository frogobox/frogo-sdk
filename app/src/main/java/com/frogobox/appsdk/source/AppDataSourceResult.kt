package com.frogobox.appsdk.source

import androidx.lifecycle.MutableLiveData
import com.frogobox.appsdk.model.ArticleResponse
import com.frogobox.appsdk.model.SourceResponse
import com.frogobox.coresdk.source.Resource


/**
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

interface AppDataSourceResult {

    // Get Top Headline
    fun getTopHeadlineResult(
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        result: MutableLiveData<Resource<ArticleResponse>>,
    )

    // Get Everythings
    fun getEverythingsResult(
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
        result: MutableLiveData<Resource<ArticleResponse>>,
    )

    // Get Sources
    fun getSourcesResult(
        language: String,
        country: String,
        category: String,
        result: MutableLiveData<Resource<SourceResponse>>,
    )

}