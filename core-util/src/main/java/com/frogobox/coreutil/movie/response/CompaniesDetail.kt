package com.frogobox.coreutil.movie.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * consumable-code-movie-tmdb-api
 * Copyright (C) 24/03/2020.
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
data class CompaniesDetail(

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("headquarters")
    var headquarters: String? = null,

    @SerializedName("homepage")
    var homepage: String? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("logo_path")
    var logo_path: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("origin_country")
    var origin_country: String? = null,

    @SerializedName("parent_company")
    var parent_company: com.frogobox.coreutil.movie.model.CompanyParent? = null

)