package com.frogobox.coreutil.movie.response

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
 * com.frogobox.frogoconsumeapi.movie.data.response
 *
 */
data class Credits(

    @SerializedName("credit_type")
    var credit_type: String? = null,

    @SerializedName("department")
    var department: String? = null,

    @SerializedName("job")
    var job: String? = null,

    @SerializedName("media")
    var media: com.frogobox.coreutil.movie.model.CreditMedia? = null,

    @SerializedName("media_type")
    var media_type: String? = null,

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("person")
    var person: com.frogobox.coreutil.movie.model.CreditPerson? = null

)