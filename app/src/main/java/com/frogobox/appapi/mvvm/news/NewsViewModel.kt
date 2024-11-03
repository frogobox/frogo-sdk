package com.frogobox.appapi.mvvm.news

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frogobox.appapi.core.BaseViewModel
import com.frogobox.appapi.source.ApiRepository
import com.frogobox.appapi.util.isDebug
import com.frogobox.coreapi.ConsumeApiResponse
import com.frogobox.coreutil.news.NewsConstant.CATEGORY_BUSINESS
import com.frogobox.coreutil.news.NewsConstant.CATEGORY_ENTERTAIMENT
import com.frogobox.coreutil.news.NewsConstant.CATEGORY_GENERAL
import com.frogobox.coreutil.news.NewsConstant.CATEGORY_HEALTH
import com.frogobox.coreutil.news.NewsConstant.CATEGORY_SCIENCE
import com.frogobox.coreutil.news.NewsConstant.CATEGORY_SPORTS
import com.frogobox.coreutil.news.NewsConstant.CATEGORY_TECHNOLOGY
import com.frogobox.coreutil.news.NewsConstant.COUNTRY_ID
import com.frogobox.coreutil.news.model.Article
import com.frogobox.coreutil.news.response.ArticleResponse
import com.frogobox.sdk.ext.showLogDebug

/*
 * Created by faisalamir on 28/07/21
 * Consumable
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
class NewsViewModel(
    private val context: Context,
    private val repository: ApiRepository,
) : BaseViewModel() {

    private var _listDataCategory = MutableLiveData<List<com.frogobox.coreutil.news.model.Article>>()
    var listDataCategory: LiveData<List<com.frogobox.coreutil.news.model.Article>> = _listDataCategory

    private var _listData = MutableLiveData<List<com.frogobox.coreutil.news.model.Article>>()
    var listData: LiveData<List<com.frogobox.coreutil.news.model.Article>> = _listData

    private var _listCategory = MutableLiveData<List<String>>()
    var listCategory : LiveData<List<String>> = _listCategory

    val newsApi = repository.consumeNewsApi().usingChuckInterceptor(isDebug, context)

    fun setupCategory() {
        val categories = mutableListOf<String>()
        categories.add(CATEGORY_BUSINESS)
        categories.add(CATEGORY_HEALTH)
        categories.add(CATEGORY_ENTERTAIMENT)
        categories.add(CATEGORY_GENERAL)
        categories.add(CATEGORY_SCIENCE)
        categories.add(CATEGORY_SPORTS)
        categories.add(CATEGORY_TECHNOLOGY)
        _listCategory.postValue(categories)
    }

    fun getTopHeadline(category: String) {
        newsApi.getTopHeadline(
            null,
            null,
            category,
            COUNTRY_ID,
            null,
            null,
            object : ConsumeApiResponse<com.frogobox.coreutil.news.response.ArticleResponse> {

                override fun onSuccess(data: com.frogobox.coreutil.news.response.ArticleResponse) {
                    // Your Ui or data
                    data.articles?.let { _listDataCategory.postValue(it) }
                }

                override fun onFailed(statusCode: Int, errorMessage: String) {
                    // Your failed to do
                    _eventFailed.postValue(errorMessage)
                }

                override fun onFinish() {

                }

                override fun onShowProgress() {
                    // Your Progress Show
                    showLogDebug("onShowProgress --------------------------------------------------->")
                    _eventShowProgressState.postValue(true)
                }

                override fun onHideProgress() {
                    // Your Progress Hide
                    showLogDebug("onHideProgress --------------------------------------------------->")
                    _eventShowProgressState.postValue(false)
                }
            })
    }

    fun getTopHeadline() {
        newsApi.getTopHeadline(
            null,
            null,
            null,
            COUNTRY_ID,
            null,
            null,
            object : ConsumeApiResponse<com.frogobox.coreutil.news.response.ArticleResponse> {

                override fun onSuccess(data: com.frogobox.coreutil.news.response.ArticleResponse) {
                    // Your Ui or data
                    data.articles?.let { _listData.postValue(it) }
                }

                override fun onFailed(statusCode: Int, errorMessage: String) {
                    // Your failed to do
                    _eventFailed.postValue(errorMessage)
                }

                override fun onFinish() {

                }

                override fun onShowProgress() {
                    // Your Progress Show
                    showLogDebug("onShowProgress --------------------------------------------------->")
                    _eventShowProgressState.postValue(true)
                }

                override fun onHideProgress() {
                    // Your Progress Hide
                    showLogDebug("onHideProgress --------------------------------------------------->")
                    _eventShowProgressState.postValue(false)
                }

            })
    }

}