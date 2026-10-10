package com.frogobox.ads.core

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.frogobox.ads.callback.FrogoUnityAdInitializationCallback
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback

/**
 * Unity Ads entry point.
 * Provides factory methods [create] for instance-scoped [FrogoUnityAdManager] (recommended to prevent state collisions),
 * as well as singleton methods for backward compatibility.
 */
object FrogoUnityAd : IFrogoUnityAd {

    val TAG: String = FrogoUnityAd::class.java.simpleName

    private val defaultManager = FrogoUnityAdManager()

    /**
     * Creates an instance-scoped [FrogoUnityAdManager] tied to the given [lifecycleOwner].
     */
    fun create(lifecycleOwner: LifecycleOwner? = null): FrogoUnityAdManager {
        return FrogoUnityAdManager(lifecycleOwner)
    }

    override fun setupUnityAdApp(
        context: Context,
        testMode: Boolean,
        unityGameId: String,
        callback: FrogoUnityAdInitializationCallback?
    ) {
        defaultManager.setupUnityAdApp(context, testMode, unityGameId, callback)
    }

    override fun showAdInterstitial(
        activity: Activity,
        adInterstitialUnitId: String,
        callback: FrogoUnityAdInterstitialCallback?
    ) {
        defaultManager.showAdInterstitial(activity, adInterstitialUnitId, callback)
    }
}