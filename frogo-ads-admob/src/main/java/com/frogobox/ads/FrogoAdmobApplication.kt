package com.frogobox.ads

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.webkit.WebView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.frogobox.ads.callback.FrogoAdmobAppOpenAdCallback
import com.frogobox.ads.core.FrogoAppOpenAdManager
import com.frogobox.ads.core.IFrogoAppOpenAdOptOut
import com.frogobox.sdk.FrogoApplication
import com.google.android.libraries.ads.mobile.sdk.MobileAds
import com.google.android.libraries.ads.mobile.sdk.initialization.InitializationConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Created by Faisal Amir on 24/10/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */

open class FrogoAdmobApplication : FrogoApplication(),
    Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {

    companion object {
        const val LOG_TAG = "FrogoAdmobApplication"
    }

    private lateinit var appOpenAdManager: FrogoAppOpenAdManager
    private var currentActivity: Activity? = null

    open fun getCurrentActivity(): Activity? = currentActivity

    override fun onCreate() {
        super<FrogoApplication>.onCreate()
        registerActivityLifecycleCallbacks(this)

        prewarmWebView()

        appOpenAdManager = FrogoAppOpenAdManager()

        val backgroundScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        backgroundScope.launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            val appId = try {
                val appInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    packageManager.getApplicationInfo(
                        packageName,
                        android.content.pm.PackageManager.ApplicationInfoFlags.of(
                            android.content.pm.PackageManager.GET_META_DATA.toLong()
                        )
                    )
                } else {
                    packageManager.getApplicationInfo(
                        packageName,
                        android.content.pm.PackageManager.GET_META_DATA
                    )
                }
                appInfo.metaData?.getString("com.google.android.gms.ads.APPLICATION_ID").orEmpty()
            } catch (e: Exception) {
                ""
            }
            try {
                val config = InitializationConfig.Builder(appId).build()
                MobileAds.initialize(this@FrogoAdmobApplication, config) {}
            } catch (e: Throwable) {
                android.util.Log.e(LOG_TAG, "MobileAds initialization encountered error: ${e.message}")
            }

            // Proactively preload App Open Ad on cold start
            if (isAppOpenAdEnabled() && isOpenAppAdEnabled()) {
                val adUnitId = getAdOpenAppUnitId(this@FrogoAdmobApplication)
                if (adUnitId.isNotBlank()) {
                    appOpenAdManager.loadAd(this@FrogoAdmobApplication, adUnitId)
                }
            }
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    /** ActivityLifecycleCallback methods. */
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {
        // Update currentActivity only when an ad is not showing to prevent caching third-party ad activities
        if (!appOpenAdManager.isShowingAd) {
            currentActivity = activity
        }
    }

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        // Clear the reference to avoid Activity memory leak
        if (currentActivity === activity) {
            currentActivity = null
        }
    }

    /** App Open Ad unit ID provider. Override this in your Application subclass. */
    open fun getAdOpenAppUnitId(context: Context?): String {
        return ""
    }

    /** Global toggle for App Open Ad monetisation (e.g., VIP/ad-free users). */
    open fun isAppOpenAdEnabled(): Boolean = true

    /** Alias for backward compatibility with older v3.0 APIs. */
    open fun isOpenAppAdEnabled(): Boolean = isAppOpenAdEnabled()

    /** Determines whether the app should automatically show App Open Ad upon foregrounding. */
    open fun isAutoShowAppOpenAdOnForegroundEnabled(): Boolean = true

    /**
     * Inspects the target foreground activity to decide if an App Open Ad should be shown.
     * Checks global toggles, opt-out marker interfaces, and activity validity.
     */
    open fun shouldShowAppOpenAd(activity: Activity): Boolean {
        if (!isAppOpenAdEnabled() || !isOpenAppAdEnabled()) return false
        if (!isAutoShowAppOpenAdOnForegroundEnabled()) return false
        if (activity is IFrogoAppOpenAdOptOut && activity.isAppOpenAdOptedOut()) return false
        if (activity.isFinishing || activity.isDestroyed) return false
        return true
    }

    /** DefaultLifecycleObserver method that shows the app open ad when the app moves to foreground. */
    override fun onStart(owner: LifecycleOwner) {
        val activity = currentActivity ?: return
        if (!shouldShowAppOpenAd(activity)) {
            return
        }

        val adUnitId = getAdOpenAppUnitId(activity)
        if (adUnitId.isNotBlank()) {
            appOpenAdManager.showAdIfAvailable(activity, adUnitId)
        }
    }

    // =============================================================================================
    // Public App Open Ad Accessors
    // =============================================================================================

    fun isAppOpenAdAvailable(): Boolean = appOpenAdManager.isAdAvailable()

    fun isAppOpenAdLoading(): Boolean = appOpenAdManager.isLoadingAd

    fun isAppOpenAdShowing(): Boolean = appOpenAdManager.isShowingAd

    fun getAppOpenAdLoadTime(): Long = appOpenAdManager.loadTimeMillis

    fun getAppOpenAdManager(): FrogoAppOpenAdManager = appOpenAdManager

    internal fun setAppOpenAdManager(manager: FrogoAppOpenAdManager) {
        this.appOpenAdManager = manager
    }

    fun loadAppOpenAd(
        context: Context,
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback? = null
    ) {
        appOpenAdManager.loadAd(context, appOpenAdUnitId, callback)
    }

    fun showAppOpenAdIfAvailable(
        activity: Activity,
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback? = null
    ) {
        appOpenAdManager.showAdIfAvailable(activity, appOpenAdUnitId, callback)
    }

    fun showAdIfAvailable(
        activity: Activity,
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback
    ) {
        appOpenAdManager.showAdIfAvailable(activity, appOpenAdUnitId, callback)
    }

    fun loadAndShowAppOpenAd(
        activity: Activity,
        appOpenAdUnitId: String,
        callback: FrogoAdmobAppOpenAdCallback? = null
    ) {
        appOpenAdManager.loadAndShowAd(activity, appOpenAdUnitId, callback)
    }

    fun clearAppOpenAd() {
        appOpenAdManager.clearAd()
    }

    /**
     * Pre-warms the Chromium WebView engine on an idle main looper pass.
     * Prevents synchronous Binder IPC / InputDispatch ANRs when the first ad (WebView) is loaded.
     */
    open fun prewarmWebView() {
        Looper.myQueue().addIdleHandler {
            try {
                WebView(this@FrogoAdmobApplication)
            } catch (_: Throwable) {
                // Silently ignore if WebView is disabled or unavailable on device
            }
            false
        }
    }

}
