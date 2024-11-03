package com.frogobox.coreutil.pixabay.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PixabayAPI
 * Copyright (C) 14/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.pixabay.data.model
 *
 */
data class PixabayVideoType(

    @SerializedName("large")
    var large: com.frogobox.coreutil.pixabay.model.PixabayVideoType? = null,

    @SerializedName("medium")
    var medium: com.frogobox.coreutil.pixabay.model.PixabayVideoType? = null,

    @SerializedName("small")
    var small: com.frogobox.coreutil.pixabay.model.PixabayVideoType? = null,

    @SerializedName("tiny")
    var tiny: com.frogobox.coreutil.pixabay.model.PixabayVideoType? = null
)