package com.frogobox.coreapi.meal


import com.frogobox.coreutil.meal.response.CategoryResponse
import com.frogobox.coreutil.meal.response.MealResponse
import com.frogobox.coreutil.meal.model.Area
import com.frogobox.coreutil.meal.model.Category
import com.frogobox.coreutil.meal.model.Ingredient
import com.frogobox.coreutil.meal.model.Meal
import com.frogobox.coreutil.meal.model.MealFilter
import com.frogobox.coresdk.response.FrogoDataResponse
import okhttp3.Interceptor


/*
 * Created by faisalamir on 07/04/22
 * FrogoConsumeApi
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

interface IMealApi {

    // Switch For Using Chuck Interceptor
    fun usingChuckInterceptor(isDebug: Boolean, chuckerInterceptor: Interceptor): IMealApi

    // Search meal by name
    fun searchMeal(mealName: String, callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Meal>>)

    // List all meals by first letter
    fun listAllMeal(firstLetter: String, callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Meal>>)

    // Lookup full meal details by id
    fun lookupFullMeal(idMeal: String, callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Meal>>)

    // Lookup a single random meal
    fun lookupRandomMeal(callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Meal>>)

    // List all meal categories
    fun listMealCategories(callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.CategoryResponse>)

    // List all Categories
    fun listAllCateories(callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Category>>)

    // List all Area
    fun listAllArea(callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Area>>)

    // List all Ingredients
    fun listAllIngredients(callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.Ingredient>>)

    // Filter by main ingredient
    fun filterByIngredient(
        ingredient: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.MealFilter>>
    )

    // Filter by Category
    fun filterByCategory(category: String, callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.MealFilter>>)

    // Filter by Area
    fun filterByArea(area: String, callback: FrogoDataResponse<com.frogobox.coreutil.meal.response.MealResponse<com.frogobox.coreutil.meal.model.MealFilter>>)

}