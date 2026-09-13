package com.frogobox.appsdk.source

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.frogobox.BuildConfig
import com.frogobox.appsdk.model.Article
import com.frogobox.appsdk.model.ArticleResponse
import com.frogobox.appsdk.model.SourceResponse
import com.frogobox.appsdk.util.NewsUrl
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.coresdk.source.FrogoApiClient
import com.frogobox.coresdk.source.Resource
import com.frogobox.sdk.ext.usingChuck
import com.frogobox.sdk.source.FrogoRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 * Created by faisalamir on 08/04/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.
 * All rights reserved
 *
 */

class AppRemoteDataSource(private val context: Context) : FrogoRemoteDataSource(), AppDataSource,
    AppDataSourceResult {

    private fun getApiService(): AppApiService {
        return FrogoApiClient.create(
            url = NewsUrl.BASE_URL,
            isDebug = BuildConfig.DEBUG,
            chuckInterceptor = context.usingChuck()
        )
    }

    override fun getTopHeadline(
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        callback: FrogoDataResponse<List<Article>>,
    ) {
        callback.onShowProgress()
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getApiService().getTopHeadline(
                    NewsUrl.API_KEY, q, sources, category, country, pageSize, page
                )
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onSuccess(response.articles.orEmpty())
                    callback.onFinish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onFailed(500, e.message ?: "Unknown Error")
                    callback.onFinish()
                }
            }
        }
        addSubscribe(job)
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
        callback: FrogoDataResponse<List<Article>>,
    ) {
        callback.onShowProgress()
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getApiService().getEverythings(
                    NewsUrl.API_KEY, q, from, to, qInTitle, sources, domains, excludeDomains, language, sortBy, pageSize, page
                )
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onSuccess(response.articles.orEmpty())
                    callback.onFinish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onFailed(500, e.message ?: "Unknown Error")
                    callback.onFinish()
                }
            }
        }
        addSubscribe(job)
    }

    override fun getSources(
        language: String,
        country: String,
        category: String,
        callback: FrogoDataResponse<SourceResponse>,
    ) {
        callback.onShowProgress()
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getApiService().getSources(
                    NewsUrl.API_KEY, language, country, category
                )
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onSuccess(response)
                    callback.onFinish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onFailed(500, e.message ?: "Unknown Error")
                    callback.onFinish()
                }
            }
        }
        addSubscribe(job)
    }

    override fun saveArticles(data: List<Article>, callback: FrogoStateResponse) {}

    override fun deleteArticles(callback: FrogoStateResponse) {}

    override fun getTopHeadlineResult(
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        result: MutableLiveData<Resource<ArticleResponse>>,
    ) {
        result.postValue(Resource.Loading())
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getApiService().getTopHeadline(
                    NewsUrl.API_KEY, q, sources, category, country, pageSize, page
                )
                result.postValue(Resource.Success(response))
            } catch (e: Exception) {
                result.postValue(Resource.Error(message = e.message ?: "Unknown Error"))
            }
        }
        addSubscribe(job)
    }

    override fun getEverythingsResult(
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
    ) {
        result.postValue(Resource.Loading())
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getApiService().getEverythings(
                    NewsUrl.API_KEY, q, from, to, qInTitle, sources, domains, excludeDomains, language, sortBy, pageSize, page
                )
                result.postValue(Resource.Success(response))
            } catch (e: Exception) {
                result.postValue(Resource.Error(message = e.message ?: "Unknown Error"))
            }
        }
        addSubscribe(job)
    }

    override fun getSourcesResult(
        language: String,
        country: String,
        category: String,
        result: MutableLiveData<Resource<SourceResponse>>,
    ) {
        result.postValue(Resource.Loading())
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getApiService().getSources(
                    NewsUrl.API_KEY, language, country, category
                )
                result.postValue(Resource.Success(response))
            } catch (e: Exception) {
                result.postValue(Resource.Error(message = e.message ?: "Unknown Error"))
            }
        }
        addSubscribe(job)
    }

}