package com.frogobox.ads.ui.compose

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.core.FrogoAdmobManager

/**
 * Remembers a [FrogoAdmobManager] scoped to the current [LocalLifecycleOwner].
 * Automatically detaches and clears memory when the composable leaves the composition or lifecycle is destroyed.
 */
@Composable
fun rememberFrogoAdmobManager(): FrogoAdmobManager {
    val lifecycleOwner = LocalLifecycleOwner.current
    val manager = remember(lifecycleOwner) {
        FrogoAdmobManager(lifecycleOwner)
    }

    DisposableEffect(lifecycleOwner) {
        onDispose {
            manager.clear()
        }
    }

    return manager
}

/**
 * Controller for Interstitial ad in Jetpack Compose without requiring any Frogo Activity inheritance.
 */
class FrogoAdmobInterstitialLauncher(
    private val activity: Activity?,
    private val manager: FrogoAdmobManager,
    private val adUnitId: String,
    private val callback: FrogoAdmobInterstitialCallback?
) {
    fun load() {
        val appCompat = activity as? androidx.appcompat.app.AppCompatActivity
        if (appCompat != null) {
            manager.loadAdInterstitial(appCompat, adUnitId, callback = callback)
        }
    }

    fun show() {
        val appCompat = activity as? androidx.appcompat.app.AppCompatActivity
        if (appCompat != null) {
            manager.showAdInterstitial(appCompat, adUnitId, callback = callback)
        }
    }

    fun isLoaded(): Boolean = manager.isInterstitialLoaded()
}

/**
 * Remembers a [FrogoAdmobInterstitialLauncher] for showing interstitial ads in Jetpack Compose.
 */
@Composable
fun rememberFrogoAdmobInterstitialLauncher(
    adUnitId: String,
    callback: FrogoAdmobInterstitialCallback? = null
): FrogoAdmobInterstitialLauncher {
    val context = LocalContext.current
    val activity = context as? Activity
    val manager = rememberFrogoAdmobManager()

    return remember(activity, manager, adUnitId, callback) {
        FrogoAdmobInterstitialLauncher(
            activity = activity,
            manager = manager,
            adUnitId = adUnitId,
            callback = callback
        )
    }
}
