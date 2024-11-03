package com.frogobox.appapi.source

import com.frogobox.api.meal.ConsumeTheMealDbApi
import com.frogobox.api.movie.ConsumeMovieApi
import com.frogobox.api.news.ConsumeNewsApi
import com.frogobox.api.pixabay.ConsumePixabayApi
import com.frogobox.api.sport.ConsumeTheSportDbApi


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

interface ApiDataSource {

    fun consumeNewsApi(): ConsumeNewsApi

    fun consumePixabayApi(): ConsumePixabayApi

    fun consumeMovieApi(): ConsumeMovieApi

    fun consumeTheSportDbApi(): ConsumeTheSportDbApi

    fun consumeTheMealDbApi(): ConsumeTheMealDbApi

}