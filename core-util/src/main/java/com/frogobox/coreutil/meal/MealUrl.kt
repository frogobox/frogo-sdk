package com.frogobox.coreutil.meal

/*
 * Created by faisalamir on 27/07/21
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
object MealUrl {

    const val BASE_URL = "https://www.themealdb.com/"
    const val BASE_PATH = "api/json/v1/"
    const val PATH_API = "{api_key}/"
    const val API_KEY = "1"

    const val URL_SEARCH_MEAL = "${com.frogobox.coreutil.meal.MealUrl.BASE_PATH}${com.frogobox.coreutil.meal.MealUrl.PATH_API}" + "search.php"
    const val URL_LOOKUP_MEAL = "${com.frogobox.coreutil.meal.MealUrl.BASE_PATH}${com.frogobox.coreutil.meal.MealUrl.PATH_API}" + "lookup.php"
    const val URL_RANDOM_MEAL = "${com.frogobox.coreutil.meal.MealUrl.BASE_PATH}${com.frogobox.coreutil.meal.MealUrl.PATH_API}" + "random.php"
    const val URL_CATEGORIES = "${com.frogobox.coreutil.meal.MealUrl.BASE_PATH}${com.frogobox.coreutil.meal.MealUrl.PATH_API}" + "categories.php"
    const val URL_LIST = "${com.frogobox.coreutil.meal.MealUrl.BASE_PATH}${com.frogobox.coreutil.meal.MealUrl.PATH_API}" + "list.php"
    const val URL_FILTER = "${com.frogobox.coreutil.meal.MealUrl.BASE_PATH}${com.frogobox.coreutil.meal.MealUrl.PATH_API}" + "filter.php"

}