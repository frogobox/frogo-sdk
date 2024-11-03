package com.frogobox.coreapi.pixabay

import com.frogobox.coreutil.pixabay.model.PixabayImage
import com.frogobox.coreutil.pixabay.model.PixabayVideo
import com.frogobox.coreutil.pixabay.response.Response
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

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
 * com.frogobox.frogoconsumeapi.pixabay.data.source
 *
 */
interface PixabayApiService {

    // Search for Image
    @GET(com.frogobox.coreutil.pixabay.PixabayUrl.PATH_IMAGE)
    fun searchImage(
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_Q) q: String,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_LANG) lang: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_ID) id: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_IMAGE_TYPE) imageType: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_ORIENTATION) orientation: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_CATEGORY) category: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_MIN_WIDTH) minWidth: Int?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_MIN_HEIGHT) minHeight: Int?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_COLORS) colors: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_EDITORS_CHOICE) editorsChoice: Boolean?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_SAFE_SEARCH) safeSearch: Boolean?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_ORDER) order: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_PER_PAGE) perPage: Int?
    ): Observable<com.frogobox.coreutil.pixabay.response.Response<com.frogobox.coreutil.pixabay.model.PixabayImage>>

    // Search for Video
    @GET(com.frogobox.coreutil.pixabay.PixabayUrl.PATH_VIDEO)
    fun searchVideo(
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_Q) q: String,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_LANG) lang: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_ID) id: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_VIDEO_TYPE) videoType: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_CATEGORY) category: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_MIN_WIDTH) minWidth: Int?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_MIN_HEIGHT) minHeight: Int?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_EDITORS_CHOICE) editorsChoice: Boolean?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_SAFE_SEARCH) safeSearch: Boolean?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_ORDER) order: String?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.pixabay.PixabayConstant.QUERY_PER_PAGE) perPage: Int?
    ): Observable<com.frogobox.coreutil.pixabay.response.Response<com.frogobox.coreutil.pixabay.model.PixabayVideo>>

}