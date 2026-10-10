package com.frogobox.ads.core

import android.app.Activity
import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.frogobox.ads.callback.FrogoUnityAdInitializationCallback
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback
import com.unity3d.ads.InitializationConfiguration
import com.unity3d.ads.InitializationListener
import com.unity3d.ads.InterstitialAd
import com.unity3d.ads.InterstitialShowListener
import com.unity3d.ads.LoadConfiguration
import com.unity3d.ads.LoadListener
import com.unity3d.ads.ShowConfiguration
import com.unity3d.ads.ShowFinishState
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAdsError

/**
 * Instance-scoped and lifecycle-aware Unity Ads manager.
 * Prevents state collisions and leaks by binding to [LifecycleOwner].
 */
class FrogoUnityAdManager(
    lifecycleOwner: LifecycleOwner? = null
) : IFrogoUnityAd, DefaultLifecycleObserver {

    companion object {
        val TAG: String = FrogoUnityAdManager::class.java.simpleName
    }

    private var activeInterstitialAd: InterstitialAd? = null
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
        activeInterstitialAd = null
        owner.lifecycle.removeObserver(this)
        boundLifecycleOwner = null
    }

    fun clear() {
        activeInterstitialAd = null
    }

    private fun runOnActivity(activity: Activity, action: () -> Unit) {
        if (!activity.isFinishing && !activity.isDestroyed) {
            activity.runOnUiThread(action)
        }
    }

    override fun setupUnityAdApp(
        context: Context,
        testMode: Boolean,
        unityGameId: String,
        callback: FrogoUnityAdInitializationCallback?
    ) {
        if (unityGameId.isNotBlank()) {
            if (UnityAds.isInitialized) {
                callback?.onInitializationComplete(TAG, "$TAG : onInitializationComplete (already initialized)")
                return
            }
            val config = InitializationConfiguration.Builder(unityGameId)
                .withTestMode(testMode)
                .build()

            UnityAds.initialize(config, object : InitializationListener {
                override fun onInitializationComplete(error: UnityAdsError?) {
                    if (error == null) {
                        callback?.onInitializationComplete(TAG, "$TAG : onInitializationComplete")
                    } else {
                        callback?.onInitializationFailed(
                            TAG,
                            "$TAG: onInitializationFailed with error message : ${error.message}"
                        )
                    }
                }
            })
        } else {
            callback?.onInitializationFailed(TAG, "$TAG : Unity Game Id is Empty")
        }
    }

    override fun showAdInterstitial(
        activity: Activity,
        adInterstitialUnitId: String,
        callback: FrogoUnityAdInterstitialCallback?
    ) {
        if (adInterstitialUnitId.isNotBlank()) {
            if (UnityAds.isInitialized) {
                callback?.onShowAdRequestProgress(TAG, "$TAG [Unity showAdInterstitial] >> Run - onShowAdRequestProgress")
                val loadConfig = LoadConfiguration.Builder(adInterstitialUnitId).build()
                InterstitialAd.load(loadConfig, object : LoadListener<InterstitialAd> {
                    override fun onAdLoaded(unityAd: InterstitialAd?, error: UnityAdsError?) {
                        if (unityAd != null) {
                            activeInterstitialAd = unityAd
                            runOnActivity(activity) {
                                callback?.onAdLoaded(TAG, "$TAG : onUnityAdsAdLoaded $adInterstitialUnitId")
                            }
                            runOnActivity(activity) {
                                val showConfig = ShowConfiguration.Builder().build()
                                unityAd.show(activity, showConfig, object : InterstitialShowListener {
                                    override fun onStarted(unityAd: InterstitialAd) {
                                        runOnActivity(activity) {
                                            callback?.onHideAdRequestProgress(TAG, "$TAG [Unity showAdInterstitial] >> Run - onHideAdRequestProgress : onUnityAdsShowStart")
                                            callback?.onAdShowed(TAG, "$TAG [Unity showAdInterstitial] >> Success - onUnityAdsShowStart [placementId] : $adInterstitialUnitId")
                                        }
                                    }

                                    override fun onClicked(unityAd: InterstitialAd) {
                                        runOnActivity(activity) {
                                            callback?.onClicked(TAG, "$TAG [Unity showAdInterstitial] >> Success - onUnityAdsShowClick [placementId] : $adInterstitialUnitId")
                                        }
                                    }

                                    override fun onCompleted(unityAd: InterstitialAd, state: ShowFinishState) {
                                        activeInterstitialAd = null
                                        runOnActivity(activity) {
                                            callback?.onAdDismissed(TAG, "$TAG [Unity showAdInterstitial] >> Success - onUnityAdsShowComplete [state] : $state, [placement] : $adInterstitialUnitId")
                                        }
                                    }

                                    override fun onFailed(unityAd: InterstitialAd, error: UnityAdsError) {
                                        activeInterstitialAd = null
                                        runOnActivity(activity) {
                                            callback?.onHideAdRequestProgress(TAG, "$TAG [Unity showAdInterstitial] >> Run - onHideAdRequestProgress : onUnityAdsShowFailure")
                                            callback?.onAdFailed(TAG, "$TAG [Unity showAdInterstitial] >> Error - onUnityAdsShowFailure [message] : ${error.message}")
                                        }
                                    }
                                })
                            }
                        } else {
                            activeInterstitialAd = null
                            runOnActivity(activity) {
                                callback?.onHideAdRequestProgress(TAG, "$TAG [Unity showAdInterstitial] >> Run - onHideAdRequestProgress : onUnityAdsShowFailure")
                                callback?.onAdFailed(TAG, "$TAG [Unity showAdInterstitial] >> Error - load failed : ${error?.message}")
                            }
                        }
                    }
                })
            } else {
                callback?.onAdFailed(TAG, "$TAG [Unity showAdInterstitial] >> Error - UnityAds Error Initialized [status] : ${UnityAds.isInitialized}")
            }
        } else {
            callback?.onAdFailed(TAG, "$TAG Unity Ad Interstitial id is Empty")
        }
    }
}
