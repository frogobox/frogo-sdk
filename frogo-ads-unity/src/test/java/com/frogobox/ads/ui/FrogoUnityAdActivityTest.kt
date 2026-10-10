package com.frogobox.ads.ui

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback
import com.frogobox.ads.ext.showUnityInterstitial
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FrogoUnityAdActivityTest {

    class TestUnityActivity : FrogoUnityAdActivity()

    @Test
    fun testUnityActivityLaunch() {
        val controller = Robolectric.buildActivity(TestUnityActivity::class.java).setup()
        val activity = controller.get()
        assertNotNull(activity)
    }

    @Test
    fun testUnityExtensionWithoutInheritance() {
        val controller = Robolectric.buildActivity(AppCompatActivity::class.java).setup()
        val activity = controller.get()
        assertNotNull(activity)
        val callback = mockk<FrogoUnityAdInterstitialCallback>(relaxed = true)
        activity.showUnityInterstitial("", callback)
        verify(exactly = 1) { callback.onAdFailed(any(), any()) }
    }
}
