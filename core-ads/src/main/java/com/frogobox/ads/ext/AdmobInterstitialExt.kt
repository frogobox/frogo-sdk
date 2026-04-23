package com.frogobox.ads.ext

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAd.load
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


/**
 * Created by faisalamir on 12/04/22
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

private const val TAG: String = "FrogoAdmobInterstitialExt"


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

        val adRequest = AdRequest.Builder()

        if (timeoutMilliSecond != null) {
            adRequest.setHttpTimeoutMillis(timeoutMilliSecond)
        }

        keyword?.forEach { adRequest.addKeyword(it) }

        load(
            activity,
            interstitialAdUnitId,
            adRequest.build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    callback?.onHideAdRequestProgress(
                        TAG,
                        "$TAG [Interstitial] >> Error - onAdFailedToLoad [message] : ${adError.message}"
                    )
                    callback?.onAdFailed(TAG, "Interstitial ${adError.message}")
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    callback?.onAdLoaded(TAG, "Interstitial Ad was loaded")

                    interstitialAd.fullScreenContentCallback =
                        object : FullScreenContentCallback() {
                            override fun onAdDismissedFullScreenContent() {
                                callback?.onAdDismissed(
                                    TAG,
                                    "Interstitial Ad was dismissed"
                                )
                            }

                            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                callback?.onHideAdRequestProgress(
                                    TAG,
                                    "$TAG [Interstitial] >> Error - onAdFailedToShowFullScreenContent"
                                )
                                callback?.onAdFailed(
                                    TAG,
                                    "Interstitial Ad failed to show"
                                )
                            }

                            override fun onAdShowedFullScreenContent() {
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
                    interstitialAd.show(activity)
                }
            }
        )
    } else {
        callback?.onAdFailed(TAG, "$TAG Interstitial ID is Empty")
    }

}