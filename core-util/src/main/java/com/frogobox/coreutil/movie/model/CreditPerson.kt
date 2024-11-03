package com.frogobox.coreutil.movie.model

import com.google.gson.annotations.SerializedName

data class CreditPerson(

    @SerializedName("known_for_department")
    var known_for_department: String? = null,

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("profile_path")
    var profile_path: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("known_for")
    var known_for: List<com.frogobox.coreutil.movie.model.CreditPersonKnownFor>? = null,

    @SerializedName("gender")
    var gender: Int? = null,

    @SerializedName("popularity")
    var popularity: Double? = null

)
