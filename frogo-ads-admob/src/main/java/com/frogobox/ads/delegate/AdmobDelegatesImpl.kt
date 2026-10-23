package com.frogobox.ads.delegate

import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.FrogoAdmobApplication
import com.frogobox.ads.callback.FrogoAdmobAppOpenAdCallback
import com.frogobox.ads.callback.FrogoAdmobBannerCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.frogobox.ads.core.FrogoAdConsent
import com.frogobox.ads.core.FrogoAdmobManager
import com.frogobox.ads.core.FrogoAppOpenAdManager
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

    private val fallbackAppOpenAdManager: FrogoAppOpenAdManager by lazy {
        FrogoAppOpenAdManager()
    }

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

    override fun gatherConsent(
        testDeviceHashedId: String?,
        tagForUnderAgeOfConsent: Boolean,
        onComplete: (com.google.android.ump.FormError?) -> Unit
    ) {
        if (::admobDelegatesActivity.isInitialized) {
            adConsent.gatherConsent(
                activity = admobDelegatesActivity,
                testDeviceHashedId = testDeviceHashedId,
                tagForUnderAgeOfConsent = tagForUnderAgeOfConsent,
                onConsentGatheringComplete = onComplete
            )
        }
    }

    override fun showPrivacyOptionsForm(onDismiss: (com.google.android.ump.FormError?) -> Unit) {
        if (::admobDelegatesActivity.isInitialized) {
            adConsent.showPrivacyOptionsForm(admobDelegatesActivity, onDismiss)
        }
    }

    override fun showAdBanner(
        mAdView: AdView,
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?,
        collapsible: String?
    ) {
        admobManager.showAdBanner(mAdView, bannerAdUnitId, mAdsSize, timeoutMilliSecond, keyword, callback, collapsible)
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
        callback: FrogoAdmobBannerCallback?,
        collapsible: String?
    ) {
        admobManager.showAdBannerContainer(
            context = admobDelegatesActivity,
            bannerAdUnitId = bannerAdUnitId,
            mAdsSize = mAdsSize,
            container = container,
            timeoutMilliSecond = timeoutMilliSecond,
            keyword = keyword,
            callback = callback,
            collapsible = collapsible
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

    // ---------------------------------------------------------------------------------------------
    // Next-Gen Preloaders
    // ---------------------------------------------------------------------------------------------

    override fun startInterstitialPreload(
        adUnitId: String,
        bufferSize: Int,
        callback: com.google.android.libraries.ads.mobile.sdk.common.PreloadCallback?
    ) {
        com.frogobox.ads.core.FrogoAdmobPreloader.startInterstitialPreload(adUnitId, bufferSize, callback)
    }

    override fun pollAndShowInterstitialPreload(
        adUnitId: String,
        callback: FrogoAdmobInterstitialCallback?
    ): Boolean {
        return if (::admobDelegatesActivity.isInitialized) {
            com.frogobox.ads.core.FrogoAdmobPreloader.pollAndShowInterstitial(admobDelegatesActivity, adUnitId, callback)
        } else {
            false
        }
    }

    override fun startRewardedPreload(
        adUnitId: String,
        bufferSize: Int,
        callback: com.google.android.libraries.ads.mobile.sdk.common.PreloadCallback?
    ) {
        com.frogobox.ads.core.FrogoAdmobPreloader.startRewardedPreload(adUnitId, bufferSize, callback)
    }

    override fun pollAndShowRewardedPreload(
        adUnitId: String,
        callback: FrogoAdmobRewardedCallback
    ): Boolean {
        return if (::admobDelegatesActivity.isInitialized) {
            com.frogobox.ads.core.FrogoAdmobPreloader.pollAndShowRewarded(admobDelegatesActivity, adUnitId, callback)
        } else {
            false
        }
    }

    override fun startRewardedInterstitialPreload(
        adUnitId: String,
        bufferSize: Int,
        callback: com.google.android.libraries.ads.mobile.sdk.common.PreloadCallback?
    ) {
        com.frogobox.ads.core.FrogoAdmobPreloader.startRewardedInterstitialPreload(adUnitId, bufferSize, callback)
    }

    override fun pollAndShowRewardedInterstitialPreload(
        adUnitId: String,
        callback: FrogoAdmobRewardedCallback
    ): Boolean {
        return if (::admobDelegatesActivity.isInitialized) {
            com.frogobox.ads.core.FrogoAdmobPreloader.pollAndShowRewardedInterstitial(admobDelegatesActivity, adUnitId, callback)
        } else {
            false
        }
    }

    override fun startAppOpenPreload(
        adUnitId: String,
        bufferSize: Int,
        callback: com.google.android.libraries.ads.mobile.sdk.common.PreloadCallback?
    ) {
        com.frogobox.ads.core.FrogoAdmobPreloader.startAppOpenPreload(adUnitId, bufferSize, callback)
    }

    override fun pollAndShowAppOpenPreload(
        adUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback?
    ): Boolean {
        return if (::admobDelegatesActivity.isInitialized) {
            com.frogobox.ads.core.FrogoAdmobPreloader.pollAndShowAppOpen(admobDelegatesActivity, adUnitId, callback)
        } else {
            false
        }
    }

    // ---------------------------------------------------------------------------------------------
    // App Open Ads
    // ---------------------------------------------------------------------------------------------

    override fun isAppOpenAdAvailable(): Boolean {
        val app = if (::admobDelegatesActivity.isInitialized) {
            admobDelegatesActivity.application as? FrogoAdmobApplication
        } else {
            null
        }
        return app?.isAppOpenAdAvailable() ?: fallbackAppOpenAdManager.isAdAvailable()
    }

    override fun loadAppOpenAd(
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback?
    ) {
        val app = if (::admobDelegatesActivity.isInitialized) {
            admobDelegatesActivity.application as? FrogoAdmobApplication
        } else {
            null
        }
        if (app != null) {
            app.loadAppOpenAd(admobDelegatesActivity, appOpenAdUnitId, callback)
        } else if (::admobDelegatesActivity.isInitialized) {
            fallbackAppOpenAdManager.loadAd(admobDelegatesActivity, appOpenAdUnitId, callback)
        }
    }

    override fun showAppOpenAd(
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback?
    ) {
        val app = if (::admobDelegatesActivity.isInitialized) {
            admobDelegatesActivity.application as? FrogoAdmobApplication
        } else {
            null
        }
        if (app != null) {
            app.showAppOpenAdIfAvailable(admobDelegatesActivity, appOpenAdUnitId, callback)
        } else if (::admobDelegatesActivity.isInitialized) {
            fallbackAppOpenAdManager.showAdIfAvailable(admobDelegatesActivity, appOpenAdUnitId, callback)
        }
    }

    override fun loadAndShowAppOpenAd(
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback?
    ) {
        val app = if (::admobDelegatesActivity.isInitialized) {
            admobDelegatesActivity.application as? FrogoAdmobApplication
        } else {
            null
        }
        if (app != null) {
            app.loadAndShowAppOpenAd(admobDelegatesActivity, appOpenAdUnitId, callback)
        } else if (::admobDelegatesActivity.isInitialized) {
            fallbackAppOpenAdManager.loadAndShowAd(admobDelegatesActivity, appOpenAdUnitId, callback)
        }
    }
}