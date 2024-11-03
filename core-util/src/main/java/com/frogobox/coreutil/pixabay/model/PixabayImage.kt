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
data class PixabayImage(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("pageURL")
    var pageURL: String? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("tags")
    var tags: String? = null,

    @SerializedName("previewURL")
    var previewURL: String? = null,

    @SerializedName("previewWidth")
    var previewWidth: Int? = null,

    @SerializedName("previewHeight")
    var previewHeight: Int? = null,

    @SerializedName("webformatURL")
    var webformatURL: String? = null,

    @SerializedName("webformatWidth")
    var webformatWidth: Int? = null,

    @SerializedName("webformatHeight")
    var webformatHeight: Int? = null,

    @SerializedName("largeImageURL")
    var largeImageURL: String? = null,

    @SerializedName("imageWidth")
    var imageWidth: Int? = null,

    @SerializedName("imageHeight")
    var imageHeight: Int? = null,

    @SerializedName("imageSize")
    var imageSize: Int? = null,

    @SerializedName("views")
    var views: Int? = null,

    @SerializedName("downloads")
    var downloads: Int? = null,

    @SerializedName("favorites")
    var favorites: Int? = null,

    @SerializedName("likes")
    var likes: Int? = null,

    @SerializedName("comments")
    var comments: Int? = null,

    @SerializedName("user_id")
    var user_id: Int? = null,

    @SerializedName("user:")
    var user: String? = null,

    @SerializedName("userImageURL")
    var userImageURL: String? = null

)