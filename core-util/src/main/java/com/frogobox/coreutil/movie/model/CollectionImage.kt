package com.frogobox.coreutil.movie.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * TMDBAPI
 * Copyright (C) 13/03/2020.
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
data class CollectionImage(

    @SerializedName("aspect_ratio")
    var aspect_ratio: Int? = null,

    @SerializedName("file_path")
    var file_path: String? = null,

    @SerializedName("height")
    var height: Int? = null,

    @SerializedName("iso_639_1")
    var iso_639_1: Int? = null,

    @SerializedName("vote_average")
    var vote_average: Double? = null,

    @SerializedName("vote_count")
    var vote_count: Int? = null,

    @SerializedName("width")
    var width: Int? = null

)