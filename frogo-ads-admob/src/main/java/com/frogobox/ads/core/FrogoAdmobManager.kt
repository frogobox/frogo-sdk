package com.frogobox.ads.core

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.frogobox.ads.callback.FrogoAdmobBannerCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.frogobox.ads.model.FrogoAdmobId
import com.google.android.libraries.ads.mobile.sdk.MobileAds
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize
import com.google.android.libraries.ads.mobile.sdk.banner.AdView
import com.google.android.libraries.ads.mobile.sdk.banner.BannerAd
import com.google.android.libraries.ads.mobile.sdk.banner.BannerAdEventCallback
import com.google.android.libraries.ads.mobile.sdk.banner.BannerAdRequest
import com.google.android.libraries.ads.mobile.sdk.common.AdLoadCallback
import com.google.android.libraries.ads.mobile.sdk.common.AdRequest
import com.google.android.libraries.ads.mobile.sdk.common.FullScreenContentError
import com.google.android.libraries.ads.mobile.sdk.common.LoadAdError
import com.google.android.libraries.ads.mobile.sdk.initialization.InitializationConfig
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAd
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAdEventCallback
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardItem
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardedAd
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardedAdEventCallback
import com.google.android.libraries.ads.mobile.sdk.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.libraries.ads.mobile.sdk.rewardedinterstitial.RewardedInterstitialAdEventCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Instance-scoped and lifecycle-aware AdMob manager.
 * Prevents global singleton state collisions when multiple screens load or show ads concurrently.
 */
class FrogoAdmobManager(
    lifecycleOwner: LifecycleOwner? = null
) : IFrogoAdmob,
    IFrogoAdmobBanner,
    IFrogoAdmobInterstitial,
    IFrogoAdmobRewarded,
    DefaultLifecycleObserver {

    companion object {
        const val TAG: String = "FrogoAdmob"
    }

    private var mInterstitialAd: InterstitialAd? = null
    private var mRewardedAd: RewardedAd? = null
    private var mRewardedInterstitialAd: RewardedInterstitialAd? = null

    private val mainHandler = Handler(Looper.getMainLooper())
    private var boundLifecycleOwner: LifecycleOwner? = lifecycleOwner

    init {
        lifecycleOwner?.lifecycle?.addObserver(this)
    }

    fun bindLifecycle(owner: LifecycleOwner) {
        boundLifecycleOwner?.lifecycle?.removeObserver(this)
        boundLifecycleOwner = owner
        owner.lifecycle.addObserver(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        clear()
        owner.lifecycle.removeObserver(this)
        boundLifecycleOwner = null
    }

    fun clear() {
        mInterstitialAd = null
        mRewardedAd = null
        mRewardedInterstitialAd = null
    }

    fun isInterstitialLoaded(): Boolean = mInterstitialAd != null
    fun isRewardedLoaded(): Boolean = mRewardedAd != null
    fun isRewardedInterstitialLoaded(): Boolean = mRewardedInterstitialAd != null

    private fun runOnMainThread(action: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            action()
        } else {
            mainHandler.post(action)
        }
    }

    override fun setupAdmobApp(context: Context, appUnitId: String?) {
        val backgroundScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        backgroundScope.launch {
            val appId = appUnitId ?: try {
                val appInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    context.packageManager.getApplicationInfo(
                        context.packageName,
                        android.content.pm.PackageManager.ApplicationInfoFlags.of(
                            android.content.pm.PackageManager.GET_META_DATA.toLong()
                        )
                    )
                } else {
                    context.packageManager.getApplicationInfo(
                        context.packageName,
                        android.content.pm.PackageManager.GET_META_DATA
                    )
                }
                appInfo.metaData?.getString("com.google.android.gms.ads.APPLICATION_ID").orEmpty()
            } catch (_: Exception) {
                ""
            }
            val config = InitializationConfig.Builder(appId).build()
            MobileAds.initialize(context, config) {}
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Banner Ads
    // ---------------------------------------------------------------------------------------------

    @SuppressLint("MissingPermission")
    override fun showAdBanner(
        mAdView: AdView,
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?
    ) {
        val actualAdUnitId = bannerAdUnitId.ifBlank { FrogoAdmobId().testAdmobBanner }
        val bannerAdRequestBuilder = BannerAdRequest.Builder(actualAdUnitId, mAdsSize)
        keyword?.forEach { bannerAdRequestBuilder.putCustomTargeting("keyword", it) }

        mAdView.loadAd(
            bannerAdRequestBuilder.build(),
            object : AdLoadCallback<BannerAd> {
                override fun onAdLoaded(ad: BannerAd) {
                    ad.adEventCallback = object : BannerAdEventCallback {
                        override fun onAdShowedFullScreenContent() {
                            runOnMainThread {
                                callback?.onAdOpened(TAG, "Ad Banner onAdOpened")
                            }
                        }

                        override fun onAdDismissedFullScreenContent() {
                            runOnMainThread {
                                callback?.onAdClosed(TAG, "Ad Banner onAdClosed")
                            }
                        }

                        override fun onAdClicked() {
                            runOnMainThread {
                                callback?.onAdClicked(TAG, "Ad Banner onAdClicked")
                            }
                        }
                    }
                    runOnMainThread {
                        callback?.onAdLoaded(TAG, "Ad Banner onAdLoaded")
                    }
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    runOnMainThread {
                        callback?.onAdFailedToLoad(
                            TAG,
                            adError.code.toString(),
                            adError.message
                        )
                    }
                }
            }
        )
    }

    @SuppressLint("MissingPermission")
    override fun showAdBanner(
        mAdView: AdView,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?
    ) {
        showAdBanner(
            mAdView = mAdView,
            bannerAdUnitId = FrogoAdmobId().testAdmobBanner,
            mAdsSize = AdSize.BANNER,
            timeoutMilliSecond = timeoutMilliSecond,
            keyword = keyword,
            callback = callback
        )
    }

    @SuppressLint("MissingPermission")
    override fun showAdBannerContainer(
        context: Context,
        bannerAdUnitId: String,
        mAdsSize: AdSize,
        container: RelativeLayout,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobBannerCallback?
    ) {
        if (bannerAdUnitId.isNotBlank()) {
            val mAdView = AdView(context)
            if (context is LifecycleOwner) {
                context.lifecycle.addObserver(object : DefaultLifecycleObserver {
                    override fun onDestroy(owner: LifecycleOwner) {
                        mAdView.destroy()
                    }
                })
            }
            container.removeAllViews()
            container.addView(mAdView)

            showAdBanner(
                mAdView = mAdView,
                bannerAdUnitId = bannerAdUnitId,
                mAdsSize = mAdsSize,
                timeoutMilliSecond = timeoutMilliSecond,
                keyword = keyword,
                callback = callback
            )
        } else {
            runOnMainThread {
                callback?.onAdFailedToLoad(TAG, "000", ": Banner Unit Id is Empty")
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Interstitial Ads
    // ---------------------------------------------------------------------------------------------

    override fun loadAdInterstitial(
        activity: AppCompatActivity,
        interstitialAdUnitId: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobInterstitialCallback?
    ) {
        if (interstitialAdUnitId.isNotBlank()) {
            callback?.onShowAdRequestProgress(TAG, "[Interstitial] >> Run - loadAdInterstitial")
            val adRequestBuilder = AdRequest.Builder(interstitialAdUnitId)
            keyword?.forEach { adRequestBuilder.putCustomTargeting("keyword", it) }

            InterstitialAd.load(
                adRequestBuilder.build(),
                object : AdLoadCallback<InterstitialAd> {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mInterstitialAd = null
                        runOnMainThread {
                            callback?.onHideAdRequestProgress(TAG, "[Interstitial] >> Error - onAdFailedToLoad : ${adError.message}")
                            callback?.onAdFailed(TAG, "Interstitial ${adError.message}")
                        }
                    }

                    override fun onAdLoaded(ad: InterstitialAd) {
                        mInterstitialAd = ad
                        runOnMainThread {
                            callback?.onAdLoaded(TAG, "Interstitial Ad was loaded")
                        }

                        mInterstitialAd?.adEventCallback = object : InterstitialAdEventCallback {
                            override fun onAdDismissedFullScreenContent() {
                                mInterstitialAd = null
                                runOnMainThread {
                                    callback?.onAdDismissed(TAG, "Interstitial Ad was dismissed")
                                }
                            }

                            override fun onAdFailedToShowFullScreenContent(fullScreenContentError: FullScreenContentError) {
                                mInterstitialAd = null
                                runOnMainThread {
                                    callback?.onAdFailed(TAG, "Interstitial Ad failed to show: ${fullScreenContentError.message}")
                                }
                            }

                            override fun onAdShowedFullScreenContent() {
                                runOnMainThread {
                                    callback?.onAdShowed(TAG, "Interstitial Ad showed fullscreen content")
                                }
                            }
                        }
                    }
                }
            )
        } else {
            callback?.onAdFailed(TAG, "Interstitial ID is Empty")
        }
    }

    override fun showAdInterstitial(
        activity: AppCompatActivity,
        interstitialAdUnitId: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobInterstitialCallback?
    ) {
        if (mInterstitialAd != null) {
            runOnMainThread {
                mInterstitialAd?.show(activity)
            }
        } else {
            loadAdInterstitial(activity, interstitialAdUnitId, timeoutMilliSecond, keyword, object : FrogoAdmobInterstitialCallback {
                override fun onShowAdRequestProgress(tag: String, message: String) { callback?.onShowAdRequestProgress(tag, message) }
                override fun onHideAdRequestProgress(tag: String, message: String) { callback?.onHideAdRequestProgress(tag, message) }
                override fun onAdLoaded(tag: String, message: String) {
                    callback?.onAdLoaded(tag, message)
                    runOnMainThread { mInterstitialAd?.show(activity) }
                }
                override fun onAdFailed(tag: String, errorMessage: String) { callback?.onAdFailed(tag, errorMessage) }
                override fun onAdDismissed(tag: String, message: String) { callback?.onAdDismissed(tag, message) }
                override fun onAdShowed(tag: String, message: String) { callback?.onAdShowed(tag, message) }
            })
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Rewarded Ads
    // ---------------------------------------------------------------------------------------------

    override fun loadAdRewarded(
        activity: AppCompatActivity,
        mAdUnitIdRewarded: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        if (mAdUnitIdRewarded.isNotBlank()) {
            callback.onShowAdRequestProgress(TAG, "[RewardedAd] >> Run - loadAdRewarded")
            val adRequestBuilder = AdRequest.Builder(mAdUnitIdRewarded)
            keyword?.forEach { adRequestBuilder.putCustomTargeting("keyword", it) }

            RewardedAd.load(
                adRequestBuilder.build(),
                object : AdLoadCallback<RewardedAd> {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mRewardedAd = null
                        runOnMainThread {
                            callback.onHideAdRequestProgress(TAG, "[RewardedAd] >> Error - onAdFailedToLoad: ${adError.message}")
                            callback.onAdFailed(TAG, "RewardedAd ${adError.message}")
                        }
                    }

                    override fun onAdLoaded(ad: RewardedAd) {
                        mRewardedAd = ad
                        runOnMainThread {
                            callback.onAdLoaded(TAG, "RewardedAd was loaded")
                        }
                        mRewardedAd?.adEventCallback = object : RewardedAdEventCallback {
                            override fun onAdDismissedFullScreenContent() {
                                mRewardedAd = null
                                runOnMainThread {
                                    callback.onAdDismissed(TAG, "Rewarded Ad was dismissed")
                                }
                            }
                            override fun onAdFailedToShowFullScreenContent(fullScreenContentError: FullScreenContentError) {
                                mRewardedAd = null
                                runOnMainThread {
                                    callback.onAdFailed(TAG, "Rewarded Ad failed to show: ${fullScreenContentError.message}")
                                }
                            }
                            override fun onAdShowedFullScreenContent() {
                                runOnMainThread {
                                    callback.onAdShowed(TAG, "Rewarded Ad showed fullscreen content")
                                }
                            }
                        }
                    }
                }
            )
        } else {
            callback.onAdFailed(TAG, "Rewarded Unit Id is Empty")
        }
    }

    override fun showAdRewarded(
        activity: AppCompatActivity,
        mAdUnitIdRewarded: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        if (mRewardedAd != null) {
            runOnMainThread {
                mRewardedAd?.show(activity) { rewardItem ->
                    runOnMainThread { callback.onUserEarnedReward(TAG, rewardItem) }
                }
            }
        } else {
            loadAdRewarded(activity, mAdUnitIdRewarded, timeoutMilliSecond, keyword, object : FrogoAdmobRewardedCallback {
                override fun onUserEarnedReward(tag: String, rewardItem: RewardItem) { callback.onUserEarnedReward(tag, rewardItem) }
                override fun onShowAdRequestProgress(tag: String, message: String) { callback.onShowAdRequestProgress(tag, message) }
                override fun onHideAdRequestProgress(tag: String, message: String) { callback.onHideAdRequestProgress(tag, message) }
                override fun onAdLoaded(tag: String, message: String) {
                    callback.onAdLoaded(tag, message)
                    runOnMainThread {
                        mRewardedAd?.show(activity) { item -> runOnMainThread { callback.onUserEarnedReward(TAG, item) } }
                    }
                }
                override fun onAdFailed(tag: String, errorMessage: String) { callback.onAdFailed(tag, errorMessage) }
                override fun onAdDismissed(tag: String, message: String) { callback.onAdDismissed(tag, message) }
                override fun onAdShowed(tag: String, message: String) { callback.onAdShowed(tag, message) }
            })
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Rewarded Interstitial Ads
    // ---------------------------------------------------------------------------------------------

    override fun loadAdRewardedInterstitial(
        activity: AppCompatActivity,
        mAdUnitIdRewardedInterstitial: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        if (mAdUnitIdRewardedInterstitial.isNotBlank()) {
            callback.onShowAdRequestProgress(TAG, "[RewardedInterstitial] >> Run - loadAdRewardedInterstitial")
            val adRequestBuilder = AdRequest.Builder(mAdUnitIdRewardedInterstitial)
            keyword?.forEach { adRequestBuilder.putCustomTargeting("keyword", it) }

            RewardedInterstitialAd.load(
                adRequestBuilder.build(),
                object : AdLoadCallback<RewardedInterstitialAd> {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mRewardedInterstitialAd = null
                        runOnMainThread {
                            callback.onHideAdRequestProgress(TAG, "[RewardedInterstitial] >> Error - ${adError.message}")
                            callback.onAdFailed(TAG, "RewardedInterstitial ${adError.message}")
                        }
                    }

                    override fun onAdLoaded(ad: RewardedInterstitialAd) {
                        mRewardedInterstitialAd = ad
                        runOnMainThread {
                            callback.onAdLoaded(TAG, "RewardedInterstitial Ad was loaded")
                        }
                        mRewardedInterstitialAd?.adEventCallback = object : RewardedInterstitialAdEventCallback {
                            override fun onAdDismissedFullScreenContent() {
                                mRewardedInterstitialAd = null
                                runOnMainThread {
                                    callback.onAdDismissed(TAG, "RewardedInterstitial Ad was dismissed")
                                }
                            }
                            override fun onAdFailedToShowFullScreenContent(fullScreenContentError: FullScreenContentError) {
                                mRewardedInterstitialAd = null
                                runOnMainThread {
                                    callback.onAdFailed(TAG, "RewardedInterstitial Ad failed to show: ${fullScreenContentError.message}")
                                }
                            }
                            override fun onAdShowedFullScreenContent() {
                                runOnMainThread {
                                    callback.onAdShowed(TAG, "RewardedInterstitial Ad showed fullscreen content")
                                }
                            }
                        }
                    }
                }
            )
        } else {
            callback.onAdFailed(TAG, "Rewarded Interstitial Id Is Empty")
        }
    }

    override fun showAdRewardedInterstitial(
        activity: AppCompatActivity,
        mAdUnitIdRewardedInterstitial: String,
        timeoutMilliSecond: Int?,
        keyword: List<String>?,
        callback: FrogoAdmobRewardedCallback
    ) {
        if (mRewardedInterstitialAd != null) {
            runOnMainThread {
                mRewardedInterstitialAd?.show(activity) { rewardItem ->
                    runOnMainThread { callback.onUserEarnedReward(TAG, rewardItem) }
                }
            }
        } else {
            loadAdRewardedInterstitial(activity, mAdUnitIdRewardedInterstitial, timeoutMilliSecond, keyword, object : FrogoAdmobRewardedCallback {
                override fun onUserEarnedReward(tag: String, rewardItem: RewardItem) { callback.onUserEarnedReward(tag, rewardItem) }
                override fun onShowAdRequestProgress(tag: String, message: String) { callback.onShowAdRequestProgress(tag, message) }
                override fun onHideAdRequestProgress(tag: String, message: String) { callback.onHideAdRequestProgress(tag, message) }
                override fun onAdLoaded(tag: String, message: String) {
                    callback.onAdLoaded(tag, message)
                    runOnMainThread {
                        mRewardedInterstitialAd?.show(activity) { item -> runOnMainThread { callback.onUserEarnedReward(TAG, item) } }
                    }
                }
                override fun onAdFailed(tag: String, errorMessage: String) { callback.onAdFailed(tag, errorMessage) }
                override fun onAdDismissed(tag: String, message: String) { callback.onAdDismissed(tag, message) }
                override fun onAdShowed(tag: String, message: String) { callback.onAdShowed(tag, message) }
            })
        }
    }
}
