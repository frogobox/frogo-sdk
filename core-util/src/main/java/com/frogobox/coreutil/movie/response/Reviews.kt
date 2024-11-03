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
data class Reviews(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("author")
    var author: String? = null,

    @SerializedName("content")
    var content: String? = null,

    @SerializedName("iso_639_1")
    var iso_639_1: String? = null,

    @SerializedName("media_id")
    var media_id: Int? = null,

    @SerializedName("media_title")
    var media_title: String? = null,

    @SerializedName("media_type")
    var media_type: String? = null,

    @SerializedName("url")
    var url: String? = null

)