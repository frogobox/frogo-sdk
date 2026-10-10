package com.frogobox.ads.delegate

import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdmobBannerCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.frogobox.ads.core.FrogoAdConsent
import com.frogobox.ads.core.FrogoAdmobManager
import com.frogobox.ads.core.IFrogoAdConsent
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize
import com.google.android.libraries.ads.mobile.sdk.banner.AdView

class AdmobDelegatesImpl : AdmobDelegates {

    companion object {
        val TAG: String = AdmobDelegatesImpl::class.java.simpleName
    }

    private lateinit var admobDelegatesActivity: AppCompatActivity
    private lateinit var admobManager: FrogoAdmobManager
    private val adConsent = FrogoAdConsent

    override fun setupAdmobDelegates(activity: AppCompatActivity) {
        admobDelegatesActivity = activity
        admobManager = FrogoAdmobManager(activity)
    }

    override fun setupAdmobApp(appUnitId: String?) {
        admobManager.setupAdmobApp(admobDelegatesActivity, appUnitId)
    }

    override fun showAdConsent(callback: IFrogoAdConsent) {
        adConsent.showConsent(callback)
    }

    override fun showAdBanner(
        mAdView: AdView,
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?
    ) {
        admobManager.showAdBanner(mAdView, bannerAdUnitId, mAdsSize, timeoutMilliSecond, keyword, callback)
    }

    override fun showAdBanner(
        mAdView: AdView,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?
    ) {
        admobManager.showAdBanner(mAdView, timeoutMilliSecond, keyword, callback)
    }

    override fun showAdBannerContainer(
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        container: RelativeLayout,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?
    ) {
        admobManager.showAdBannerContainer(
            context = admobDelegatesActivity,
            bannerAdUnitId = bannerAdUnitId,
            mAdsSize = mAdsSize,
            container = container,
            timeoutMilliSecond = timeoutMilliSecond,
            keyword = keyword,
            callback = callback
        )
    }

    override fun showAdInterstitial(
        interstitialAdUnitId: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobInterstitialCallback?
    ) {
        admobManager.showAdInterstitial(
            activity = admobDelegatesActivity,
            interstitialAdUnitId = interstitialAdUnitId,
            timeoutMilliSecond = timeoutMilliSecond,
            keyword = keyword,
            callback = callback
        )
    }

    override fun showAdRewarded(
        mAdUnitIdRewarded: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        admobManager.showAdRewarded(
            activity = admobDelegatesActivity,
            mAdUnitIdRewarded = mAdUnitIdRewarded,
            timeoutMilliSecond = timeoutMilliSecond,
            keyword = keyword,
            callback = callback
        )
    }

    override fun showAdRewardedInterstitial(
        mAdUnitIdRewardedInterstitial: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        admobManager.showAdRewardedInterstitial(
            activity = admobDelegatesActivity,
            mAdUnitIdRewardedInterstitial = mAdUnitIdRewardedInterstitial,
            timeoutMilliSecond = timeoutMilliSecond,
            keyword = keyword,
            callback = callback
        )
    }

    override fun loadAdInterstitial(
        interstitialAdUnitId: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobInterstitialCallback?
    ) {
        admobManager.loadAdInterstitial(admobDelegatesActivity, interstitialAdUnitId, timeoutMilliSecond, keyword, callback)
    }

    override fun loadAdRewarded(
        mAdUnitIdRewarded: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        admobManager.loadAdRewarded(admobDelegatesActivity, mAdUnitIdRewarded, timeoutMilliSecond, keyword, callback)
    }

    override fun loadAdRewardedInterstitial(
        mAdUnitIdRewardedInterstitial: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        admobManager.loadAdRewardedInterstitial(admobDelegatesActivity, mAdUnitIdRewardedInterstitial, timeoutMilliSecond, keyword, callback)
    }
}