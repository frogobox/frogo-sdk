package com.frogobox.appadmob.source

import android.content.Context
import com.frogobox.R
import com.frogobox.ads.core.FrogoAdmob
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.ext.showLogError
import com.frogobox.sdk.source.FrogoRemoteDataSource
import com.google.android.libraries.ads.mobile.sdk.common.AdLoadCallback
import com.google.android.libraries.ads.mobile.sdk.common.AdRequest
import com.google.android.libraries.ads.mobile.sdk.common.LoadAdError
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAd
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by faisalamir on 19/04/22
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

class AdmobRemoteDataSource : FrogoRemoteDataSource(), AdmobDataSource {

    override fun getInterstitial(context: Context, callback: FrogoDataResponse<InterstitialAd>) {
        callback.onShowProgress()
        val request = AdRequest.Builder(context.getString(R.string.admob_interstitial)).build()
        InterstitialAd.load(
            request,
            object : AdLoadCallback<InterstitialAd> {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    showLogError("${FrogoAdmob.TAG} [Interstitial] >> Run - IFrogoAdInterstitial [callback] : onAdFailedToLoad()")
                    showLogError("${FrogoAdmob.TAG} [Interstitial] >> Error - onAdFailedToLoad [code] : ${adError.code}")
                    showLogError("${FrogoAdmob.TAG} [Interstitial] >> Error - onAdFailedToLoad [message] : ${adError.message}")
                    CoroutineScope(Dispatchers.Main).launch {
                        callback.onHideProgress()
                        callback.onFailed(adError.code.ordinal, adError.message)
                        callback.onFinish()
                    }
                }

                override fun onAdLoaded(ad: InterstitialAd) {
                    showLogDebug("${FrogoAdmob.TAG} [Interstitial] >> Run - IFrogoAdInterstitial [callback] : onAdLoaded()")
                    showLogDebug("${FrogoAdmob.TAG} [Interstitial] >> Succes - onAdLoaded [message] : Ad was loaded")
                    showLogDebug("${FrogoAdmob.TAG} [Interstitial] >> Succes - onAdLoaded [unit id] : ${ad.adUnitId}")
                    showLogDebug("${FrogoAdmob.TAG} [Interstitial] >> Suggest : You Can Give Your Reward Here")
                    showLogDebug(Gson().toJson(ad))
                    CoroutineScope(Dispatchers.Main).launch {
                        callback.onHideProgress()
                        callback.onSuccess(ad)
                        callback.onFinish()
                    }
                }
            }
        )
    }

    override fun saveInterstitial(interstitialAd: InterstitialAd) {
    }
}