package com.frogobox.ads.core

import android.app.Activity
import android.content.Context
import com.frogobox.ads.callback.FrogoUnityAdInitializationCallback
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback
import com.unity3d.ads.InitializationConfiguration
import com.unity3d.ads.InitializationListener
import com.unity3d.ads.InterstitialAd
import com.unity3d.ads.InterstitialShowListener
import com.unity3d.ads.LoadConfiguration
import com.unity3d.ads.LoadListener
import com.unity3d.ads.ShowConfiguration
import com.unity3d.ads.ShowFinishState
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAdsError

/**
 * Created by faisalamir on 22/03/22
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

object FrogoUnityAd : IFrogoUnityAd {

    val TAG: String = FrogoUnityAd::class.java.simpleName

    override fun setupUnityAdApp(
        context: Context,
        testMode: Boolean,
        unityGameId: String,
        callback: FrogoUnityAdInitializationCallback?
    ) {
        if (unityGameId.isNotBlank()) {
            if (!UnityAds.isInitialized) {
                val config = InitializationConfiguration.Builder(unityGameId)
                    .withTestMode(testMode)
                    .build()

                UnityAds.initialize(config, object : InitializationListener {
                    override fun onInitializationComplete(error: UnityAdsError?) {
                        if (error == null) {
                            callback?.onInitializationComplete(TAG, "$TAG : onInitializationComplete")
                        } else {
                            callback?.onInitializationFailed(
                                TAG,
                                "$TAG: onInitializationFailed with error message : ${error.message}"
                            )
                        }
                    }
                })
            }
        } else {
            callback?.onInitializationFailed(TAG, "$TAG : Unity Game Id is Empty")
        }
    }

    // ---------------------------------------------------------------------------------------------

    override fun showAdInterstitial(
        activity: Activity,
        adInterstitialUnitId: String,
        callback: FrogoUnityAdInterstitialCallback?
    ) {
        if (adInterstitialUnitId.isNotBlank()) {
            if (UnityAds.isInitialized) {
                callback?.onShowAdRequestProgress(TAG, "$TAG [Unity showAdInterstitial] >> Run - onShowAdRequestProgress")
                val loadConfig = LoadConfiguration.Builder(adInterstitialUnitId).build()
                InterstitialAd.load(loadConfig, object : LoadListener<InterstitialAd> {
                    override fun onAdLoaded(unityAd: InterstitialAd?, error: UnityAdsError?) {
                        if (unityAd != null) {
                            activity.runOnUiThread {
                                callback?.onAdLoaded(TAG, "$TAG : onUnityAdsAdLoaded $adInterstitialUnitId")
                            }
                            activity.runOnUiThread {
                                val showConfig = ShowConfiguration.Builder().build()
                                unityAd.show(activity, showConfig, object : InterstitialShowListener {
                                    override fun onStarted(unityAd: InterstitialAd) {
                                        activity.runOnUiThread {
                                            callback?.onHideAdRequestProgress(TAG, "$TAG [Unity showAdInterstitial] >> Run - onHideAdRequestProgress : onUnityAdsShowStart")
                                            callback?.onAdShowed(TAG, "$TAG [Unity showAdInterstitial] >> Succes - onUnityAdsShowStart [placementId] : $adInterstitialUnitId")
                                        }
                                    }

                                    override fun onClicked(unityAd: InterstitialAd) {
                                        activity.runOnUiThread {
                                            callback?.onClicked(TAG, "$TAG [Unity showAdInterstitial] >> Succes - onUnityAdsShowClick [placementId] : $adInterstitialUnitId")
                                        }
                                    }

                                    override fun onCompleted(unityAd: InterstitialAd, state: ShowFinishState) {
                                        activity.runOnUiThread {
                                            callback?.onAdDismissed(TAG, "$TAG [Unity showAdInterstitial] >> Succes - onUnityAdsShowComplete [state] : $state, [placement] : $adInterstitialUnitId")
                                        }
                                    }

                                    override fun onFailed(unityAd: InterstitialAd, error: UnityAdsError) {
                                        activity.runOnUiThread {
                                            callback?.onHideAdRequestProgress(TAG, "$TAG [Unity showAdInterstitial] >> Run - onHideAdRequestProgress : onUnityAdsShowFailure")
                                            callback?.onAdFailed(TAG, "$TAG [Unity showAdInterstitial] >> Error - onUnityAdsShowFailure [message] : ${error.message}")
                                        }
                                    }
                                })
                            }
                        } else {
                            activity.runOnUiThread {
                                callback?.onHideAdRequestProgress(TAG, "$TAG [Unity showAdInterstitial] >> Run - onHideAdRequestProgress : onUnityAdsShowFailure")
                                callback?.onAdFailed(TAG, "$TAG [Unity showAdInterstitial] >> Error - load failed : ${error?.message}")
                            }
                        }
                    }
                })
            } else {
                callback?.onAdFailed(TAG, "$TAG [Unity showAdInterstitial] >> Error - UnityAds Error Initialized [status] : ${UnityAds.isInitialized}")
            }
        } else {
            callback?.onAdFailed(TAG, "$TAG Unity Ad Interstitial id is Empty")
        }
    }

}