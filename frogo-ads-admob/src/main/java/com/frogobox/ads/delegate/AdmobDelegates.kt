package com.frogobox.ads.delegate

import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdmobAppOpenAdCallback
import com.frogobox.ads.callback.FrogoAdmobBannerCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.frogobox.ads.core.IFrogoAdConsent
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize
import com.google.android.libraries.ads.mobile.sdk.banner.AdView

/**
 * Created by faisalamir on 03/07/22
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

interface AdmobDelegates {

    fun setupAdmobDelegates(activity: AppCompatActivity)

    // Show Ad Consent (Legacy)
    fun showAdConsent(callback: IFrogoAdConsent)

    // Modern UMP Consent
    fun gatherConsent(
        testDeviceHashedId: String? = null,
        tagForUnderAgeOfConsent: Boolean = false,
        onComplete: (com.google.android.ump.FormError?) -> Unit
    )

    fun showPrivacyOptionsForm(
        onDismiss: (com.google.android.ump.FormError?) -> Unit
    )

    // ---------------------------------------------------------------------------------------------

    fun setupAdmobApp(appUnitId: String? = null)

    // Show Banner Ads
    fun showAdBanner(
        mAdView: AdView,
        bannerAdUnitId: String,
        mAdsSize: AdSize = AdSize.BANNER,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobBannerCallback? = null
    ) {
        showAdBanner(mAdView, bannerAdUnitId, mAdsSize, timeoutMilliSecond, keyword, callback, null)
    }

    fun showAdBanner(
        mAdView: AdView,
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?,
        collapsible: String?
    )

    fun showAdBanner(
        mAdView: AdView,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobBannerCallback? = null
    )

    // ---------------------------------------------------------------------------------------------

    // Show Banner Ads with container
    fun showAdBannerContainer(
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        container: RelativeLayout,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobBannerCallback? = null
    ) {
        showAdBannerContainer(bannerAdUnitId, mAdsSize, container, timeoutMilliSecond, keyword, callback, null)
    }

    fun showAdBannerContainer(
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        container: RelativeLayout,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?,
        collapsible: String?
    )

    // ---------------------------------------------------------------------------------------------

    // Show Interstitial Ads with timeout millisecond, keyword, callback
    fun showAdInterstitial(
        interstitialAdUnitId: String,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobInterstitialCallback? = null
    )

    // ---------------------------------------------------------------------------------------------

    // Show Rewarded Ads
    fun showAdRewarded(
        mAdUnitIdRewarded: String,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobRewardedCallback
    )

    // ---------------------------------------------------------------------------------------------

    // Show Rewarded Interstitial Ads
    fun showAdRewardedInterstitial(
        mAdUnitIdRewardedInterstitial: String,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobRewardedCallback
    )

    fun loadAdInterstitial(
        interstitialAdUnitId: String,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobInterstitialCallback? = null
    )

    fun loadAdRewarded(
        mAdUnitIdRewarded: String,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobRewardedCallback
    )

    fun loadAdRewardedInterstitial(
        mAdUnitIdRewardedInterstitial: String,
        timeoutMilliSecond: Int? = null,
        keyword: List<String>? = null,
        callback: FrogoAdmobRewardedCallback
    )

    // ---------------------------------------------------------------------------------------------
    // Next-Gen Preloaders
    // ---------------------------------------------------------------------------------------------

    fun startInterstitialPreload(
        adUnitId: String,
        bufferSize: Int = 1,
        callback: com.google.android.libraries.ads.mobile.sdk.common.PreloadCallback? = null
    )

    fun pollAndShowInterstitialPreload(
        adUnitId: String,
        callback: FrogoAdmobInterstitialCallback? = null
    ): Boolean

    fun startRewardedPreload(
        adUnitId: String,
        bufferSize: Int = 1,
        callback: com.google.android.libraries.ads.mobile.sdk.common.PreloadCallback? = null
    )

    fun pollAndShowRewardedPreload(
        adUnitId: String,
        callback: FrogoAdmobRewardedCallback
    ): Boolean

    fun startRewardedInterstitialPreload(
        adUnitId: String,
        bufferSize: Int = 1,
        callback: com.google.android.libraries.ads.mobile.sdk.common.PreloadCallback? = null
    )

    fun pollAndShowRewardedInterstitialPreload(
        adUnitId: String,
        callback: FrogoAdmobRewardedCallback
    ): Boolean

    fun startAppOpenPreload(
        adUnitId: String,
        bufferSize: Int = 1,
        callback: com.google.android.libraries.ads.mobile.sdk.common.PreloadCallback? = null
    )

    fun pollAndShowAppOpenPreload(
        adUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback? = null
    ): Boolean

    // ---------------------------------------------------------------------------------------------
    // App Open Ads
    // ---------------------------------------------------------------------------------------------

    fun isAppOpenAdAvailable(): Boolean

    fun loadAppOpenAd(
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback? = null
    )

    fun showAppOpenAd(
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback? = null
    )

    fun loadAndShowAppOpenAd(
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback? = null
    )

}
