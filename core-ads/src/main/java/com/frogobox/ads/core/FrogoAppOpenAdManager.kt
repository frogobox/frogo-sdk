package com.frogobox.ads.core

import android.app.Activity
import android.content.Context
import com.frogobox.ads.callback.FrogoAdmobAppOpenAdCallback
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import java.util.Date

/**
 * Created by Faisal Amir on 24/10/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */


/** Inner class that loads and shows app open ads. */
class FrogoAppOpenAdManager {

    companion object {
        const val LOG_TAG = "FrogoAppOpenAdManager"
    }

    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd = false
    var isShowingAd = false

    /** Keep track of the time an app open ad is loaded to ensure you don't show an expired ad. */
    private var loadTime: Long = 0

    /**
     * Load an ad.
     *
     * @param context the context of the activity that loads the ad
     */
    fun loadAd(context: Context, appOpenAdUnitId: String) {
        // Do not load ad if there is an unused ad or one is already loading.
        if (isLoadingAd || isAdAvailable()) {
            return
        }

        isLoadingAd = true
        val request = AdRequest.Builder().build()
        AppOpenAd.load(
            context,
            appOpenAdUnitId,
            request,
            object : AppOpenAd.AppOpenAdLoadCallback() {
                /**
                 * Called when an app open ad has loaded.
                 *
                 * @param ad the loaded app open ad.
                 */
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                    isLoadingAd = false
                    loadTime = Date().time
                }

                /**
                 * Called when an app open ad has failed to load.
                 *
                 * @param loadAdError the error.
                 */
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    isLoadingAd = false
                }
            }
        )
    }

    /** Check if ad was loaded more than n hours ago. */
    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference: Long = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * numHours
    }

    /** Check if ad exists and can be shown. */
    private fun isAdAvailable(): Boolean {
        // Ad references in the app open beta will time out after four hours, but this time limit
        // may change in future beta versions. For details, see:
        // https://support.google.com/admob/answer/9341964?hl=en
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
    }

    /**
     * Show the ad if one isn't already showing.
     *
     * @param activity the activity that shows the app open ad
     */
    fun showAdIfAvailable(activity: Activity, appOpenAdUnitId: String) {
        showAdIfAvailable(
            activity,
            appOpenAdUnitId,
            object : FrogoAdmobAppOpenAdCallback {
                override fun onShowAdRequestProgress(tag: String, message: String) {
                    // Empty because the user will go back to the activity that shows the ad.
                }

                override fun onHideAdRequestProgress(tag: String, message: String) {
                    // Empty because the user will go back to the activity that shows the ad.
                }

                override fun onAdDismissed(tag: String, message: String) {
                    // Empty because the user will go back to the activity that shows the ad.
                }

                override fun onAdFailed(tag: String, errorMessage: String) {
                    // Empty because the user will go back to the activity that shows the ad.
                }

                override fun onAdLoaded(tag: String, message: String) {
                    // Empty because the user will go back to the activity that shows the ad.
                }

                override fun onAdShowed(tag: String, message: String) {
                    // Empty because the user will go back to the activity that shows the ad.
                }
            }
        )
    }

    /**
     * Show the ad if one isn't already showing.
     *
     * @param activity the activity that shows the app open ad
     * @param callback the listener to be notified when an app open ad is complete
     */
    fun showAdIfAvailable(
        activity: Activity,
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback
    ) {

        callback.onShowAdRequestProgress(LOG_TAG, "Will show ad.")

        // If the app open ad is already showing, do not show the ad again.
        if (isShowingAd) {
            callback.onHideAdRequestProgress(LOG_TAG, "The app open ad is already showing.")
            return
        }

        // If the app open ad is not available yet, invoke the callback then load the ad.
        if (!isAdAvailable()) {
            callback.onAdDismissed(LOG_TAG, "The app open ad is not ready yet.")
            callback.onHideAdRequestProgress(LOG_TAG, "The app open ad is not ready yet.")
            loadAd(activity, appOpenAdUnitId)
            return
        }


        appOpenAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
            /** Called when full screen content is dismissed. */
            override fun onAdDismissedFullScreenContent() {
                // Set the reference to null so isAdAvailable() returns false.
                appOpenAd = null
                isShowingAd = false
                callback.onHideAdRequestProgress(LOG_TAG, "onAdDismissedFullScreenContent")
                callback.onAdDismissed(LOG_TAG, "onAdDismissedFullScreenContent")
                loadAd(activity, appOpenAdUnitId)
            }

            /** Called when fullscreen content failed to show. */
            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                appOpenAd = null
                isShowingAd = false
                callback.onHideAdRequestProgress(LOG_TAG, "onAdFailedToShowFullScreenContent")
                callback.onAdFailed(LOG_TAG, "onAdFailedToShowFullScreenContent: ${adError.message}")
                loadAd(activity, appOpenAdUnitId)
            }

            /** Called when fullscreen content is shown. */
            override fun onAdShowedFullScreenContent() {
                callback.onHideAdRequestProgress(LOG_TAG, "onAdShowedFullScreenContent")
                callback.onAdShowed(LOG_TAG, "onAdShowedFullScreenContent")
            }
        }
        isShowingAd = true
        appOpenAd!!.show(activity)
    }
}