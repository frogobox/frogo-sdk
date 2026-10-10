package com.frogobox.ads.ui.compose

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback
import com.frogobox.ads.core.FrogoUnityAdManager

/**
 * Remembers a [FrogoUnityAdManager] scoped to the current [LocalLifecycleOwner].
 */
@Composable
fun rememberFrogoUnityAdManager(): FrogoUnityAdManager {
    val lifecycleOwner = LocalLifecycleOwner.current
    val manager = remember(lifecycleOwner) {
        FrogoUnityAdManager(lifecycleOwner)
    }

    DisposableEffect(lifecycleOwner) {
        onDispose {
            manager.clear()
        }
    }

    return manager
}

/**
 * Controller for Unity Interstitial ad in Jetpack Compose without requiring any Frogo Activity inheritance.
 */
class FrogoUnityInterstitialLauncher(
    private val activity: Activity?,
    private val manager: FrogoUnityAdManager,
    private val adUnitId: String,
    private val callback: FrogoUnityAdInterstitialCallback?
) {
    fun show() {
        if (activity != null) {
            manager.showAdInterstitial(activity, adUnitId, callback = callback)
        }
    }
}

/**
 * Remembers a [FrogoUnityInterstitialLauncher] for showing Unity interstitial ads in Jetpack Compose.
 */
@Composable
fun rememberFrogoUnityInterstitialLauncher(
    adUnitId: String,
    callback: FrogoUnityAdInterstitialCallback? = null
): FrogoUnityInterstitialLauncher {
    val context = LocalContext.current
    val activity = context as? Activity
    val manager = rememberFrogoUnityAdManager()

    return remember(activity, manager, adUnitId, callback) {
        FrogoUnityInterstitialLauncher(
            activity = activity,
            manager = manager,
            adUnitId = adUnitId,
            callback = callback
        )
    }
}
