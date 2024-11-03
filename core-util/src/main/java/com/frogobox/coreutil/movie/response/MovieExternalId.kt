package com.frogobox.coreutil.movie.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * consumable-code-movie-tmdb-api
 * Copyright (C) 05/04/2020.
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
data class MovieExternalId(

    @SerializedName("imdb_id")
    var imdb_id: String? = null,

    @SerializedName("facebook_id")
    var facebook_id: String? = null,

    @SerializedName("instagram_id")
    var instagram_id: String? = null,

    @SerializedName("twitter_id")
    var twitter_id: String? = null,

    @SerializedName("id")
    var id: Int? = null

)