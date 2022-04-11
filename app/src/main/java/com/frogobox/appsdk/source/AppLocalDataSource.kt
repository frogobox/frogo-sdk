package com.frogobox.appsdk.source

import com.frogobox.appsdk.model.Article
import com.frogobox.appsdk.model.SourceResponse
import com.frogobox.appsdk.source.dao.ArticleDao
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.response.FrogoStateResponse
import com.frogobox.sdk.ext.doLocalRequest
import com.frogobox.sdk.ext.rxJavaCompletableFromAction
import com.frogobox.sdk.preference.FrogoPreference
import com.frogobox.sdk.source.FrogoLocalDataSource
import com.frogobox.sdk.util.AppExecutors
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
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

class AppLocalDataSource(
    private val appExecutors: AppExecutors,
    private val preferences: FrogoPreference,
    private val articleDao: ArticleDao
) : FrogoLocalDataSource(appExecutors, preferences), AppDataSource {

    override fun getTopHeadline(
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        callback: FrogoDataResponse<List<Article>>
    ) {
        articleDao.getArticleList().doLocalRequest(callback) {
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
        callback: FrogoDataResponse<List<Article>>
    ) {
        articleDao.getArticleList()
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Article>> {
                override fun onSuccess(t: List<Article>) {
                    callback.onSuccess(t)
                }

                override fun onSubscribe(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onError(e: Throwable) {
                    callback.onHideProgress()
                    e.message?.let { callback.onFailed(404, it) }
                }
            })
    }

    override fun getSources(
        language: String,
        country: String,
        category: String,
        callback: FrogoDataResponse<SourceResponse>
    ) {

    }

    override fun saveArticles(data: List<Article>, callback: FrogoStateResponse) {
        rxJavaCompletableFromAction(callback) {
            articleDao.insertAllArticles(data)
        }
    }

    override fun deleteArticles(callback: FrogoStateResponse) {
        rxJavaCompletableFromAction(callback){
            articleDao.deleteAllArticles()
        }
    }
}