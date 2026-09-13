package com.frogobox.appsdk.source

import com.frogobox.appsdk.model.Article
import com.frogobox.appsdk.model.SourceResponse
import com.frogobox.appsdk.source.dao.ArticleDao
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.source.FrogoLocalDataSource
import com.frogobox.sdk.util.AppExecutors
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

class AppLocalDataSource(
    private val appExecutors: AppExecutors,
    private val preferences: PreferenceDelegatesImpl,
    private val articleDao: ArticleDao,
) : FrogoLocalDataSource(appExecutors, preferences), AppDataSource {

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
                val data = articleDao.getArticles()
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onSuccess(data)
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
                val data = articleDao.getArticles()
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onSuccess(data)
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

    }

    override fun saveArticles(data: List<Article>, callback: FrogoStateResponse) {
        callback.onShowProgress()
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                articleDao.insertArticles(data)
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onSuccess()
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

    override fun deleteArticles(callback: FrogoStateResponse) {
        callback.onShowProgress()
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                articleDao.deleteArticles()
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onSuccess()
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
}