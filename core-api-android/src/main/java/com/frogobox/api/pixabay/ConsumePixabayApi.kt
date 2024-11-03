package com.frogobox.api.pixabay

import android.content.Context
import com.frogobox.coreapi.pixabay.IPixabayApi
import com.frogobox.coreapi.pixabay.PixabayApi
import com.frogobox.coreutil.pixabay.model.PixabayImage
import com.frogobox.coreutil.pixabay.model.PixabayVideo
import com.frogobox.coreutil.pixabay.response.Response
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.sdk.ext.usingChuck
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import okhttp3.Interceptor

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
 * com.frogobox.frogoconsumeapi.pixabay
 *
 */
class ConsumePixabayApi(apiKey: String) : IConsumePixabayApi {

    private var pixabayApi = PixabayApi(AndroidSchedulers.mainThread(), apiKey)

    override fun usingChuckInterceptor(isDebug: Boolean, context: Context): IPixabayApi {
        return usingChuckInterceptor(isDebug, context.usingChuck())
    }

    override fun usingChuckInterceptor(
        isDebug: Boolean,
        chuckerInterceptor: Interceptor
    ): IPixabayApi {
        return pixabayApi.usingChuckInterceptor(isDebug, chuckerInterceptor)
    }

    override fun searchImage(
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
    ) {
        pixabayApi.searchImage(
            q,
            lang,
            id,
            imageType,
            orientation,
            category,
            minWidth,
            minHeight,
            colors,
            editorsChoice,
            safeSearch,
            order,
            page,
            perPage,
            callback
        )
    }

    override fun searchVideo(
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
    ) {
        pixabayApi.searchVideo(
            q,
            lang,
            id,
            videoType,
            category,
            minWidth,
            minHeight,
            editorsChoice,
            safeSearch,
            order,
            page,
            perPage,
            callback
        )
    }
}