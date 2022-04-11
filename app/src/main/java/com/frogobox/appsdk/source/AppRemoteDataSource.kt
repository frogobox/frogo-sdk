package com.frogobox.appsdk.source

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.frogobox.appsdk.BuildConfig
import com.frogobox.appsdk.model.Article
import com.frogobox.appsdk.model.ArticleResponse
import com.frogobox.appsdk.model.SourceResponse
import com.frogobox.appsdk.util.NewsUrl
import com.frogobox.coresdk.observer.FrogoApiObserver
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.coresdk.source.FrogoApiClient
import com.frogobox.sdk.ext.doApiRequest
import com.frogobox.sdk.source.FrogoRemoteDataSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


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
        callback: FrogoDataResponse<List<Article>>
    ) {
        FrogoApiClient
            .create<AppApiService>(
                NewsUrl.BASE_URL,
                BuildConfig.DEBUG,
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .getTopHeadline(NewsUrl.API_KEY, q, sources, category, country, pageSize, page)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : FrogoApiObserver<ArticleResponse>() {
                override fun onApiSuccess(data: ArticleResponse) {
                    data.articles?.let { callback.onSuccess(it) }
                }

                override fun onApiFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }

                override fun onApiFinish() {
                    callback.onFinish()
                }

                override fun onApiStartObserver(disposable: Disposable) {
                    addSubscribe(disposable)
                }
            })
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
        callback: FrogoDataResponse<List<Article>>
    ) {
        FrogoApiClient
            .create<AppApiService>(
                NewsUrl.BASE_URL,
                BuildConfig.DEBUG,
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
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
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : FrogoApiObserver<ArticleResponse>() {
                override fun onApiSuccess(data: ArticleResponse) {
                    data.articles?.let { callback.onSuccess(it) }
                }

                override fun onApiFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }

                override fun onApiFinish() {
                    callback.onFinish()
                }

                override fun onApiStartObserver(disposable: Disposable) {
                    addSubscribe(disposable)
                }
            })
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
                BuildConfig.DEBUG,
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .getSources(NewsUrl.API_KEY, language, country, category)
            .doApiRequest(callback) {
                addSubscribe(it)
            }
    }

    override fun saveArticles(data: List<Article>, callback: FrogoStateResponse) {
    }

    override fun deleteArticles(callback: FrogoStateResponse) {}
}