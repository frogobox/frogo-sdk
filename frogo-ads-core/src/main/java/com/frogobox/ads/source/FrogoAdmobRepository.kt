package com.frogobox.ads.source

import android.content.Context
import com.frogobox.ads.model.FrogoAdmobId
import com.frogobox.ads.model.FrogoMonetizeId
import com.frogobox.ads.model.FrogoUnityId
import com.frogobox.coresdk.source.FrogoApiClient
import com.frogobox.sdk.ext.usingChuck
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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

    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var frogoAdmobApiService = FrogoApiClient.create<FrogoAdmobApiService>(baseUrl, isDebug)

    override fun usingClient(context: Context) {
        frogoAdmobApiService =
            FrogoApiClient.create(
                url = baseUrl,
                isDebug = isDebug,
                chuckInterceptor = context.usingChuck()
            )
    }

    private fun <T> executeRequest(
        request: suspend () -> T,
        callback: FrogoAdmobApiResponse<T>
    ) {
        callback.onShowProgress()
        repositoryScope.launch {
            try {
                val data = request()
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onSuccess(data)
                    callback.onFinish()
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onHideProgress()
                    callback.onFailed(500, e.message ?: "Unknown Error")
                    callback.onFinish()
                }
            }
        }
    }

    override fun getFrogoAdmobId(
        jsonFileName: String,
        callback: FrogoAdmobApiResponse<FrogoAdmobId>,
    ) {
        executeRequest({ frogoAdmobApiService.getFrogoAdmobId(jsonFileName) }, callback)
    }

    override fun getFrogoMonetizeId(
        jsonFileName: String,
        callback: FrogoAdmobApiResponse<FrogoMonetizeId>,
    ) {
        executeRequest({ frogoAdmobApiService.getMonetizeId(jsonFileName) }, callback)
    }

    override fun getFrogoUnityId(
        jsonFileName: String,
        callback: FrogoAdmobApiResponse<FrogoUnityId>,
    ) {
        executeRequest({ frogoAdmobApiService.getUnityId(jsonFileName) }, callback)
    }

    // ---------------------------------------------------------------------------------------------
    // Modern Coroutine Suspend Functions
    // ---------------------------------------------------------------------------------------------

    override suspend fun fetchFrogoAdmobId(jsonFileName: String): Result<FrogoAdmobId> = runCatching {
        withContext(Dispatchers.IO) {
            frogoAdmobApiService.getFrogoAdmobId(jsonFileName)
        }
    }

    override suspend fun fetchFrogoMonetizeId(jsonFileName: String): Result<FrogoMonetizeId> = runCatching {
        withContext(Dispatchers.IO) {
            frogoAdmobApiService.getMonetizeId(jsonFileName)
        }
    }

    override suspend fun fetchFrogoUnityId(jsonFileName: String): Result<FrogoUnityId> = runCatching {
        withContext(Dispatchers.IO) {
            frogoAdmobApiService.getUnityId(jsonFileName)
        }
    }

}