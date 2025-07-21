package com.frogobox.ads.source

import android.content.Context
import com.frogobox.ads.model.FrogoAdmobId
import com.frogobox.ads.model.FrogoMonetizeId
import com.frogobox.ads.model.FrogoUnityId
import com.frogobox.coresdk.source.FrogoApiClient
import com.frogobox.sdk.ext.doApiRequest
import com.frogobox.sdk.ext.usingChuck


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


class FrogoAdmobRepository(
    private val isDebug: Boolean,
    private val baseUrl: String,
) : FrogoAdmobDataSource {

    companion object {
        val TAG = FrogoAdmobRepository::class.java.simpleName
    }

    private var frogoAdmobApiService = FrogoApiClient.create<FrogoAdmobApiService>(baseUrl, isDebug)

    override fun usingClient(context: Context) {
        frogoAdmobApiService =
            FrogoApiClient.create(
                url = baseUrl,
                isDebug = isDebug,
                chuckInterceptor = context.usingChuck()
            )
    }

    override fun getFrogoAdmobId(
        jsonFileName: String,
        callback: FrogoAdmobApiResponse<FrogoAdmobId>,
    ) {
        frogoAdmobApiService.getFrogoAdmobId(jsonFileName).doApiRequest(callback) {}
    }

    override fun getFrogoMonetizeId(
        jsonFileName: String,
        callback: FrogoAdmobApiResponse<FrogoMonetizeId>,
    ) {
        frogoAdmobApiService.getMonetizeId(jsonFileName).doApiRequest(callback) {}
    }

    override fun getFrogoUnityId(
        jsonFileName: String,
        callback: FrogoAdmobApiResponse<FrogoUnityId>,
    ) {
        frogoAdmobApiService.getUnityId(jsonFileName).doApiRequest(callback) {}
    }

}