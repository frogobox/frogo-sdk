package com.frogobox.ads.ext

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.google.android.libraries.ads.mobile.sdk.common.AdLoadCallback
import com.google.android.libraries.ads.mobile.sdk.common.AdRequest
import com.google.android.libraries.ads.mobile.sdk.common.FullScreenContentError
import com.google.android.libraries.ads.mobile.sdk.common.LoadAdError
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAd
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAdEventCallback


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by faisalamir on 12/04/22
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

private const val TAG: String = "FrogoAdmobInterstitialExt"

private fun runOnMainThread(action: () -> Unit) {
    CoroutineScope(Dispatchers.Main).launch {
        action()
    }
}

fun InterstitialAd.showAd(
    activity: AppCompatActivity,
    interstitialAdUnitId: String,
    timeoutMilliSecond: Int?,
    keyword: List<String>?,
    callback: FrogoAdmobInterstitialCallback?
) {

    if (interstitialAdUnitId.isNotBlank()) {

        callback?.onShowAdRequestProgress(
            TAG,
            "$TAG [Interstitial] >> Run - FrogoAdmobInterstitialCallback [callback] : onShowAdRequestProgress()"
        )

        val adRequestBuilder = AdRequest.Builder(interstitialAdUnitId)
        keyword?.forEach { adRequestBuilder.putCustomTargeting("keyword", it) }

        InterstitialAd.load(
            adRequestBuilder.build(),
            object : AdLoadCallback<InterstitialAd> {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    runOnMainThread {
                        callback?.onHideAdRequestProgress(
                            TAG,
                            "$TAG [Interstitial] >> Error - onAdFailedToLoad [message] : ${adError.message}"
                        )
                        callback?.onAdFailed(TAG, "Interstitial ${adError.message}")
                    }
                }

                override fun onAdLoaded(ad: InterstitialAd) {
                    runOnMainThread {
                        callback?.onAdLoaded(TAG, "Interstitial Ad was loaded")
                    }

                    ad.adEventCallback =
                        object : InterstitialAdEventCallback {
                            override fun onAdDismissedFullScreenContent() {
                                runOnMainThread {
                                    callback?.onAdDismissed(
                                        TAG,
                                        "Interstitial Ad was dismissed"
                                    )
                                }
                            }

                            override fun onAdFailedToShowFullScreenContent(fullScreenContentError: FullScreenContentError) {
                                runOnMainThread {
                                    callback?.onHideAdRequestProgress(
                                        TAG,
                                        "$TAG [Interstitial] >> Error - onAdFailedToShowFullScreenContent: ${fullScreenContentError.message}"
                                    )
                                    callback?.onAdFailed(
                                        TAG,
                                        "Interstitial Ad failed to show: ${fullScreenContentError.message}"
                                    )
                                }
                            }

                            override fun onAdShowedFullScreenContent() {
                                runOnMainThread {
                                    callback?.onHideAdRequestProgress(
                                        TAG,
                                        "Interstitial Ad showed fullscreen content"
                                    )
                                    callback?.onAdShowed(
                                        TAG,
                                        "Interstitial Ad showed fullscreen content"
                                    )
                                }
                            }
                        }
                    runOnMainThread {
                        ad.show(activity)
                    }
                }
            }
        )
    } else {
        callback?.onAdFailed(TAG, "$TAG Interstitial ID is Empty")
    }

}