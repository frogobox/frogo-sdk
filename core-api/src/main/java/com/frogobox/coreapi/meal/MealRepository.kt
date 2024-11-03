package com.frogobox.coreapi.meal

import com.frogobox.coreutil.meal.response.CategoryResponse
import com.frogobox.coreutil.meal.response.MealResponse
import com.frogobox.coreutil.meal.model.Area
import com.frogobox.coreutil.meal.model.Category
import com.frogobox.coreutil.meal.model.Ingredient
import com.frogobox.coreutil.meal.model.Meal
import com.frogobox.coreutil.meal.model.MealFilter
import com.frogobox.coresdk.ext.doApiRequest
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.source.FrogoApiClient
import io.reactivex.rxjava3.core.Scheduler
import okhttp3.Interceptor

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * consumable-code-the-meal-db-api
 * Copyright (C) 15/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogomealsapi.data.source
 *
 */
object MealRepository : MealDataSource {

    private val TAG = MealRepository::class.java.simpleName
    private var mealApiService = FrogoApiClient.create<MealApiService>(com.frogobox.coreutil.meal.MealUrl.BASE_URL)

    override fun usingChuckInterceptor(
        isDebug: Boolean,
        chuckerInterceptor: Interceptor
    ): MealDataSource {
        mealApiService = FrogoApiClient.create(com.frogobox.coreutil.meal.MealUrl.BASE_URL, isDebug, chuckerInterceptor)
        return this
    }

    override fun searchMeal(
        scheduler: Scheduler?, apiKey: String,
        mealName: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Meal>>
    ) {
        mealApiService.searchMeal(apiKey, mealName).doApiRequest(scheduler, callback) {}
    }

    override fun listAllMeal(
        scheduler: Scheduler?, apiKey: String,
        firstLetter: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Meal>>
    ) {
        mealApiService.listAllMeal(apiKey, firstLetter).doApiRequest(scheduler, callback) {}
    }

    override fun lookupFullMeal(
        scheduler: Scheduler?, apiKey: String,
        idMeal: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Meal>>
    ) {
        mealApiService.lookupFullMeal(apiKey, idMeal).doApiRequest(scheduler, callback) {}
    }

    override fun lookupRandomMeal(
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Meal>>
    ) {
        mealApiService.lookupRandomMeal(apiKey).doApiRequest(scheduler, callback) {}
    }

    override fun listMealCategories(
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.CategoryResponse>
    ) {
        mealApiService.listMealCategories(apiKey).doApiRequest(scheduler, callback) {}
    }

    override fun listAllCateories(
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Category>>
    ) {
        mealApiService.listAllCateories(apiKey, com.frogobox.coreutil.meal.MealConstant.VALUE_LIST)
            .doApiRequest(scheduler, callback) {}
    }

    override fun listAllArea(
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Area>>
    ) {
        mealApiService.listAllArea(apiKey, com.frogobox.coreutil.meal.MealConstant.VALUE_LIST)
            .doApiRequest(scheduler, callback) {}
    }

    override fun listAllIngredients(
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Ingredient>>
    ) {
        mealApiService.listAllIngredients(apiKey, com.frogobox.coreutil.meal.MealConstant.VALUE_LIST)
            .doApiRequest(scheduler, callback) {}
    }

    override fun filterByIngredient(
        scheduler: Scheduler?, apiKey: String,
        ingredient: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.MealFilter>>
    ) {
        mealApiService.filterByIngredient(apiKey, ingredient).doApiRequest(scheduler, callback) {}
    }

    override fun filterByCategory(
        scheduler: Scheduler?, apiKey: String,
        category: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.MealFilter>>
    ) {
        mealApiService.filterByCategory(apiKey, category).doApiRequest(scheduler, callback) {}
    }

    override fun filterByArea(
        scheduler: Scheduler?, apiKey: String,
        area: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.MealFilter>>
    ) {
        mealApiService.filterByArea(apiKey, area).doApiRequest(scheduler, callback) {}
    }
}