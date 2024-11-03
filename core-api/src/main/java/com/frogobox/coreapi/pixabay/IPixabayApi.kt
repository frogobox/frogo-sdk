package com.frogobox.coreapi.pixabay

import com.frogobox.coreutil.pixabay.model.PixabayImage
import com.frogobox.coreutil.pixabay.model.PixabayVideo
import com.frogobox.coreutil.pixabay.response.Response
import com.frogobox.coresdk.response.FrogoDataResponse
import okhttp3.Interceptor


/*
 * Created by faisalamir on 07/04/22
 * FrogoConsumeApi
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

interface IPixabayApi {

    // Switch For Using Chuck Interceptor
    fun usingChuckInterceptor(isDebug: Boolean, chuckerInterceptor: Interceptor): IPixabayApi

    // Search for Image
    fun searchImage(
        q: String,
        lang: String?,
        id: String?,
        imageType: String?,
        orientation: String?,
        category: String?,
        minWidth: Int?,
        minHeight: Int?,
        colors: String?,
        editorsChoice: Boolean?,
        safeSearch: Boolean?,
        order: String?,
        page: Int?,
        perPage: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.pixabay.response.Response<com.frogobox.coreutil.pixabay.model.PixabayImage>>
    )

    // Search for Video
    fun searchVideo(
        q: String,
        lang: String?,
        id: String?,
        videoType: String?,
        category: String?,
        minWidth: Int?,
        minHeight: Int?,
        editorsChoice: Boolean?,
        safeSearch: Boolean?,
        order: String?,
        page: Int?,
        perPage: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.pixabay.response.Response<com.frogobox.coreutil.pixabay.model.PixabayVideo>>
    )

}