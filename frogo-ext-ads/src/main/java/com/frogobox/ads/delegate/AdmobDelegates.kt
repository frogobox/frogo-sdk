package com.frogobox.ads.delegate

import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
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

    // Show Ad Consent
    fun showAdConsent(callback: IFrogoAdConsent)

    // ---------------------------------------------------------------------------------------------

    fun setupAdmobApp(appUnitId: String? = null)

    // Show Banner Ads
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

}
