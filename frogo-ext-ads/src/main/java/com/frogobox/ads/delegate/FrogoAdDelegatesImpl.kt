package com.frogobox.ads.delegate

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback

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

class FrogoAdDelegatesImpl : FrogoAdDelegates,
    AdmobDelegates by AdmobDelegatesImpl(),
    UnityAdDelegates by UnityAdDelegatesImpl() {

    override fun setupFrogoAdDelegates(activity: AppCompatActivity) {
        setupAdmobDelegates(activity)
        setupUnityAdDelegates(activity)
    }

    private fun mapToUnityCallback(callback: FrogoAdInterstitialCallback): FrogoUnityAdInterstitialCallback {
        return object : FrogoUnityAdInterstitialCallback {
            override fun onClicked(tag: String, message: String) = callback.onClicked(tag, message)
            override fun onShowAdRequestProgress(tag: String, message: String) = callback.onShowAdRequestProgress(tag, message)
            override fun onHideAdRequestProgress(tag: String, message: String) = callback.onHideAdRequestProgress(tag, message)
            override fun onAdDismissed(tag: String, message: String) = callback.onAdDismissed(tag, message)
            override fun onAdFailed(tag: String, errorMessage: String) = callback.onAdFailed(tag, errorMessage)
            override fun onAdLoaded(tag: String, message: String) = callback.onAdLoaded(tag, message)
            override fun onAdShowed(tag: String, message: String) = callback.onAdShowed(tag, message)
        }
    }

    private fun mapToAdmobCallback(
        callback: FrogoAdInterstitialCallback,
        onFailedFallback: ((String, String) -> Unit)? = null
    ): FrogoAdmobInterstitialCallback {
        return object : FrogoAdmobInterstitialCallback {
            override fun onShowAdRequestProgress(tag: String, message: String) = callback.onShowAdRequestProgress(tag, message)
            override fun onHideAdRequestProgress(tag: String, message: String) = callback.onHideAdRequestProgress(tag, message)
            override fun onAdDismissed(tag: String, message: String) = callback.onAdDismissed(tag, message)
            override fun onAdFailed(tag: String, errorMessage: String) {
                if (onFailedFallback != null) {
                    onFailedFallback(tag, errorMessage)
                } else {
                    callback.onAdFailed(tag, errorMessage)
                }
            }
            override fun onAdLoaded(tag: String, message: String) = callback.onAdLoaded(tag, message)
            override fun onAdShowed(tag: String, message: String) = callback.onAdShowed(tag, message)
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Hybrid Ads: AdMob -> Unity Ads
    // ---------------------------------------------------------------------------------------------

    override fun showAdmobXUnityAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        callback: FrogoAdInterstitialCallback
    ) {
        showAdmobXUnityAdInterstitialInternal(admobInterstitialId, unityInterstitialId, null, callback)
    }

    override fun showAdmobXUnityAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        timeout: Int,
        callback: FrogoAdInterstitialCallback
    ) {
        showAdmobXUnityAdInterstitialInternal(admobInterstitialId, unityInterstitialId, timeout, callback)
    }

    private fun showAdmobXUnityAdInterstitialInternal(
        admobInterstitialId: String,
        unityInterstitialId: String,
        timeout: Int?,
        callback: FrogoAdInterstitialCallback
    ) {
        if (admobInterstitialId.isBlank()) {
            showUnityAdInterstitial(unityInterstitialId, mapToUnityCallback(callback))
        } else {
            val admobCb = mapToAdmobCallback(callback) { tag, errorMessage ->
                if (unityInterstitialId.isBlank()) {
                    callback.onAdFailed(tag, errorMessage)
                } else {
                    showUnityAdInterstitial(unityInterstitialId, mapToUnityCallback(callback))
                }
            }
            if (timeout != null) {
                showAdInterstitial(admobInterstitialId, timeout, callback = admobCb)
            } else {
                showAdInterstitial(admobInterstitialId, callback = admobCb)
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Hybrid Ads: Unity Ads -> AdMob
    // ---------------------------------------------------------------------------------------------

    override fun showUnityXAdmobAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        callback: FrogoAdInterstitialCallback
    ) {
        showUnityXAdmobAdInterstitialInternal(admobInterstitialId, unityInterstitialId, null, callback)
    }

    override fun showUnityXAdmobAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        timeout: Int,
        callback: FrogoAdInterstitialCallback
    ) {
        showUnityXAdmobAdInterstitialInternal(admobInterstitialId, unityInterstitialId, timeout, callback)
    }

    private fun showUnityXAdmobAdInterstitialInternal(
        admobInterstitialId: String,
        unityInterstitialId: String,
        timeout: Int?,
        callback: FrogoAdInterstitialCallback
    ) {
        if (unityInterstitialId.isBlank()) {
            val admobCb = mapToAdmobCallback(callback)
            if (timeout != null) {
                showAdInterstitial(admobInterstitialId, timeout, callback = admobCb)
            } else {
                showAdInterstitial(admobInterstitialId, callback = admobCb)
            }
            return
        }

        showUnityAdInterstitial(unityInterstitialId, object : FrogoUnityAdInterstitialCallback {
            override fun onAdDismissed(tag: String, message: String) = callback.onAdDismissed(tag, message)
            override fun onAdFailed(tag: String, errorMessage: String) {
                if (admobInterstitialId.isBlank()) {
                    callback.onAdFailed(tag, errorMessage)
                } else {
                    val admobCb = mapToAdmobCallback(callback)
                    if (timeout != null) {
                        showAdInterstitial(admobInterstitialId, timeout, callback = admobCb)
                    } else {
                        showAdInterstitial(admobInterstitialId, callback = admobCb)
                    }
                }
            }
            override fun onAdLoaded(tag: String, message: String) = callback.onAdLoaded(tag, message)
            override fun onAdShowed(tag: String, message: String) = callback.onAdShowed(tag, message)
            override fun onClicked(tag: String, message: String) = callback.onClicked(tag, message)
            override fun onShowAdRequestProgress(tag: String, message: String) = callback.onShowAdRequestProgress(tag, message)
            override fun onHideAdRequestProgress(tag: String, message: String) = callback.onHideAdRequestProgress(tag, message)
        })
    }

}