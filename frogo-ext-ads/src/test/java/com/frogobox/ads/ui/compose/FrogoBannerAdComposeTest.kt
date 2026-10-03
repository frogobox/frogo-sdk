package com.frogobox.ads.ui.compose

import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class FrogoBannerAdComposeTest {

    @Test
    fun testFrogoBannerAdComposeInspectionModePreview() {
        val activity = Robolectric.buildActivity(ComponentActivity::class.java).setup().get()
        val composeView = ComposeView(activity)

        composeView.setContent {
            FrogoBannerAdCompose(
                adUnitId = "ca-app-pub-3940256099942544/6300978111"
            )
        }

        activity.setContentView(composeView)
        composeView.measure(
            View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1920, View.MeasureSpec.EXACTLY)
        )
        composeView.layout(0, 0, 1080, 1920)

        assertNotNull(composeView)
    }
}
