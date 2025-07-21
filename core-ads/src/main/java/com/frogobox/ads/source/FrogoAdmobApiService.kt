package com.frogobox.ads.source

import com.frogobox.ads.model.FrogoAdmobId
import com.frogobox.ads.model.FrogoMonetizeId
import com.frogobox.ads.model.FrogoUnityId
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by faisalamir on 02/03/22
 * FrogoAdmob
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */


interface FrogoAdmobApiService {

    @GET("{json-file-name}")
    fun getFrogoAdmobId(
        @Path("json-file-name") jsonFileName: String
    ): Observable<FrogoAdmobId>

    @GET("{json-file-name}")
    fun getMonetizeId(
        @Path("json-file-name") jsonFileName: String
    ): Observable<FrogoMonetizeId>

    @GET("{json-file-name}")
    fun getUnityId(
        @Path("json-file-name") jsonFileName: String
    ): Observable<FrogoUnityId>

}