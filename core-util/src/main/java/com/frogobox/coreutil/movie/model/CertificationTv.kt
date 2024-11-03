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
data class CertificationTv(

    @SerializedName("RU")
    var RU: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("US")
    var US: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("CA")
    var CA: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("AU")
    var AU: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("FR")
    var FR: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("DE")
    var DE: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("TH")
    var TH: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("KR")
    var KR: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("GB")
    var GB: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("BR")
    var BR: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("NL")
    var NL: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("CA-QC")
    var CAQC: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("HU")
    var HU: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("LT")
    var LT: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("PH")
    var PH: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("ES")
    var ES: List<com.frogobox.coreutil.movie.model.Certification>? = null,

    @SerializedName("SK")
    var SK: List<com.frogobox.coreutil.movie.model.Certification>? = null

)