package com.frogobox.coreutil.movie.model

import com.google.gson.annotations.SerializedName

data class CreditMediaSeason(

    @SerializedName("air_date")
    var air_date: String? = null,

    @SerializedName("episode_count")
    var episode_count: Int? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("poster_path")
    var poster_path: String? = null,

    @SerializedName("season_number")
    var season_number: Int? = null,

    @SerializedName("show_id")
    var show_id: Int? = null

)
