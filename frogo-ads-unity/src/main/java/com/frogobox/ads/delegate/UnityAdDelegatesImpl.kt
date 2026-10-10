package com.frogobox.ads.delegate

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoUnityAdInitializationCallback
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback
import com.frogobox.ads.core.FrogoUnityAdManager
import com.unity3d.ads.UnityAds

/**
 * Lifecycle-scoped implementation of UnityAdDelegates.
 */
class UnityAdDelegatesImpl : UnityAdDelegates {

    companion object {
        val TAG: String = UnityAdDelegatesImpl::class.java.simpleName
    }

    private lateinit var unityAdDelegatesActivity: AppCompatActivity
    private lateinit var unityAdManager: FrogoUnityAdManager

    override fun setupUnityAdDelegates(activity: AppCompatActivity) {
        unityAdDelegatesActivity = activity
        unityAdManager = FrogoUnityAdManager(activity)
        setUnityAdConsent(userConsent = true, userOptOut = false)
    }

    override fun setUnityAdConsent(userConsent: Boolean, userOptOut: Boolean) {
        UnityAds.userConsent = userConsent
        UnityAds.userOptOut = userOptOut
    }

    override fun setupUnityAdApp(
        testMode: Boolean,
        unityGameId: String,
        callback: FrogoUnityAdInitializationCallback?
    ) {
        unityAdManager.setupUnityAdApp(unityAdDelegatesActivity, testMode, unityGameId, callback)
    }

    override fun showUnityAdInterstitial(
        adInterstitialUnitId: String,
        callback: FrogoUnityAdInterstitialCallback?
    ) {
        unityAdManager.showAdInterstitial(unityAdDelegatesActivity, adInterstitialUnitId, callback)
    }

}