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
data class MovieChangeItem(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("action")
    var action: String? = null,

    @SerializedName("time")
    var time: String? = null,

    @SerializedName("iso_639_1")
    var iso_639_1: Boolean? = null,

    @SerializedName("value")
    var value: String? = null,

    @SerializedName("original_value")
    var original_value: String? = null

)