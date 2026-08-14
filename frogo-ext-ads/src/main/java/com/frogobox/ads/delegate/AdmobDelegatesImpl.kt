package com.frogobox.ads.delegate

import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdmobBannerCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.frogobox.ads.core.FrogoAdConsent
import com.frogobox.ads.core.FrogoAdmob
import com.frogobox.ads.core.IFrogoAdConsent
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize
import com.google.android.libraries.ads.mobile.sdk.banner.AdView


/**
 * Created by faisalamir on 03/07/22
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

class AdmobDelegatesImpl : AdmobDelegates {

    companion object {
        val TAG: String = AdmobDelegatesImpl::class.java.simpleName
    }

    private lateinit var admobDelegatesActivity: AppCompatActivity

    private val adConsent = FrogoAdConsent

    override fun setupAdmobDelegates(activity: AppCompatActivity) {
        admobDelegatesActivity = activity
    }

    override fun setupAdmobApp() {
        FrogoAdmob.setupAdmobApp(admobDelegatesActivity)
    }

    override fun showAdConsent(callback: IFrogoAdConsent) {
        adConsent.showConsent(callback)
    }

    // ---------------------------------------------------------------------------------------------

    override fun showAdBanner(
        mAdView: AdView,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?
    ) {
        FrogoAdmob.showAdBanner(mAdView, timeoutMilliSecond, keyword, callback)
    }

    override fun showAdBannerContainer(
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        container: RelativeLayout,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?
    ) {
        FrogoAdmob.showAdBannerContainer(
            context = admobDelegatesActivity,
            bannerAdUnitId = bannerAdUnitId,
            mAdsSize = mAdsSize,
            container = container,
            timeoutMilliSecond = timeoutMilliSecond,
            keyword = keyword,
            callback = callback
        )
    }

    // ---------------------------------------------------------------------------------------------

    override fun showAdInterstitial(
        interstitialAdUnitId: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobInterstitialCallback?
    ) {
        FrogoAdmob.showAdInterstitial(
            activity = admobDelegatesActivity,
            interstitialAdUnitId = interstitialAdUnitId,
            timeoutMilliSecond = timeoutMilliSecond,
            keyword = keyword,
            callback = callback
        )
    }

    // ---------------------------------------------------------------------------------------------

    override fun showAdRewarded(
        mAdUnitIdRewarded: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        FrogoAdmob.showAdRewarded(
            activity = admobDelegatesActivity,
            mAdUnitIdRewarded = mAdUnitIdRewarded,
            timeoutMilliSecond = timeoutMilliSecond,
            keyword = keyword,
            callback = callback
        )
    }

    // ---------------------------------------------------------------------------------------------

    override fun showAdRewardedInterstitial(
        mAdUnitIdRewardedInterstitial: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        FrogoAdmob.showAdRewardedInterstitial(
            activity = admobDelegatesActivity,
            mAdUnitIdRewardedInterstitial = mAdUnitIdRewardedInterstitial,
            timeoutMilliSecond = timeoutMilliSecond,
            keyword = keyword,
            callback = callback
        )
    }
}