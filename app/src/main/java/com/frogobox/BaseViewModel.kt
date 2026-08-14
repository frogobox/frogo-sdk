package com.frogobox

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.core.FrogoAdmob
import com.frogobox.appadmob.source.AdmobRepository
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.ext.showLogError
import com.frogobox.sdk.view.FrogoViewModel
import com.google.android.libraries.ads.mobile.sdk.common.FullScreenContentError
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAd
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAdEventCallback


/**
 * Created by faisalamir on 19/04/22
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

open class BaseViewModel(
    private val context: Context,
    private val repository: AdmobRepository
) : FrogoViewModel() {

    fun showInterstitial(activity: AppCompatActivity, callback: FrogoAdmobInterstitialCallback?) {
        repository.getInterstitial(context, object : FrogoDataResponse<InterstitialAd> {
            override fun onFinish() {}

            override fun onFailed(statusCode: Int, errorMessage: String) {
                callback?.onAdFailed(statusCode.toString(), errorMessage)
            }

            override fun onHideProgress() {
                callback?.onHideAdRequestProgress("", "")
            }

            override fun onShowProgress() {
                callback?.onShowAdRequestProgress("", "")
            }

            override fun onSuccess(data: InterstitialAd) {
                callback?.onAdLoaded("", "")
                data.adEventCallback =
                    object : InterstitialAdEventCallback {
                        override fun onAdDismissedFullScreenContent() {
                            showLogDebug("${FrogoAdmob.TAG} [Interstitial] >> Run - IFrogoAdInterstitial [callback] : onAdDismissed()")
                            showLogDebug("${FrogoAdmob.TAG} [Interstitial] >> Succes - onAdDismissedFullScreenContent [message] : Ad was dismissed")
                            callback?.onAdDismissed(FrogoAdmob.TAG, "Interstitial Ad was dismissed")
                        }

                        override fun onAdFailedToShowFullScreenContent(fullScreenContentError: FullScreenContentError) {
                            showLogError("${FrogoAdmob.TAG} [Interstitial] >> Run - IFrogoAdInterstitial [callback] : onAdFailedToShow()")
                            showLogError("${FrogoAdmob.TAG} [Interstitial] >> Error - onAdFailedToShowFullScreenContent [unit id] : $")
                            showLogError("${FrogoAdmob.TAG} [Interstitial] >> Error - onAdFailedToShowFullScreenContent [code] : ${fullScreenContentError.code}")
                            showLogError("${FrogoAdmob.TAG} [Interstitial] >> Error - onAdFailedToShowFullScreenContent [message] : ${fullScreenContentError.message}")
                            showLogError("${FrogoAdmob.TAG} [Interstitial] >> Error : Ad failed to show")
                            callback?.onHideAdRequestProgress(
                                FrogoAdmob.TAG,
                                "${FrogoAdmob.TAG} [Interstitial] >> Error - onHideAdRequestProgress [message] : onAdFailedToShowFullScreenContent"
                            )
                            callback?.onAdFailed(FrogoAdmob.TAG, "Interstitial Ad failed to show")
                        }

                        override fun onAdShowedFullScreenContent() {
                            showLogDebug("${FrogoAdmob.TAG} [Interstitial] >> Run - IFrogoAdInterstitial [callback] : onAdShowed()")
                            showLogDebug("${FrogoAdmob.TAG} [Interstitial] >> Succes - onAdShowedFullScreenContent [message] : Ad showed fullscreen content")
                            callback?.onHideAdRequestProgress(
                                FrogoAdmob.TAG,
                                "${FrogoAdmob.TAG} [Interstitial] >> Succes - onHideAdRequestProgress [message] : Ad showed fullscreen content"
                            )
                            callback?.onAdShowed(
                                FrogoAdmob.TAG,
                                "Interstitial Ad showed fullscreen content"
                            )
                        }
                    }
                data.show(activity)
            }
        })
    }
}