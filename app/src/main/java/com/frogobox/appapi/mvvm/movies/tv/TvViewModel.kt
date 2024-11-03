package com.frogobox.appapi.mvvm.movies.tv

import android.app.Application
import com.frogobox.appapi.mvvm.movies.core.BaseMovieApiViewModel
import com.frogobox.appapi.source.ApiRepository
import com.frogobox.coreapi.ConsumeApiResponse
import com.frogobox.sdk.util.FrogoMutableLiveData

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
class TvViewModel(
    private val context: Application,
    private val repository: ApiRepository
) : BaseMovieApiViewModel(context, repository) {

    val listDataDay = FrogoMutableLiveData<List<com.frogobox.coreutil.movie.model.TrendingTv>>()
    val listDataWeek = FrogoMutableLiveData<List<com.frogobox.coreutil.movie.model.TrendingTv>>()

    fun getTrendingTvDay() {
        movieApi.getTrendingTvDay(object : ConsumeApiResponse<com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingTv>> {
            override fun onSuccess(data: com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingTv>) {
                // On Success
                data.results?.let { listDataDay.postValue(it) }
            }

            override fun onFailed(statusCode: Int, errorMessage: String) {
                // Your failed to do
                _eventFailed.postValue(errorMessage)
            }

            override fun onFinish() {

            }

            override fun onShowProgress() {
                // Your Progress Show
                _eventShowProgressState.postValue(true)
            }

            override fun onHideProgress() {
                // Your Progress Hide
                _eventShowProgressState.postValue(false)
            }
        })
    }

    fun getTrendingTvWeek() {
        movieApi.getTrendingTvWeek(object : ConsumeApiResponse<com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingTv>> {
            override fun onSuccess(data: com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingTv>) {
                // On Success
                data.results?.let { listDataWeek.postValue(it) }
            }

            override fun onFailed(statusCode: Int, errorMessage: String) {
                // Your failed to do
                _eventFailed.postValue(errorMessage)
            }

            override fun onFinish() {

            }

            override fun onShowProgress() {
                // Your Progress Show
                _eventShowProgressState.postValue(true)
            }

            override fun onHideProgress() {
                // Your Progress Hide
                _eventShowProgressState.postValue(false)
            }
        })
    }

}