package com.frogobox.coreutil.movie.response

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
 * com.frogobox.frogoconsumeapi.movie.data.response
 *
 */
data class MovieDetail(

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,

    @SerializedName("budget")
    var budget: Int? = null,

    @SerializedName("genres")
    var genres: List<com.frogobox.coreutil.movie.model.Genre>? = null,

    @SerializedName("homepage")
    var homepage: String? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("imdb_id")
    var imdb_id: String? = null,

    @SerializedName("original_language")
    var original_language: String? = null,

    @SerializedName("original_title")
    var original_title: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("popularity")
    var popularity: Double? = null,

    @SerializedName("poster_path")
    var poster_path: String? = null,

    @SerializedName("production_companies")
    var production_companies: List<com.frogobox.coreutil.movie.model.ProductionCompany>? = null,

    @SerializedName("production_countries")
    var production_countries: List<com.frogobox.coreutil.movie.model.ProductionCountry>? = null,

    @SerializedName("release_date")
    var release_date: String? = null,

    @SerializedName("revenue")
    var revenue: Int? = null,

    @SerializedName("runtime")
    var runtime: Int? = null,

    @SerializedName("spoken_languages")
    var spoken_languages: List<com.frogobox.coreutil.movie.model.SpokenLanguage>? = null,

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("tagline")
    var tagline: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("video")
    var video: Boolean? = null,

    @SerializedName("vote_average")
    var vote_average: Double? = null,

    @SerializedName("vote_count")
    var vote_count: Int? = null

)