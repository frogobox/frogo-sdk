package com.frogobox.coreutil.pixabay.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PixabayAPI
 * Copyright (C) 15/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.pixabay.data.response
 *
 */
data class Response<T>(
    @SerializedName("total")
    var total: Int? = null,

    @SerializedName("totalHits")
    var totalHits: Int? = null,

    @SerializedName("hits")
    var hits: List<T>? = null
)