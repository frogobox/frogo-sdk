package com.frogobox.ads.ui

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.ext.showAdmobInterstitial
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FrogoAdmobActivityTest {

    class TestAdmobActivity : FrogoAdmobActivity()

    @Test
    fun testAdmobActivityLaunch() {
        val controller = Robolectric.buildActivity(TestAdmobActivity::class.java).setup()
        val activity = controller.get()
        assertNotNull(activity)
    }

    @Test
    fun testAdmobExtensionWithoutInheritance() {
        val controller = Robolectric.buildActivity(AppCompatActivity::class.java).setup()
        val activity = controller.get()
        assertNotNull(activity)
        val callback = mockk<FrogoAdmobInterstitialCallback>(relaxed = true)
        activity.showAdmobInterstitial("", callback = callback)
        verify(exactly = 1) { callback.onAdFailed(any(), any()) }
    }
}
