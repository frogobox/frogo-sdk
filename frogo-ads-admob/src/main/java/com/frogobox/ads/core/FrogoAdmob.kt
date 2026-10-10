package com.frogobox.ads.core

import android.content.Context
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.frogobox.ads.callback.FrogoAdmobBannerCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize
import com.google.android.libraries.ads.mobile.sdk.banner.AdView

/**
 * AdMob entry point.
 * Provides factory methods [create] for instance-scoped [FrogoAdmobManager] (recommended to prevent state collisions),
 * as well as singleton methods for backward compatibility.
 */
object FrogoAdmob : IFrogoAdmob,
    IFrogoAdmobBanner,
    IFrogoAdmobInterstitial,
    IFrogoAdmobRewarded {

    val TAG: String = FrogoAdmob::class.java.simpleName

    private val defaultManager = FrogoAdmobManager()

    /**
     * Creates an instance-scoped [FrogoAdmobManager] optionally tied to [lifecycleOwner].
     * Recommended for multi-screen apps to avoid ad state collisions.
     */
    fun create(lifecycleOwner: LifecycleOwner? = null): FrogoAdmobManager {
        return FrogoAdmobManager(lifecycleOwner)
    }

    override fun setupAdmobApp(context: Context, appUnitId: String?) {
        defaultManager.setupAdmobApp(context, appUnitId)
    }

    override fun showAdBanner(
        mAdView: AdView,
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?
    ) {
        defaultManager.showAdBanner(mAdView, bannerAdUnitId, mAdsSize, timeoutMilliSecond, keyword, callback)
    }

    override fun showAdBanner(
        mAdView: AdView,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?
    ) {
        defaultManager.showAdBanner(mAdView, timeoutMilliSecond, keyword, callback)
    }

    override fun showAdBannerContainer(
        context: Context,
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        container: RelativeLayout,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?
    ) {
        defaultManager.showAdBannerContainer(context, bannerAdUnitId, mAdsSize, container, timeoutMilliSecond, keyword, callback)
    }

    override fun loadAdInterstitial(
        activity: AppCompatActivity,
        interstitialAdUnitId: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobInterstitialCallback?
    ) {
        defaultManager.loadAdInterstitial(activity, interstitialAdUnitId, timeoutMilliSecond, keyword, callback)
    }

    override fun showAdInterstitial(
        activity: AppCompatActivity,
        interstitialAdUnitId: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobInterstitialCallback?
    ) {
        defaultManager.showAdInterstitial(activity, interstitialAdUnitId, timeoutMilliSecond, keyword, callback)
    }

    override fun loadAdRewarded(
        activity: AppCompatActivity,
        mAdUnitIdRewarded: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        defaultManager.loadAdRewarded(activity, mAdUnitIdRewarded, timeoutMilliSecond, keyword, callback)
    }

    override fun showAdRewarded(
        activity: AppCompatActivity,
        mAdUnitIdRewarded: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        defaultManager.showAdRewarded(activity, mAdUnitIdRewarded, timeoutMilliSecond, keyword, callback)
    }

    override fun loadAdRewardedInterstitial(
        activity: AppCompatActivity,
        mAdUnitIdRewardedInterstitial: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        defaultManager.loadAdRewardedInterstitial(activity, mAdUnitIdRewardedInterstitial, timeoutMilliSecond, keyword, callback)
    }

    override fun showAdRewardedInterstitial(
        activity: AppCompatActivity,
        mAdUnitIdRewardedInterstitial: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        defaultManager.showAdRewardedInterstitial(activity, mAdUnitIdRewardedInterstitial, timeoutMilliSecond, keyword, callback)
    }
}