package com.frogobox.appapi.mvvm.meal

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frogobox.appapi.core.BaseViewModel
import com.frogobox.appapi.source.ApiRepository
import com.frogobox.appapi.util.isDebug
import com.frogobox.coreapi.ConsumeApiResponse
import com.frogobox.coreutil.meal.model.Meal
/**
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
class MealViewModel(
    private val repository: ApiRepository
) : BaseViewModel() {

    val _listData = MutableLiveData<List<Meal>>()
    val listData: LiveData<List<Meal>> = _listData

    fun getListMeals(context: Context, firstLetter: String) {
        val mealApi = repository.consumeTheMealDbApi().usingChuckInterceptor(isDebug, context)
        mealApi.listAllMeal(
            firstLetter,
            object : ConsumeApiResponse<com.frogobox.coreutil.meal.response.MealResponse<Meal>> {
                override fun onSuccess(data: com.frogobox.coreutil.meal.response.MealResponse<Meal>) {
                    // on Success Request
                    data.meals?.let { _listData.postValue(it) }
                }

                override fun onFailed(statusCode: Int, errorMessage: String) {
                    // on Failed
                    _eventFailed.postValue(errorMessage)
                }

                override fun onFinish() {
                    // on Finish
                    _eventFinishState.postValue(true)
                }

                override fun onShowProgress() {
                    // Show Your Progress View
                    _eventShowProgressState.postValue(true)
                }

                override fun onHideProgress() {
                    // Hide Your Progress View
                    _eventShowProgressState.postValue(false)
                }
            })
    }


}