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

class ApiRepository(
    private val consumeNewsApi: ConsumeNewsApi,
    private val consumePixabayApi: ConsumePixabayApi,
    private val consumeMovieApi: ConsumeMovieApi,
    private val consumeTheSportDbApi: ConsumeTheSportDbApi,
    private val consumeTheMealDbApi: ConsumeTheMealDbApi
) : ApiDataSource {

    override fun consumeNewsApi(): ConsumeNewsApi {
        return consumeNewsApi
    }

    override fun consumePixabayApi(): ConsumePixabayApi {
        return consumePixabayApi
    }

    override fun consumeMovieApi(): ConsumeMovieApi {
        return consumeMovieApi
    }

    override fun consumeTheSportDbApi(): ConsumeTheSportDbApi {
        return consumeTheSportDbApi
    }

    override fun consumeTheMealDbApi(): ConsumeTheMealDbApi {
        return consumeTheMealDbApi
    }

}