package com.frogobox.coreutil.movie.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(

    @SerializedName("iso_639_1")
    var iso_639_1: String? = null,

    @SerializedName("name")
    var name: String? = null

)