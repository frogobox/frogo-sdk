package com.frogobox.coreutil.movie.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * consumable-code-movie-tmdb-api
 * Copyright (C) 26/03/2020.
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
data class ConfigurationApiImage(

    @SerializedName("base_url")
    var base_url: String? = null,

    @SerializedName("secure_base_url")
    var secure_base_url: String? = null,

    @SerializedName("backdrop_sizes")
    var backdrop_sizes: List<String>? = null,

    @SerializedName("logo_sizes")
    var logo_sizes: List<String>? = null,

    @SerializedName("poster_sizes")
    var poster_sizes: List<String>? = null,

    @SerializedName("profile_sizes")
    var profile_sizes: List<String>? = null,

    @SerializedName("still_sizes")
    var still_sizes: List<String>? = null

)