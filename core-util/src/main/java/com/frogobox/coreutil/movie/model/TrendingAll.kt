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
data class TrendingAll(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("video")
    var video: Boolean? = null,

    @SerializedName("vote_count")
    var vote_count: Int? = null,

    @SerializedName("vote_average")
    var vote_average: Double? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("release_date")
    var release_date: String? = null,

    @SerializedName("original_language")
    var original_language: String? = null,

    @SerializedName("original_title")
    var original_title: String? = null,

    @SerializedName("genre_ids")
    var genre_ids: List<Int>? = null,

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("poster_path")
    var poster_path: String? = null,

    @SerializedName("popularity")
    var popularity: Double? = null,

    @SerializedName("media_type")
    var media_type: String? = null

)