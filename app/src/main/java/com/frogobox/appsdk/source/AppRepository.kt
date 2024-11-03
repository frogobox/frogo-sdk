package com.frogobox.appsdk.source

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.frogobox.appsdk.model.Article
import com.frogobox.appsdk.model.ArticleResponse
import com.frogobox.appsdk.model.SourceResponse
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.coresdk.source.FrogoResult
import com.frogobox.sdk.ext.toJson
import com.frogobox.sdk.source.FrogoRepository
import com.frogobox.sdk.util.FrogoFunc


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

class AppRepository(
    private val context: Context,
    private val remoteDataSource: AppRemoteDataSource,
    private val localDataSource: AppLocalDataSource,
) : FrogoRepository(remoteDataSource, localDataSource), AppDataSource, AppDataSourceResult {

    fun handlingNetworkTopHeadLine(
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        callback: FrogoDataResponse<List<Article>>,
    ) {
        localDataSource.getTopHeadline(q, sources, category, country, pageSize, page,
            object : FrogoDataResponse<List<Article>> {
                override fun onFailed(statusCode: Int, errorMessage: String) {
                    callback.onFailed(statusCode, errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }

                override fun onHideProgress() {
                    callback.onHideProgress()
                }

                override fun onShowProgress() {
                    callback.onShowProgress()
                }

                override fun onSuccess(data: List<Article>) {
                    if (!FrogoFunc.isNetworkConnected(context)) {
                        callback.onSuccess(data)
                    } else {
                        deleteArticles(object : FrogoStateResponse {
                            override fun onSuccess() {
                                remoteDataSource.getTopHeadline(q,
                                    sources,
                                    category,
                                    country,
                                    pageSize,
                                    page,
                                    object : FrogoDataResponse<List<Article>> {
                                        override fun onFailed(
                                            statusCode: Int,
                                            errorMessage: String,
                                        ) {
                                        }

                                        override fun onFinish() {}
                                        override fun onHideProgress() {
                                            callback.onHideProgress()
                                        }

                                        override fun onShowProgress() {
                                            callback.onShowProgress()
                                        }

                                        override fun onSuccess(data: List<Article>) {
                                            callback.onSuccess(data)
                                            saveArticles(data, object : FrogoStateResponse {
                                                override fun onSuccess() {}
                                                override fun onFailed(
                                                    statusCode: Int,
                                                    errorMessage: String,
                                                ) {
                                                }

                                                override fun onFinish() {}
                                                override fun onHideProgress() {
                                                    callback.onHideProgress()
                                                }

                                                override fun onShowProgress() {
                                                    callback.onShowProgress()
                                                }
                                            })
                                            savePrefString("KEY_PREF", data[0].toJson())
                                        }
                                    })
                            }

                            override fun onFailed(statusCode: Int, errorMessage: String) {}
                            override fun onFinish() {}
                            override fun onHideProgress() {
                                callback.onHideProgress()
                            }

                            override fun onShowProgress() {
                                callback.onShowProgress()
                            }
                        })
                    }
                }

            })
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
        remoteDataSource.getTopHeadline(q, sources, category, country, pageSize, page, callback)
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
        remoteDataSource.getEverythings(
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
        callback: FrogoDataResponse<SourceResponse>,
    ) {
        remoteDataSource.getSources(language, country, category, callback)
    }

    override fun saveArticles(data: List<Article>, callback: FrogoStateResponse) {
        localDataSource.saveArticles(data, callback)
    }

    override fun deleteArticles(callback: FrogoStateResponse) {
        localDataSource.deleteArticles(callback)
    }

    override fun getTopHeadlineResult(
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        result: MutableLiveData<FrogoResult<ArticleResponse>>,
    ) {
        remoteDataSource.getTopHeadlineResult(q, sources, category, country, pageSize, page, result)
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
        result: MutableLiveData<FrogoResult<ArticleResponse>>,
    ) {
        remoteDataSource.getEverythingsResult(
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
            result
        )
    }

    override fun getSourcesResult(
        language: String,
        country: String,
        category: String,
        result: MutableLiveData<FrogoResult<SourceResponse>>,
    ) {
        remoteDataSource.getSourcesResult(language, country, category, result)
    }


}