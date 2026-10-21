package com.frogobox.ads.core

import android.app.Activity
import com.frogobox.ads.callback.FrogoAdmobAppOpenAdCallback
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import android.os.Looper

@RunWith(RobolectricTestRunner::class)
class FrogoAppOpenAdManagerTest {

    @Test
    fun testInitialState_isAdAvailableReturnsFalse() {
        val manager = FrogoAppOpenAdManager()
        assertFalse(manager.isAdAvailable())
        assertFalse(manager.isLoadingAd)
        assertFalse(manager.isShowingAd)
        assertEquals(0L, manager.loadTimeMillis)
    }

    @Test
    fun test4HourExpirationLogic() {
        val manager = FrogoAppOpenAdManager()
        val currentTime = System.currentTimeMillis()

        // 0 hours elapsed -> valid
        manager.loadTime = currentTime
        assertTrue(manager.wasLoadTimeLessThanNHoursAgo(4))

        // 3 hours elapsed -> valid
        manager.loadTime = currentTime - (3 * 3600000L)
        assertTrue(manager.wasLoadTimeLessThanNHoursAgo(4))

        // 4 hours + 1 minute elapsed -> expired
        manager.loadTime = currentTime - (4 * 3600000L + 60000L)
        assertFalse(manager.wasLoadTimeLessThanNHoursAgo(4))
    }

    @Test
    fun testClearAd_resetsAllProperties() {
        val manager = FrogoAppOpenAdManager()
        manager.loadTime = System.currentTimeMillis()
        manager.isShowingAd = true

        manager.clearAd()

        assertEquals(0L, manager.loadTimeMillis)
        assertFalse(manager.isAdAvailable())
        assertFalse(manager.isShowingAd)
        assertFalse(manager.isLoadingAd)
    }

    @Test
    fun testLoadAd_blankAdUnitIdTriggersOnAdFailed() {
        val manager = FrogoAppOpenAdManager()
        var failedCalled = false
        var errorMsg = ""

        manager.loadAd(
            RuntimeEnvironment.getApplication(),
            "",
            object : FrogoAdmobAppOpenAdCallback {
                override fun onShowAdRequestProgress(tag: String, message: String) {}
                override fun onHideAdRequestProgress(tag: String, message: String) {}
                override fun onAdDismissed(tag: String, message: String) {}
                override fun onAdFailed(tag: String, errorMessage: String) {
                    failedCalled = true
                    errorMsg = errorMessage
                }
                override fun onAdLoaded(tag: String, message: String) {}
                override fun onAdShowed(tag: String, message: String) {}
            }
        )

        shadowOf(Looper.getMainLooper()).idle()

        assertTrue(failedCalled)
        assertTrue(errorMsg.contains("blank"))
        assertFalse(manager.isLoadingAd)
    }

    @Test
    fun testShowAdIfAvailable_destroyedActivityTriggersOnAdFailed() {
        val manager = FrogoAppOpenAdManager()
        val controller = Robolectric.buildActivity(Activity::class.java).setup()
        val activity = controller.get()
        controller.destroy()

        var failedCalled = false
        var errorMsg = ""

        manager.showAdIfAvailable(
            activity,
            "test-ad-unit",
            object : FrogoAdmobAppOpenAdCallback {
                override fun onShowAdRequestProgress(tag: String, message: String) {}
                override fun onHideAdRequestProgress(tag: String, message: String) {}
                override fun onAdDismissed(tag: String, message: String) {}
                override fun onAdFailed(tag: String, errorMessage: String) {
                    failedCalled = true
                    errorMsg = errorMessage
                }
                override fun onAdLoaded(tag: String, message: String) {}
                override fun onAdShowed(tag: String, message: String) {}
            }
        )

        shadowOf(Looper.getMainLooper()).idle()

        assertTrue(failedCalled)
        assertTrue(errorMsg.contains("finishing or destroyed"))
        assertFalse(manager.isShowingAd)
    }

    @Test
    fun testShowAdIfAvailable_finishingActivityTriggersOnAdFailed() {
        val manager = FrogoAppOpenAdManager()
        val controller = Robolectric.buildActivity(Activity::class.java).setup()
        val activity = controller.get()
        activity.finish()

        var failedCalled = false

        manager.showAdIfAvailable(
            activity,
            "test-ad-unit",
            object : FrogoAdmobAppOpenAdCallback {
                override fun onShowAdRequestProgress(tag: String, message: String) {}
                override fun onHideAdRequestProgress(tag: String, message: String) {}
                override fun onAdDismissed(tag: String, message: String) {}
                override fun onAdFailed(tag: String, errorMessage: String) {
                    failedCalled = true
                }
                override fun onAdLoaded(tag: String, message: String) {}
                override fun onAdShowed(tag: String, message: String) {}
            }
        )

        shadowOf(Looper.getMainLooper()).idle()

        assertTrue(failedCalled)
        assertFalse(manager.isShowingAd)
    }

    @Test
    fun testLoadAndShowAd_destroyedActivityTriggersOnAdFailed() {
        val manager = FrogoAppOpenAdManager()
        val controller = Robolectric.buildActivity(Activity::class.java).setup()
        val activity = controller.get()
        controller.destroy()

        var failedCalled = false

        manager.loadAndShowAd(
            activity,
            "test-ad-unit",
            object : FrogoAdmobAppOpenAdCallback {
                override fun onShowAdRequestProgress(tag: String, message: String) {}
                override fun onHideAdRequestProgress(tag: String, message: String) {}
                override fun onAdDismissed(tag: String, message: String) {}
                override fun onAdFailed(tag: String, errorMessage: String) {
                    failedCalled = true
                }
                override fun onAdLoaded(tag: String, message: String) {}
                override fun onAdShowed(tag: String, message: String) {}
            }
        )

        shadowOf(Looper.getMainLooper()).idle()

        assertTrue(failedCalled)
    }
}
