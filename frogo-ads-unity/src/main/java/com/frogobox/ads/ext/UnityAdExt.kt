package com.frogobox.ads.ext

import android.app.Activity
import androidx.activity.ComponentActivity
import com.frogobox.ads.callback.FrogoUnityAdInitializationCallback
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback
import com.frogobox.ads.core.FrogoUnityAdManager

/**
 * Creates and attaches an instance-scoped [FrogoUnityAdManager] to this [ComponentActivity].
 * The manager will automatically detach and clean up references upon [ComponentActivity.onDestroy].
 */
fun ComponentActivity.createUnityAdManager(): FrogoUnityAdManager {
    return FrogoUnityAdManager(this)
}

/**
 * Initializes Unity Ads on any [Activity] without requiring activity inheritance.
 */
fun Activity.setupUnityAds(
    unityGameId: String,
    testMode: Boolean = true,
    callback: FrogoUnityAdInitializationCallback? = null
) {
    com.frogobox.ads.core.FrogoUnityAd.setupUnityAdApp(
        context = this,
        testMode = testMode,
        unityGameId = unityGameId,
        callback = callback
    )
}

/**
 * Shows a Unity Interstitial Ad on any [Activity] without requiring activity inheritance.
 */
fun Activity.showUnityInterstitial(
    adInterstitialUnitId: String,
    callback: FrogoUnityAdInterstitialCallback? = null
) {
    com.frogobox.ads.core.FrogoUnityAd.showAdInterstitial(
        activity = this,
        adInterstitialUnitId = adInterstitialUnitId,
        callback = callback
    )
}
