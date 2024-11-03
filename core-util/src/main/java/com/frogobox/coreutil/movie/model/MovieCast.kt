package com.frogobox.coreutil.movie.model

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
data class MovieCast(

    @SerializedName("cast_id")
    var cast_id: Int? = null,

    @SerializedName("character")
    var character: String? = null,

    @SerializedName("credit_id")
    var credit_id: String? = null,

    @SerializedName("gender")
    var gender: Int? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("order")
    var order: Int? = null,

    @SerializedName("profile_path")
    var profile_path: String? = null

)