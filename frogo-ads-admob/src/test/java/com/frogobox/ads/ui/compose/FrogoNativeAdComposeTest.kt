package com.frogobox.ads.ui.compose

import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import com.google.android.libraries.ads.mobile.sdk.nativead.NativeAd
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class FrogoNativeAdComposeTest {

    @Test
    fun testFrogoNativeAdCompose_previewModeRendersSafely() {
        val activity = Robolectric.buildActivity(ComponentActivity::class.java).setup().get()
        val composeView = ComposeView(activity)

        composeView.setContent {
            FrogoNativeAdCompose(
                adUnitId = "ca-app-pub-3940256099942544/2247696110"
            ) { _ ->
                Text("Ad Content")
            }
        }

        activity.setContentView(composeView)
        composeView.measure(
            View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1920, View.MeasureSpec.EXACTLY)
        )
        composeView.layout(0, 0, 1080, 1920)

        org.robolectric.Shadows.shadowOf(android.os.Looper.getMainLooper()).idle()
        assertNotNull(composeView)
    }

    @Test
    fun testFrogoNativeAdView_rendersComponentsSafely() {
        val activity = Robolectric.buildActivity(ComponentActivity::class.java).setup().get()
        val composeView = ComposeView(activity)
        val mockNativeAd = mockk<NativeAd>(relaxed = true)

        composeView.setContent {
            FrogoNativeAdView(nativeAd = mockNativeAd) {
                FrogoNativeAdHeadlineView {
                    Text("Headline")
                }
                FrogoNativeAdBodyView {
                    Text("Body")
                }
                FrogoNativeAdAttribution()
                FrogoNativeAdButton(text = "Install Now")
            }
        }

        activity.setContentView(composeView)
        composeView.measure(
            View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1920, View.MeasureSpec.EXACTLY)
        )
        composeView.layout(0, 0, 1080, 1920)

        org.robolectric.Shadows.shadowOf(android.os.Looper.getMainLooper()).idle()
        assertNotNull(composeView)
    }

    @Test
    fun testFrogoNativeAdAttribution_and_Button_renderSafely() {
        val activity = Robolectric.buildActivity(ComponentActivity::class.java).setup().get()
        val composeView = ComposeView(activity)

        composeView.setContent {
            FrogoNativeAdAttribution()
            FrogoNativeAdButton(text = "Learn More")
        }

        activity.setContentView(composeView)
        composeView.measure(
            View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1920, View.MeasureSpec.EXACTLY)
        )
        composeView.layout(0, 0, 1080, 1920)

        org.robolectric.Shadows.shadowOf(android.os.Looper.getMainLooper()).idle()
        assertNotNull(composeView)
    }
}
