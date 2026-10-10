package com.frogobox.ads.core

import androidx.appcompat.app.AppCompatActivity
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FrogoAdmobManagerTest {

    @Test
    fun testLifecycleAutoCleanup() {
        val controller = Robolectric.buildActivity(AppCompatActivity::class.java).setup()
        val activity = controller.get()
        val manager = FrogoAdmobManager(activity)

        assertNotNull(manager)
        assertFalse(manager.isInterstitialLoaded())
        assertFalse(manager.isRewardedLoaded())

        // Move to destroyed
        controller.destroy()

        assertFalse(manager.isInterstitialLoaded())
        assertFalse(manager.isRewardedLoaded())
    }

    @Test
    fun testManualClear() {
        val manager = FrogoAdmobManager()
        manager.clear()
        assertFalse(manager.isInterstitialLoaded())
        assertFalse(manager.isRewardedLoaded())
        assertFalse(manager.isRewardedInterstitialLoaded())
    }
}
