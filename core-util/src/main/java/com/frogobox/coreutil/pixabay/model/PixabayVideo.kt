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
data class PixabayVideo(

    @SerializedName("userImageURL")
    var id: Int? = null,

    @SerializedName("userImageURL")
    var pageURL: String? = null,

    @SerializedName("userImageURL")
    var type: String? = null,

    @SerializedName("userImageURL")
    var tags: String? = null,

    @SerializedName("userImageURL")
    var duration: Int? = null,

    @SerializedName("userImageURL")
    var picture_id: String? = null,

    @SerializedName("userImageURL")
    var videos: String? = null,

    @SerializedName("userImageURL")
    var views: Int? = null,

    @SerializedName("userImageURL")
    var downloads: Int? = null,

    @SerializedName("userImageURL")
    var favorites: Int? = null,

    @SerializedName("userImageURL")
    var likes: Int? = null,

    @SerializedName("userImageURL")
    var comments: Int? = null,

    @SerializedName("userImageURL")
    var user_id: Int? = null,

    @SerializedName("userImageURL")
    var user: String? = null,

    @SerializedName("userImageURL")
    var userImageURL: String? = null

)