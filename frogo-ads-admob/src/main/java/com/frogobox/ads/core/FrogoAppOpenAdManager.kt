package com.frogobox.ads.core

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.frogobox.ads.callback.FrogoAdmobAppOpenAdCallback
import com.google.android.libraries.ads.mobile.sdk.appopen.AppOpenAd
import com.google.android.libraries.ads.mobile.sdk.appopen.AppOpenAdEventCallback
import com.google.android.libraries.ads.mobile.sdk.common.AdLoadCallback
import com.google.android.libraries.ads.mobile.sdk.common.AdRequest
import com.google.android.libraries.ads.mobile.sdk.common.FullScreenContentError
import com.google.android.libraries.ads.mobile.sdk.common.LoadAdError

/**
 * Created by Faisal Amir on 24/10/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */

/** Production-ready manager that loads and shows App Open Ads with lifecycle guards. */
class FrogoAppOpenAdManager {

    companion object {
        const val LOG_TAG = "FrogoAppOpenAdManager"
        const val AD_EXPIRATION_HOURS = 4L
        private const val MILLIS_PER_HOUR = 3600000L
    }

    private val mainHandler = Handler(Looper.getMainLooper())

    private fun runOnMainThread(action: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            action()
        } else {
            mainHandler.post(action)
        }
    }

    private var appOpenAd: AppOpenAd? = null

    // Visible for testing / package-level mocking
    internal var appOpenAdInternal: AppOpenAd?
        get() = appOpenAd
        set(value) {
            appOpenAd = value
        }

    var isLoadingAd: Boolean = false
        private set

    var isShowingAd: Boolean = false

    /** Timestamp (millis) when ad was loaded. */
    internal var loadTime: Long = 0L

    val loadTimeMillis: Long
        get() = loadTime

    /** Check if ad was loaded less than n hours ago. */
    fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        if (loadTime == 0L) return false
        val dateDifference: Long = System.currentTimeMillis() - loadTime
        return dateDifference < MILLIS_PER_HOUR * numHours
    }

    /** Check if ad exists and can be shown (not expired). */
    fun isAdAvailable(): Boolean {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(AD_EXPIRATION_HOURS)
    }

    /**
     * Load an ad without callback.
     *
     * @param context the context of the activity/application that loads the ad
     * @param appOpenAdUnitId Google AdMob Ad Unit ID
     */
    fun loadAd(context: Context, appOpenAdUnitId: String) {
        loadAd(context, appOpenAdUnitId, null)
    }

    /**
     * Load an ad with complete callback lifecycle.
     *
     * @param context the context of the activity/application that loads the ad
     * @param appOpenAdUnitId Google AdMob Ad Unit ID
     * @param callback the listener to be notified on ad load events
     */
    fun loadAd(
        context: Context,
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback?
    ) {
        if (appOpenAdUnitId.isBlank()) {
            runOnMainThread {
                callback?.onShowAdRequestProgress(LOG_TAG, "Validating ad unit...")
                callback?.onHideAdRequestProgress(LOG_TAG, "App Open Ad Unit ID is blank.")
                callback?.onAdFailed(LOG_TAG, "App Open Ad Unit ID is blank.")
            }
            return
        }

        if (isLoadingAd) {
            runOnMainThread {
                callback?.onShowAdRequestProgress(LOG_TAG, "Ad request is already in progress.")
            }
            return
        }

        if (isAdAvailable()) {
            runOnMainThread {
                callback?.onShowAdRequestProgress(LOG_TAG, "App Open Ad is already available.")
                callback?.onHideAdRequestProgress(LOG_TAG, "App Open Ad is already available.")
                callback?.onAdLoaded(LOG_TAG, "App Open Ad is already available.")
            }
            return
        }

        // Clean up expired ad if present
        if (appOpenAd != null && !wasLoadTimeLessThanNHoursAgo(AD_EXPIRATION_HOURS)) {
            appOpenAd = null
        }

        isLoadingAd = true
        runOnMainThread {
            callback?.onShowAdRequestProgress(LOG_TAG, "Loading App Open Ad...")
        }

        try {
            val request = AdRequest.Builder(appOpenAdUnitId).build()
            AppOpenAd.load(
                request,
                object : AdLoadCallback<AppOpenAd> {
                    override fun onAdLoaded(ad: AppOpenAd) {
                        runOnMainThread {
                            appOpenAd = ad
                            isLoadingAd = false
                            loadTime = System.currentTimeMillis()
                            callback?.onHideAdRequestProgress(LOG_TAG, "App Open Ad loaded.")
                            callback?.onAdLoaded(LOG_TAG, "App Open Ad loaded.")
                        }
                    }

                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        runOnMainThread {
                            isLoadingAd = false
                            callback?.onHideAdRequestProgress(LOG_TAG, "Failed to load: ${adError.message}")
                            callback?.onAdFailed(LOG_TAG, "Failed to load: ${adError.message}")
                        }
                    }
                }
            )
        } catch (e: Throwable) {
            runOnMainThread {
                isLoadingAd = false
                callback?.onHideAdRequestProgress(LOG_TAG, "Exception during load: ${e.message}")
                callback?.onAdFailed(LOG_TAG, "Exception during load: ${e.message}")
            }
        }
    }

    /**
     * Show the ad if one isn't already showing.
     *
     * @param activity the activity that shows the app open ad
     * @param appOpenAdUnitId Google AdMob Ad Unit ID
     */
    fun showAdIfAvailable(activity: Activity, appOpenAdUnitId: String) {
        showAdIfAvailable(activity, appOpenAdUnitId, null)
    }

    /**
     * Show the ad if available with defensive activity checks and lifecycle callback.
     *
     * @param activity the activity that shows the app open ad
     * @param appOpenAdUnitId Google AdMob Ad Unit ID
     * @param callback the listener to be notified when an app open ad lifecycle changes
     */
    fun showAdIfAvailable(
        activity: Activity,
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback?
    ) {
        runOnMainThread {
            callback?.onShowAdRequestProgress(LOG_TAG, "Will show ad.")
        }

        if (activity.isFinishing || activity.isDestroyed) {
            runOnMainThread {
                callback?.onHideAdRequestProgress(LOG_TAG, "Activity is finishing or destroyed.")
                callback?.onAdFailed(LOG_TAG, "Activity is finishing or destroyed.")
            }
            return
        }

        if (appOpenAdUnitId.isBlank()) {
            runOnMainThread {
                callback?.onHideAdRequestProgress(LOG_TAG, "App Open Ad Unit ID is blank.")
                callback?.onAdFailed(LOG_TAG, "App Open Ad Unit ID is blank.")
            }
            return
        }

        if (isShowingAd) {
            runOnMainThread {
                callback?.onHideAdRequestProgress(LOG_TAG, "The app open ad is already showing.")
                callback?.onAdFailed(LOG_TAG, "The app open ad is already showing.")
            }
            return
        }

        if (!isAdAvailable()) {
            if (appOpenAd != null && !wasLoadTimeLessThanNHoursAgo(AD_EXPIRATION_HOURS)) {
                appOpenAd = null
            }
            runOnMainThread {
                callback?.onHideAdRequestProgress(LOG_TAG, "The app open ad is not ready yet.")
                callback?.onAdDismissed(LOG_TAG, "The app open ad is not ready yet.")
            }
            loadAd(activity, appOpenAdUnitId)
            return
        }

        val ad = appOpenAd
        if (ad == null) {
            runOnMainThread {
                callback?.onHideAdRequestProgress(LOG_TAG, "The app open ad is null.")
                callback?.onAdFailed(LOG_TAG, "The app open ad is null.")
            }
            loadAd(activity, appOpenAdUnitId)
            return
        }

        ad.adEventCallback = object : AppOpenAdEventCallback {
            override fun onAdDismissedFullScreenContent() {
                appOpenAd = null
                isShowingAd = false
                runOnMainThread {
                    callback?.onHideAdRequestProgress(LOG_TAG, "onAdDismissedFullScreenContent")
                    callback?.onAdDismissed(LOG_TAG, "onAdDismissedFullScreenContent")
                }
                loadAd(activity, appOpenAdUnitId)
            }

            override fun onAdFailedToShowFullScreenContent(fullScreenContentError: FullScreenContentError) {
                appOpenAd = null
                isShowingAd = false
                runOnMainThread {
                    callback?.onHideAdRequestProgress(LOG_TAG, "onAdFailedToShowFullScreenContent")
                    callback?.onAdFailed(LOG_TAG, "onAdFailedToShowFullScreenContent: ${fullScreenContentError.message}")
                }
                loadAd(activity, appOpenAdUnitId)
            }

            override fun onAdShowedFullScreenContent() {
                runOnMainThread {
                    callback?.onHideAdRequestProgress(LOG_TAG, "onAdShowedFullScreenContent")
                    callback?.onAdShowed(LOG_TAG, "onAdShowedFullScreenContent")
                }
            }
        }
        isShowingAd = true
        runOnMainThread {
            try {
                ad.show(activity)
            } catch (e: Throwable) {
                appOpenAd = null
                isShowingAd = false
                callback?.onHideAdRequestProgress(LOG_TAG, "Exception during show: ${e.message}")
                callback?.onAdFailed(LOG_TAG, "Exception during show: ${e.message}")
            }
        }
    }

    /**
     * Load an App Open Ad and show it immediately upon successful load.
     * If an ad is already cached and valid, shows it immediately without reloading.
     */
    fun loadAndShowAd(
        activity: Activity,
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback? = null
    ) {
        if (activity.isFinishing || activity.isDestroyed) {
            runOnMainThread {
                callback?.onShowAdRequestProgress(LOG_TAG, "Validating activity...")
                callback?.onHideAdRequestProgress(LOG_TAG, "Activity is finishing or destroyed.")
                callback?.onAdFailed(LOG_TAG, "Activity is finishing or destroyed.")
            }
            return
        }

        if (isAdAvailable()) {
            showAdIfAvailable(activity, appOpenAdUnitId, callback)
            return
        }

        loadAd(
            activity,
            appOpenAdUnitId,
            object : FrogoAdmobAppOpenAdCallback {
                override fun onShowAdRequestProgress(tag: String, message: String) {
                    callback?.onShowAdRequestProgress(tag, message)
                }

                override fun onHideAdRequestProgress(tag: String, message: String) {
                    callback?.onHideAdRequestProgress(tag, message)
                }

                override fun onAdLoaded(tag: String, message: String) {
                    callback?.onAdLoaded(tag, message)
                    if (!activity.isFinishing && !activity.isDestroyed) {
                        showAdIfAvailable(activity, appOpenAdUnitId, callback)
                    } else {
                        callback?.onAdFailed(LOG_TAG, "Activity finished before ad could be shown.")
                    }
                }

                override fun onAdFailed(tag: String, errorMessage: String) {
                    callback?.onAdFailed(tag, errorMessage)
                }

                override fun onAdDismissed(tag: String, message: String) {
                    callback?.onAdDismissed(tag, message)
                }

                override fun onAdShowed(tag: String, message: String) {
                    callback?.onAdShowed(tag, message)
                }
            }
        )
    }

    /** Clears cached ad reference and resets loading / showing states. */
    fun clearAd() {
        appOpenAd = null
        loadTime = 0L
        isLoadingAd = false
        isShowingAd = false
    }
}