package com.frogobox.coreutil.movie.model

import com.google.gson.annotations.SerializedName

data class NetworkLogo(

    @SerializedName("aspect_ratio")
    var aspect_ratio: Int? = null,

    @SerializedName("file_path")
    var file_path: String? = null,

    @SerializedName("height")
    var height: Int? = null,

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("file_type")
    var file_type: String? = null,

    @SerializedName("vote_average")
    var vote_average: Int? = null,

    @SerializedName("vote_count")
    var vote_count: Int? = null,

    @SerializedName("width")
    var width: Int? = null

)
