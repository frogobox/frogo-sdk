package com.frogobox.appsdk.news.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frogobox.appsdk.model.ArticleResponse
import com.frogobox.appsdk.source.AppRepository
import com.frogobox.appsdk.util.NewsConstant
import com.frogobox.coresdk.source.Resource
import com.frogobox.sdk.view.FrogoViewModel2


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

class NewsResultViewModel(
    private val repository: AppRepository,
) : FrogoViewModel2() {

    private var _articles = MutableLiveData<Resource<ArticleResponse>>()
    var articles: LiveData<Resource<ArticleResponse>> = _articles

    private fun getData() {
        repository.getTopHeadlineResult(
            "",
            null,
            NewsConstant.CATEGORY_GENERAL,
            NewsConstant.COUNTRY_ID,
            null,
            null,
            _articles
        )
    }

    private fun getPref() {
        repository.getPrefString("KEY_PREF")
    }

    override fun onStart() {
        super.onStart()
        getData()
        getPref()
    }

    override fun onClearDisposable() {
        super.onClearDisposable()
        repository.onClearDisposables()
    }

}