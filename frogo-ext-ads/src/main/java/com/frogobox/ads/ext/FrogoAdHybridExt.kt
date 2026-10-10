package com.frogobox.ads.ext

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdInterstitialCallback
import com.frogobox.ads.delegate.FrogoAdDelegates
import com.frogobox.ads.delegate.FrogoAdDelegatesImpl

/**
 * Creates and attaches a [FrogoAdDelegates] instance to this [AppCompatActivity].
 * Enables full hybrid mediation (AdMob + Unity Ads) via composition rather than inheritance.
 */
fun AppCompatActivity.createFrogoAdDelegates(): FrogoAdDelegates {
    val delegates = FrogoAdDelegatesImpl()
    delegates.setupFrogoAdDelegates(this)
    return delegates
}

/**
 * Extension function to show a hybrid interstitial ad on any [AppCompatActivity]
 * without requiring the Activity to inherit [com.frogobox.ads.ui.FrogoAdActivity].
 *
 * @param admobId AdMob interstitial unit ID
 * @param unityId Unity Ads interstitial unit ID
 * @param prioritizeAdmob If true, attempts AdMob first and falls back to Unity. If false, attempts Unity first.
 * @param callback Unified ad callback
 */
fun AppCompatActivity.showHybridInterstitial(
    admobId: String,
    unityId: String,
    prioritizeAdmob: Boolean = true,
    callback: FrogoAdInterstitialCallback
) {
    val delegates = createFrogoAdDelegates()
    if (prioritizeAdmob) {
        delegates.showAdmobXUnityAdInterstitial(
            admobInterstitialId = admobId,
            unityInterstitialId = unityId,
            callback = callback
        )
    } else {
        delegates.showUnityXAdmobAdInterstitial(
            unityInterstitialId = unityId,
            admobInterstitialId = admobId,
            callback = callback
        )
    }
}
