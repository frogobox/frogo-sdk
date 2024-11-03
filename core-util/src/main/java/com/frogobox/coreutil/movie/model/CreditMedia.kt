package com.frogobox.coreutil.movie.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CreditMedia(

    @SerializedName("original_name")
    var original_name: String? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("vote_count")
    var vote_count: Int? = null,

    @SerializedName("vote_average")
    var vote_average: Double? = null,

    @SerializedName("first_air_date")
    var first_air_date: String? = null,

    @SerializedName("poster_path")
    var poster_path: String? = null,

    @SerializedName("genre_ids")
    var genre_ids: List<Int>? = null,

    @SerializedName("original_language")
    var original_language: String? = null,

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("origin_country")
    var origin_country: List<String>? = null,

    @SerializedName("popularity")
    var popularity: Double? = null,

    @SerializedName("character")
    var character: String? = null,

    @SerializedName("episodes")
    var episodes: List<Objects>? = null,

    @SerializedName("seasons")
    var seasons: List<com.frogobox.coreutil.movie.model.CreditMediaSeason>? = null

)
