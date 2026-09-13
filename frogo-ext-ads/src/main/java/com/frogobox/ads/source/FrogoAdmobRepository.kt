package com.frogobox.ads.source

import android.content.Context
import com.frogobox.ads.model.FrogoAdmobId
import com.frogobox.ads.model.FrogoMonetizeId
import com.frogobox.ads.model.FrogoUnityId
import com.frogobox.coresdk.source.FrogoApiClient
import com.frogobox.sdk.ext.usingChuck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by faisalamir on 02/03/22
 * FrogoAdmob
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
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
        callback.onShowProgress()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = frogoAdmobApiService.getFrogoAdmobId(jsonFileName)
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onSuccess(data)
                    callback.onFinish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onFailed(500, e.message ?: "Unknown Error")
                    callback.onFinish()
                }
            }
        }
    }

    override fun getFrogoMonetizeId(
        jsonFileName: String,
        callback: FrogoAdmobApiResponse<FrogoMonetizeId>,
    ) {
        callback.onShowProgress()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = frogoAdmobApiService.getMonetizeId(jsonFileName)
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onSuccess(data)
                    callback.onFinish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onFailed(500, e.message ?: "Unknown Error")
                    callback.onFinish()
                }
            }
        }
    }

    override fun getFrogoUnityId(
        jsonFileName: String,
        callback: FrogoAdmobApiResponse<FrogoUnityId>,
    ) {
        callback.onShowProgress()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = frogoAdmobApiService.getUnityId(jsonFileName)
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onSuccess(data)
                    callback.onFinish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onFailed(500, e.message ?: "Unknown Error")
                    callback.onFinish()
                }
            }
        }
    }

}