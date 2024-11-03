package com.frogobox.coreutil.movie.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * consumable-code-movie-tmdb-api
 * Copyright (C) 28/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.movie.data.response
 *
 */
data class KeywordsMovies(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("page")
    var page: Int? = null,

    @SerializedName("total_pages")
    var total_pages: Int? = null,

    @SerializedName("total_results")
    var total_results: Int? = null,

    @SerializedName("results")
    var results: List<com.frogobox.coreutil.movie.model.KeywordMovie>? = null

)