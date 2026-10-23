package com.frogobox.ads.core

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.frogobox.ads.callback.FrogoAdmobAppOpenAdCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.google.android.libraries.ads.mobile.sdk.appopen.AppOpenAd
import com.google.android.libraries.ads.mobile.sdk.appopen.AppOpenAdEventCallback
import com.google.android.libraries.ads.mobile.sdk.appopen.AppOpenAdPreloader
import com.google.android.libraries.ads.mobile.sdk.common.AdRequest
import com.google.android.libraries.ads.mobile.sdk.common.FullScreenContentError
import com.google.android.libraries.ads.mobile.sdk.common.PreloadCallback
import com.google.android.libraries.ads.mobile.sdk.common.PreloadConfiguration
import com.google.android.libraries.ads.mobile.sdk.common.ResponseInfo
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAd
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAdEventCallback
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAdPreloader
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardItem
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardedAd
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardedAdEventCallback
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardedAdPreloader
import com.google.android.libraries.ads.mobile.sdk.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.libraries.ads.mobile.sdk.rewardedinterstitial.RewardedInterstitialAdEventCallback
import com.google.android.libraries.ads.mobile.sdk.rewardedinterstitial.RewardedInterstitialAdPreloader

/**
 * Modern Next-Gen Preloader manager for Google Mobile Ads SDK.
 * Wraps [InterstitialAdPreloader], [RewardedAdPreloader], [RewardedInterstitialAdPreloader],
 * and [AppOpenAdPreloader] adhering directly to official Google GMA Next-Gen SDK guidance.
 */
object FrogoAdmobPreloader {

    private const val TAG = "FrogoAdmobPreloader"
    private val mainHandler = Handler(Looper.getMainLooper())

    private fun runOnMainThread(action: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            action()
        } else {
            mainHandler.post(action)
        }
    }

    // =============================================================================================
    // Interstitial Ad Preloader
    // =============================================================================================

    /**
     * Starts preloading Interstitial ads in the background.
     * @param adUnitId The AdMob Interstitial ad unit ID.
     * @param bufferSize The number of preloaded ads to keep in the buffer (default: 1).
     * @param callback Optional [PreloadCallback] for monitoring preloading lifecycle events.
     */
    fun startInterstitialPreload(
        adUnitId: String,
        bufferSize: Int = 1,
        callback: PreloadCallback? = null
    ) {
        if (adUnitId.isBlank()) return
        runCatching {
            val adRequest = AdRequest.Builder(adUnitId).build()
            val preloadConfig = PreloadConfiguration(adRequest, bufferSize = bufferSize)
            if (callback != null) {
                InterstitialAdPreloader.start(adUnitId, preloadConfig, callback)
            } else {
                InterstitialAdPreloader.start(adUnitId, preloadConfig)
            }
        }.onFailure { Log.e(TAG, "Failed to start interstitial preload: ${it.message}") }
    }

    fun isInterstitialAvailable(adUnitId: String): Boolean {
        if (adUnitId.isBlank()) return false
        return runCatching { InterstitialAdPreloader.isAdAvailable(adUnitId) }.getOrDefault(false)
    }

    fun pollInterstitial(adUnitId: String): InterstitialAd? {
        if (adUnitId.isBlank()) return null
        return runCatching { InterstitialAdPreloader.pollAd(adUnitId) }.getOrNull()
    }

    fun peekInterstitialResponseInfo(adUnitId: String): ResponseInfo? {
        if (adUnitId.isBlank()) return null
        return runCatching { InterstitialAdPreloader.peekAdResponseInfo(adUnitId) }.getOrNull()
    }

    fun destroyInterstitial(adUnitId: String) {
        if (adUnitId.isBlank()) return
        runCatching { InterstitialAdPreloader.destroy(adUnitId) }
    }

    /**
     * Polls the next available preloaded Interstitial ad and displays it immediately.
     * Returns true if an ad was available and displayed, false otherwise.
     */
    fun pollAndShowInterstitial(
        activity: Activity,
        adUnitId: String,
        callback: FrogoAdmobInterstitialCallback? = null
    ): Boolean {
        if (activity.isFinishing || activity.isDestroyed) {
            callback?.onAdFailed(TAG, "Activity is finishing or destroyed")
            return false
        }
        val ad = pollInterstitial(adUnitId)
        if (ad == null) {
            callback?.onAdFailed(TAG, "Interstitial ad is not available in preloader buffer.")
            return false
        }

        ad.adEventCallback = object : InterstitialAdEventCallback {
            override fun onAdShowedFullScreenContent() {
                runOnMainThread {
                    callback?.onAdShowed(TAG, "Preloaded Interstitial ad showed")
                }
            }

            override fun onAdDismissedFullScreenContent() {
                runOnMainThread {
                    callback?.onAdDismissed(TAG, "Preloaded Interstitial ad dismissed")
                }
            }

            override fun onAdFailedToShowFullScreenContent(fullScreenContentError: FullScreenContentError) {
                runOnMainThread {
                    callback?.onAdFailed(TAG, "Preloaded Interstitial ad failed to show: ${fullScreenContentError.message}")
                }
            }

            override fun onAdImpression() {
                Log.d(TAG, "Preloaded Interstitial ad recorded an impression.")
            }

            override fun onAdClicked() {
                Log.d(TAG, "Preloaded Interstitial ad clicked.")
            }
        }

        runOnMainThread {
            ad.show(activity)
        }
        return true
    }

    // =============================================================================================
    // Rewarded Ad Preloader
    // =============================================================================================

    /**
     * Starts preloading Rewarded ads in the background.
     */
    fun startRewardedPreload(
        adUnitId: String,
        bufferSize: Int = 1,
        callback: PreloadCallback? = null
    ) {
        if (adUnitId.isBlank()) return
        runCatching {
            val adRequest = AdRequest.Builder(adUnitId).build()
            val preloadConfig = PreloadConfiguration(adRequest, bufferSize = bufferSize)
            if (callback != null) {
                RewardedAdPreloader.start(adUnitId, preloadConfig, callback)
            } else {
                RewardedAdPreloader.start(adUnitId, preloadConfig)
            }
        }.onFailure { Log.e(TAG, "Failed to start rewarded preload: ${it.message}") }
    }

    fun isRewardedAvailable(adUnitId: String): Boolean {
        if (adUnitId.isBlank()) return false
        return runCatching { RewardedAdPreloader.isAdAvailable(adUnitId) }.getOrDefault(false)
    }

    fun pollRewarded(adUnitId: String): RewardedAd? {
        if (adUnitId.isBlank()) return null
        return runCatching { RewardedAdPreloader.pollAd(adUnitId) }.getOrNull()
    }

    fun peekRewardedResponseInfo(adUnitId: String): ResponseInfo? {
        if (adUnitId.isBlank()) return null
        return runCatching { RewardedAdPreloader.peekAdResponseInfo(adUnitId) }.getOrNull()
    }

    fun destroyRewarded(adUnitId: String) {
        if (adUnitId.isBlank()) return
        runCatching { RewardedAdPreloader.destroy(adUnitId) }
    }

    /**
     * Polls the next available preloaded Rewarded ad and displays it immediately.
     */
    fun pollAndShowRewarded(
        activity: Activity,
        adUnitId: String,
        callback: FrogoAdmobRewardedCallback
    ): Boolean {
        if (activity.isFinishing || activity.isDestroyed) {
            callback.onAdFailed(TAG, "Activity is finishing or destroyed")
            return false
        }
        val ad = pollRewarded(adUnitId)
        if (ad == null) {
            callback.onAdFailed(TAG, "Rewarded ad is not available in preloader buffer.")
            return false
        }

        ad.adEventCallback = object : RewardedAdEventCallback {
            override fun onAdShowedFullScreenContent() {
                runOnMainThread {
                    callback.onAdShowed(TAG, "Preloaded Rewarded ad showed")
                }
            }

            override fun onAdDismissedFullScreenContent() {
                runOnMainThread {
                    callback.onAdDismissed(TAG, "Preloaded Rewarded ad dismissed")
                }
            }

            override fun onAdFailedToShowFullScreenContent(fullScreenContentError: FullScreenContentError) {
                runOnMainThread {
                    callback.onAdFailed(TAG, "Preloaded Rewarded ad failed to show: ${fullScreenContentError.message}")
                }
            }

            override fun onAdImpression() {
                Log.d(TAG, "Preloaded Rewarded ad recorded an impression.")
            }

            override fun onAdClicked() {
                Log.d(TAG, "Preloaded Rewarded ad clicked.")
            }
        }

        runOnMainThread {
            ad.show(activity) { rewardItem: RewardItem ->
                runOnMainThread {
                    callback.onUserEarnedReward(TAG, rewardItem)
                }
            }
        }
        return true
    }

    // =============================================================================================
    // Rewarded Interstitial Ad Preloader
    // =============================================================================================

    /**
     * Starts preloading Rewarded Interstitial ads in the background.
     */
    fun startRewardedInterstitialPreload(
        adUnitId: String,
        bufferSize: Int = 1,
        callback: PreloadCallback? = null
    ) {
        if (adUnitId.isBlank()) return
        runCatching {
            val adRequest = AdRequest.Builder(adUnitId).build()
            val preloadConfig = PreloadConfiguration(adRequest, bufferSize = bufferSize)
            if (callback != null) {
                RewardedInterstitialAdPreloader.start(adUnitId, preloadConfig, callback)
            } else {
                RewardedInterstitialAdPreloader.start(adUnitId, preloadConfig)
            }
        }.onFailure { Log.e(TAG, "Failed to start rewarded interstitial preload: ${it.message}") }
    }

    fun isRewardedInterstitialAvailable(adUnitId: String): Boolean {
        if (adUnitId.isBlank()) return false
        return runCatching { RewardedInterstitialAdPreloader.isAdAvailable(adUnitId) }.getOrDefault(false)
    }

    fun pollRewardedInterstitial(adUnitId: String): RewardedInterstitialAd? {
        if (adUnitId.isBlank()) return null
        return runCatching { RewardedInterstitialAdPreloader.pollAd(adUnitId) }.getOrNull()
    }

    fun peekRewardedInterstitialResponseInfo(adUnitId: String): ResponseInfo? {
        if (adUnitId.isBlank()) return null
        return runCatching { RewardedInterstitialAdPreloader.peekAdResponseInfo(adUnitId) }.getOrNull()
    }

    fun destroyRewardedInterstitial(adUnitId: String) {
        if (adUnitId.isBlank()) return
        runCatching { RewardedInterstitialAdPreloader.destroy(adUnitId) }
    }

    /**
     * Polls the next available preloaded Rewarded Interstitial ad and displays it immediately.
     */
    fun pollAndShowRewardedInterstitial(
        activity: Activity,
        adUnitId: String,
        callback: FrogoAdmobRewardedCallback
    ): Boolean {
        if (activity.isFinishing || activity.isDestroyed) {
            callback.onAdFailed(TAG, "Activity is finishing or destroyed")
            return false
        }
        val ad = pollRewardedInterstitial(adUnitId)
        if (ad == null) {
            callback.onAdFailed(TAG, "Rewarded Interstitial ad is not available in preloader buffer.")
            return false
        }

        ad.adEventCallback = object : RewardedInterstitialAdEventCallback {
            override fun onAdShowedFullScreenContent() {
                runOnMainThread {
                    callback.onAdShowed(TAG, "Preloaded Rewarded Interstitial ad showed")
                }
            }

            override fun onAdDismissedFullScreenContent() {
                runOnMainThread {
                    callback.onAdDismissed(TAG, "Preloaded Rewarded Interstitial ad dismissed")
                }
            }

            override fun onAdFailedToShowFullScreenContent(fullScreenContentError: FullScreenContentError) {
                runOnMainThread {
                    callback.onAdFailed(TAG, "Preloaded Rewarded Interstitial ad failed to show: ${fullScreenContentError.message}")
                }
            }

            override fun onAdImpression() {
                Log.d(TAG, "Preloaded Rewarded Interstitial ad recorded an impression.")
            }

            override fun onAdClicked() {
                Log.d(TAG, "Preloaded Rewarded Interstitial ad clicked.")
            }
        }

        runOnMainThread {
            ad.show(activity) { rewardItem: RewardItem ->
                runOnMainThread {
                    callback.onUserEarnedReward(TAG, rewardItem)
                }
            }
        }
        return true
    }

    // =============================================================================================
    // App Open Ad Preloader
    // =============================================================================================

    /**
     * Starts preloading App Open ads using official Next-Gen [AppOpenAdPreloader].
     */
    fun startAppOpenPreload(
        adUnitId: String,
        bufferSize: Int = 1,
        callback: PreloadCallback? = null
    ) {
        if (adUnitId.isBlank()) return
        runCatching {
            val adRequest = AdRequest.Builder(adUnitId).build()
            val preloadConfig = PreloadConfiguration(adRequest, bufferSize = bufferSize)
            if (callback != null) {
                AppOpenAdPreloader.start(adUnitId, preloadConfig, callback)
            } else {
                AppOpenAdPreloader.start(adUnitId, preloadConfig)
            }
        }.onFailure { Log.e(TAG, "Failed to start app open preload: ${it.message}") }
    }

    fun isAppOpenAvailable(adUnitId: String): Boolean {
        if (adUnitId.isBlank()) return false
        return runCatching { AppOpenAdPreloader.isAdAvailable(adUnitId) }.getOrDefault(false)
    }

    fun pollAppOpen(adUnitId: String): AppOpenAd? {
        if (adUnitId.isBlank()) return null
        return runCatching { AppOpenAdPreloader.pollAd(adUnitId) }.getOrNull()
    }

    fun peekAppOpenResponseInfo(adUnitId: String): ResponseInfo? {
        if (adUnitId.isBlank()) return null
        return runCatching { AppOpenAdPreloader.peekAdResponseInfo(adUnitId) }.getOrNull()
    }

    fun destroyAppOpen(adUnitId: String) {
        if (adUnitId.isBlank()) return
        runCatching { AppOpenAdPreloader.destroy(adUnitId) }
    }

    /**
     * Polls the next available preloaded App Open ad and displays it immediately.
     */
    fun pollAndShowAppOpen(
        activity: Activity,
        adUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback? = null
    ): Boolean {
        if (activity.isFinishing || activity.isDestroyed) {
            callback?.onAdFailed(TAG, "Activity is finishing or destroyed")
            return false
        }
        val ad = pollAppOpen(adUnitId)
        if (ad == null) {
            callback?.onAdFailed(TAG, "App Open ad is not available in preloader buffer.")
            return false
        }

        ad.adEventCallback = object : AppOpenAdEventCallback {
            override fun onAdShowedFullScreenContent() {
                runOnMainThread {
                    callback?.onAdShowed(TAG, "Preloaded App Open ad showed")
                }
            }

            override fun onAdDismissedFullScreenContent() {
                runOnMainThread {
                    callback?.onAdDismissed(TAG, "Preloaded App Open ad dismissed")
                }
            }

            override fun onAdFailedToShowFullScreenContent(fullScreenContentError: FullScreenContentError) {
                runOnMainThread {
                    callback?.onAdFailed(TAG, "Preloaded App Open ad failed to show: ${fullScreenContentError.message}")
                }
            }

            override fun onAdImpression() {
                Log.d(TAG, "Preloaded App Open ad recorded an impression.")
            }

            override fun onAdClicked() {
                Log.d(TAG, "Preloaded App Open ad clicked.")
            }
        }

        runOnMainThread {
            ad.show(activity)
        }
        return true
    }
}
