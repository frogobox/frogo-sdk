package com.frogobox.coreutil.movie.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * consumable-code-movie-tmdb-api
 * Copyright (C) 29/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.movie.model
 *
 */
data class TrendingTv(

    @SerializedName("original_name")
    var original_name: String? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("vote_count")
    var vote_count: Int? = null,

    @SerializedName("vote_average")
    var vote_average: Double? = null,

    @SerializedName("first_air_date")
    var first_air_date: String? = null,

    @SerializedName("poster_path")
    var poster_path: String? = null,

    @SerializedName("genre_ids")
    var genre_ids: List<Int>? = null,

    @SerializedName("original_language")
    var original_language: String? = null,

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("origin_country")
    var origin_country: List<String>? = null,

    @SerializedName("popularity")
    var popularity: Double? = null,

    @SerializedName("media_type")
    var media_type: String? = null

)