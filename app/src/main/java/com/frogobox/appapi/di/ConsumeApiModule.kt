package com.frogobox.appapi.di

import com.frogobox.api.meal.ConsumeTheMealDbApi
import com.frogobox.api.movie.ConsumeMovieApi
import com.frogobox.api.news.ConsumeNewsApi
import com.frogobox.api.pixabay.ConsumePixabayApi
import com.frogobox.api.sport.ConsumeTheSportDbApi
import com.frogobox.coreutil.meal.MealUrl
import com.frogobox.coreutil.movie.MovieUrl
import com.frogobox.coreutil.news.NewsUrl
import com.frogobox.coreutil.pixabay.PixabayUrl
import com.frogobox.coreutil.sport.SportUrl
import org.koin.dsl.module


/*
 * Created by faisalamir on 01/05/22
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

val consumeApiModule = module {

    single {
        ConsumeNewsApi(com.frogobox.coreutil.news.NewsUrl.API_KEY)
    }

    single {
        ConsumeTheSportDbApi(com.frogobox.coreutil.sport.SportUrl.API_KEY)
    }

    single {
        ConsumeTheMealDbApi(com.frogobox.coreutil.meal.MealUrl.API_KEY)
    }

    single {
        ConsumePixabayApi(com.frogobox.coreutil.pixabay.PixabayUrl.API_KEY)
    }

    single {
        ConsumeMovieApi(com.frogobox.coreutil.movie.MovieUrl.API_KEY)
    }

}