package com.frogobox.coreutil.movie.response

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
 * com.frogobox.frogoconsumeapi.movie.data.response
 *
 */
data class CollectionsImage(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("backdrops")
    var backdrops: List<com.frogobox.coreutil.movie.model.CollectionImage>? = null,

    @SerializedName("posters")
    var posters: List<com.frogobox.coreutil.movie.model.CollectionImage>? = null

)