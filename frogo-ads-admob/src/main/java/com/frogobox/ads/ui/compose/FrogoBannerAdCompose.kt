package com.frogobox.ads.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.frogobox.ads.callback.FrogoAdmobBannerCallback
import com.frogobox.ads.core.FrogoAdmob
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize
import com.google.android.libraries.ads.mobile.sdk.banner.AdView

/**
 * Modern Jetpack Compose wrapper for Google Mobile Ads Next-Gen Banner Ad ([AdView]).
 *
 * Automatically manages the Android lifecycle and disposes the native [AdView] when the
 * Composable leaves the composition, avoiding memory leaks.
 *
 * Example usage:
 * ```kotlin
 * FrogoBannerAdCompose(
 *     adUnitId = "ca-app-pub-3940256099942544/6300978111",
 *     modifier = Modifier.fillMaxWidth()
 * )
 * ```
 *
 * @param adUnitId The AdMob Banner ad unit ID.
 * @param modifier Composable layout modifier.
 * @param adSize The Banner [AdSize], defaults to [AdSize.BANNER].
 * @param timeoutMilliSecond Optional timeout in milliseconds for the ad request.
 * @param keyword Optional list of keyword targeting strings.
 * @param callback Optional banner lifecycle callback.
 */
@Composable
fun FrogoBannerAdCompose(
    adUnitId: String,
    modifier: Modifier = Modifier,
    adSize: AdSize = AdSize.BANNER,
    timeoutMilliSecond: Int? = null,
    keyword: List<String>? = null,
    callback: FrogoAdmobBannerCallback? = null
) {
    val isInspectionMode = LocalInspectionMode.current
    if (isInspectionMode) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Frogo Banner Ad ($adUnitId)",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        return
    }

    val context = LocalContext.current
    val adView = remember(adUnitId, adSize) {
        AdView(context).apply {
            FrogoAdmob.showAdBanner(
                mAdView = this,
                bannerAdUnitId = adUnitId,
                mAdsSize = adSize,
                timeoutMilliSecond = timeoutMilliSecond,
                keyword = keyword,
                callback = callback
            )
        }
    }

    DisposableEffect(adView) {
        onDispose {
            adView.destroy()
        }
    }

    AndroidView(
        factory = { adView },
        modifier = modifier
    )
}
