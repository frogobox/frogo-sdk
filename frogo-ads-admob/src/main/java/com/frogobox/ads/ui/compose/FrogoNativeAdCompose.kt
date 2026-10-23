package com.frogobox.ads.ui.compose

import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.libraries.ads.mobile.sdk.common.AdChoicesView
import com.google.android.libraries.ads.mobile.sdk.common.LoadAdError
import com.google.android.libraries.ads.mobile.sdk.common.VideoOptions
import com.google.android.libraries.ads.mobile.sdk.nativead.MediaView
import com.google.android.libraries.ads.mobile.sdk.nativead.NativeAd
import com.google.android.libraries.ads.mobile.sdk.nativead.NativeAdLoader
import com.google.android.libraries.ads.mobile.sdk.nativead.NativeAdLoaderCallback
import com.google.android.libraries.ads.mobile.sdk.nativead.NativeAdRequest
import com.google.android.libraries.ads.mobile.sdk.nativead.NativeAdView

/** CompositionLocal providing the current [NativeAd] to child asset composables. */
val LocalFrogoNativeAd = staticCompositionLocalOf<NativeAd?> { null }

/** CompositionLocal providing the host [NativeAdView] to child asset composables. */
val LocalFrogoNativeAdView = staticCompositionLocalOf<NativeAdView?> { null }

/** CompositionLocal providing registration callback for [MediaView]. */
val LocalFrogoMediaViewRegister = staticCompositionLocalOf<(MediaView?) -> Unit> { {} }

/**
 * Root Compose container for Google Mobile Ads Next-Gen Native Ad ([NativeAdView]).
 * Wraps [NativeAdView] and provisions CompositionLocals for all native ad asset children.
 */
@Composable
fun FrogoNativeAdView(
    nativeAd: NativeAd,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val nativeAdViewRef = remember { mutableStateOf<NativeAdView?>(null) }
    val mediaViewRef = remember { mutableStateOf<MediaView?>(null) }

    AndroidView(
        factory = { context ->
            val composeView = ComposeView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                )
            }
            NativeAdView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                )
                addView(composeView)
                nativeAdViewRef.value = this
            }
        },
        modifier = modifier,
        update = { view ->
            val composeView = view.getChildAt(0) as? ComposeView
            composeView?.setContent {
                val registerMediaView: (MediaView?) -> Unit = remember { { mv -> mediaViewRef.value = mv } }
                CompositionLocalProvider(
                    LocalFrogoNativeAdView provides view,
                    LocalFrogoNativeAd provides nativeAd,
                    LocalFrogoMediaViewRegister provides registerMediaView,
                ) {
                    content()
                }
            }
        },
    )

    val currentNativeAd by rememberUpdatedState(nativeAd)
    val currentNativeAdView = nativeAdViewRef.value
    val currentMediaView = mediaViewRef.value

    DisposableEffect(currentNativeAd, currentNativeAdView, currentMediaView) {
        currentNativeAdView?.registerNativeAdSafely(currentNativeAd, currentMediaView)
        onDispose {
            // SDK handles unregistering when detached from window.
        }
    }
}

private fun NativeAdView.registerNativeAdSafely(nativeAd: NativeAd, mediaView: MediaView?) {
    if (mediaView != null) {
        mediaView.post { this.registerNativeAd(nativeAd, mediaView) }
    } else {
        this.registerNativeAd(nativeAd, null)
    }
}

/** Wrapper container for headline view inside a [FrogoNativeAdView]. */
@Composable
fun FrogoNativeAdHeadlineView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val nativeAdView = LocalFrogoNativeAdView.current
        ?: throw IllegalStateException("FrogoNativeAdHeadlineView must be called inside FrogoNativeAdView")
    AndroidView(
        factory = { context -> ComposeView(context) },
        modifier = modifier,
        update = { view ->
            nativeAdView.headlineView = view
            view.setContent(content)
        },
    )
}

/** Wrapper container for body text view inside a [FrogoNativeAdView]. */
@Composable
fun FrogoNativeAdBodyView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val nativeAdView = LocalFrogoNativeAdView.current
        ?: throw IllegalStateException("FrogoNativeAdBodyView must be called inside FrogoNativeAdView")
    AndroidView(
        factory = { context -> ComposeView(context) },
        modifier = modifier,
        update = { view ->
            nativeAdView.bodyView = view
            view.setContent(content)
        },
    )
}

/** Wrapper container for icon view inside a [FrogoNativeAdView]. */
@Composable
fun FrogoNativeAdIconView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val nativeAdView = LocalFrogoNativeAdView.current
        ?: throw IllegalStateException("FrogoNativeAdIconView must be called inside FrogoNativeAdView")
    AndroidView(
        factory = { context -> ComposeView(context) },
        modifier = modifier,
        update = { view ->
            nativeAdView.iconView = view
            view.setContent(content)
        },
    )
}

/** Wrapper container for advertiser text view inside a [FrogoNativeAdView]. */
@Composable
fun FrogoNativeAdAdvertiserView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val nativeAdView = LocalFrogoNativeAdView.current
        ?: throw IllegalStateException("FrogoNativeAdAdvertiserView must be called inside FrogoNativeAdView")
    AndroidView(
        factory = { context -> ComposeView(context) },
        modifier = modifier,
        update = { view ->
            nativeAdView.advertiserView = view
            view.setContent(content)
        },
    )
}

/** Wrapper for MediaView inside a [FrogoNativeAdView]. */
@Composable
fun FrogoNativeAdMediaView(
    modifier: Modifier = Modifier,
    scaleType: ImageView.ScaleType? = null
) {
    val registerMediaView = LocalFrogoMediaViewRegister.current
    AndroidView(
        factory = { context -> MediaView(context) },
        update = { view ->
            registerMediaView(view)
            scaleType?.let { type -> view.imageScaleType = type }
        },
        modifier = modifier,
    )

    DisposableEffect(Unit) {
        onDispose { registerMediaView(null) }
    }
}

/** Wrapper container for Call To Action button view inside a [FrogoNativeAdView]. */
@Composable
fun FrogoNativeAdCallToActionView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val nativeAdView = LocalFrogoNativeAdView.current
        ?: throw IllegalStateException("FrogoNativeAdCallToActionView must be called inside FrogoNativeAdView")
    AndroidView(
        factory = { context -> ComposeView(context) },
        modifier = modifier,
        update = { view ->
            nativeAdView.callToActionView = view
            view.setContent(content)
        },
    )
}

/** Wrapper container for star rating view inside a [FrogoNativeAdView]. */
@Composable
fun FrogoNativeAdStarRatingView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val nativeAdView = LocalFrogoNativeAdView.current
        ?: throw IllegalStateException("FrogoNativeAdStarRatingView must be called inside FrogoNativeAdView")
    AndroidView(
        factory = { context -> ComposeView(context) },
        modifier = modifier,
        update = { view ->
            nativeAdView.starRatingView = view
            view.setContent(content)
        },
    )
}

/** Wrapper container for store view inside a [FrogoNativeAdView]. */
@Composable
fun FrogoNativeAdStoreView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val nativeAdView = LocalFrogoNativeAdView.current
        ?: throw IllegalStateException("FrogoNativeAdStoreView must be called inside FrogoNativeAdView")
    AndroidView(
        factory = { context -> ComposeView(context) },
        modifier = modifier,
        update = { view ->
            nativeAdView.storeView = view
            view.setContent(content)
        },
    )
}

/** Wrapper container for price view inside a [FrogoNativeAdView]. */
@Composable
fun FrogoNativeAdPriceView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val nativeAdView = LocalFrogoNativeAdView.current
        ?: throw IllegalStateException("FrogoNativeAdPriceView must be called inside FrogoNativeAdView")
    AndroidView(
        factory = { context -> ComposeView(context) },
        modifier = modifier,
        update = { view ->
            nativeAdView.priceView = view
            view.setContent(content)
        },
    )
}

/** Wrapper for AdChoicesView inside a [FrogoNativeAdView]. */
@Composable
fun FrogoNativeAdChoicesView(modifier: Modifier = Modifier) {
    val nativeAdView = LocalFrogoNativeAdView.current
        ?: throw IllegalStateException("FrogoNativeAdChoicesView must be called inside FrogoNativeAdView")
    AndroidView(
        factory = { context ->
            AdChoicesView(context).apply {
                minimumWidth = 15
                minimumHeight = 15
            }
        },
        modifier = modifier,
        update = { view -> nativeAdView.adChoicesView = view },
    )
}

/** Composable attribution badge identifying the content as an advertisement. */
@Composable
fun FrogoNativeAdAttribution(
    modifier: Modifier = Modifier,
    text: String = "Ad",
    shape: Shape = MaterialTheme.shapes.extraSmall,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    padding: PaddingValues = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
) {
    Box(
        modifier = modifier
            .background(color = containerColor, shape = shape)
            .padding(padding)
    ) {
        Text(text = text, color = contentColor, style = MaterialTheme.typography.labelSmall)
    }
}

/** Accessible composable button for native ad actions. */
@Composable
fun FrogoNativeAdButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
    ) {
        Text(text = text)
    }
}

/**
 * High-level self-loading Composable for Google Mobile Ads Next-Gen Native Ads.
 * Loads the [NativeAd], manages lifecycle cleanup on disposal, and executes the [content] block.
 *
 * @param adUnitId The AdMob Native ad unit ID.
 * @param modifier Composable layout modifier.
 * @param videoOptions Optional [VideoOptions] (e.g. muted start).
 * @param onAdFailed Optional callback invoked if ad loading fails.
 * @param content Composable slot invoked when [NativeAd] is ready.
 */
@Composable
fun FrogoNativeAdCompose(
    adUnitId: String,
    modifier: Modifier = Modifier,
    videoOptions: VideoOptions? = null,
    onAdFailed: ((LoadAdError) -> Unit)? = null,
    content: @Composable (nativeAd: NativeAd) -> Unit
) {
    val isInspectionMode = LocalInspectionMode.current
    if (isInspectionMode) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Frogo Native Ad Preview ($adUnitId)",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        return
    }

    var nativeAdState by remember(adUnitId) { mutableStateOf<NativeAd?>(null) }
    var isDisposed by remember { mutableStateOf(false) }

    LaunchedEffect(adUnitId) {
        val requestBuilder = NativeAdRequest.Builder(adUnitId, listOf(NativeAd.NativeAdType.NATIVE))
        videoOptions?.let { requestBuilder.setVideoOptions(it) }

        NativeAdLoader.load(
            requestBuilder.build(),
            object : NativeAdLoaderCallback {
                override fun onNativeAdLoaded(nativeAd: NativeAd) {
                    if (!isDisposed) {
                        nativeAdState = nativeAd
                    } else {
                        nativeAd.destroy()
                    }
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    onAdFailed?.invoke(adError)
                }
            }
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            isDisposed = true
            nativeAdState?.destroy()
            nativeAdState = null
        }
    }

    Box(modifier = modifier) {
        nativeAdState?.let { ad ->
            FrogoNativeAdView(nativeAd = ad) {
                content(ad)
            }
        }
    }
}
