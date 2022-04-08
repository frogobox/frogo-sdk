package com.frogobox.appsdk.news

import android.app.Application
import com.frogobox.appsdk.source.AppRepository
import com.frogobox.coreapi.news.NewsConstant
import com.frogobox.coreapi.news.model.Article
import com.frogobox.coreapi.news.response.ArticleResponse
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.sdk.util.FrogoMutableLiveData
import com.frogobox.sdk.view.FrogoViewModel


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

class NewsViewModel(
    application: Application,
    private val repository: AppRepository
) : FrogoViewModel(application) {

    val listData = FrogoMutableLiveData<List<Article>>()
    
    fun getData() {
        repository.getTopHeadline(
            null,
            null,
            NewsConstant.CATEGORY_GENERAL,
            NewsConstant.COUNTRY_ID,
            null,
            null,
            object : FrogoDataResponse<ArticleResponse>{
                override fun onFailed(statusCode: Int, errorMessage: String) {
                    showLogError(errorMessage)
                }

                override fun onFinish() {
                    showLogDebug("ON FINISH  --------> ")
                }

                override fun onHideProgress() {
                    showLogDebug("ON HIDE PROGRES --------> ")
                }

                override fun onShowProgress() {
                    showLogDebug("ON SHOW PROGRES --------> ")
                }

                override fun onSuccess(data: ArticleResponse) {
                    listData.postValue(data.articles!!)
                }
            }
        )
    }
    
    
}