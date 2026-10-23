package com.frogobox.ads.core

import android.app.Activity
import com.frogobox.ads.callback.FrogoAdmobAppOpenAdCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class FrogoAdmobPreloaderTest {

    @Test
    fun testEmptyAdUnitIds_safeNoOp() {
        assertFalse(FrogoAdmobPreloader.isInterstitialAvailable(""))
        assertNull(FrogoAdmobPreloader.pollInterstitial(""))
        assertNull(FrogoAdmobPreloader.peekInterstitialResponseInfo(""))

        assertFalse(FrogoAdmobPreloader.isRewardedAvailable(""))
        assertNull(FrogoAdmobPreloader.pollRewarded(""))
        assertNull(FrogoAdmobPreloader.peekRewardedResponseInfo(""))

        assertFalse(FrogoAdmobPreloader.isRewardedInterstitialAvailable(""))
        assertNull(FrogoAdmobPreloader.pollRewardedInterstitial(""))
        assertNull(FrogoAdmobPreloader.peekRewardedInterstitialResponseInfo(""))

        assertFalse(FrogoAdmobPreloader.isAppOpenAvailable(""))
        assertNull(FrogoAdmobPreloader.pollAppOpen(""))
        assertNull(FrogoAdmobPreloader.peekAppOpenResponseInfo(""))

        val mockActivity = mockk<Activity>(relaxed = true)
        assertFalse(FrogoAdmobPreloader.pollAndShowInterstitial(mockActivity, ""))
        assertFalse(FrogoAdmobPreloader.pollAndShowRewarded(mockActivity, "", mockk(relaxed = true)))
        assertFalse(FrogoAdmobPreloader.pollAndShowRewardedInterstitial(mockActivity, "", mockk(relaxed = true)))
        assertFalse(FrogoAdmobPreloader.pollAndShowAppOpen(mockActivity, ""))
    }

    @Test
    fun testAvailabilityAndPoll_whenUninitialized_returnSafeDefaults() {
        val testUnitId = "test-ad-unit-id"

        assertFalse(FrogoAdmobPreloader.isInterstitialAvailable(testUnitId))
        assertNull(FrogoAdmobPreloader.pollInterstitial(testUnitId))
        assertNull(FrogoAdmobPreloader.peekInterstitialResponseInfo(testUnitId))

        assertFalse(FrogoAdmobPreloader.isRewardedAvailable(testUnitId))
        assertNull(FrogoAdmobPreloader.pollRewarded(testUnitId))
        assertNull(FrogoAdmobPreloader.peekRewardedResponseInfo(testUnitId))

        assertFalse(FrogoAdmobPreloader.isRewardedInterstitialAvailable(testUnitId))
        assertNull(FrogoAdmobPreloader.pollRewardedInterstitial(testUnitId))
        assertNull(FrogoAdmobPreloader.peekRewardedInterstitialResponseInfo(testUnitId))

        assertFalse(FrogoAdmobPreloader.isAppOpenAvailable(testUnitId))
        assertNull(FrogoAdmobPreloader.pollAppOpen(testUnitId))
        assertNull(FrogoAdmobPreloader.peekAppOpenResponseInfo(testUnitId))
    }

    @Test
    fun testPollAndShow_whenAdUnavailable_triggersFailureCallback() {
        val mockActivity = mockk<Activity>(relaxed = true)
        val mockInterstitialCallback = mockk<FrogoAdmobInterstitialCallback>(relaxed = true)
        val mockRewardedCallback = mockk<FrogoAdmobRewardedCallback>(relaxed = true)
        val mockAppOpenCallback = mockk<FrogoAdmobAppOpenAdCallback>(relaxed = true)

        val interstitialResult = FrogoAdmobPreloader.pollAndShowInterstitial(
            mockActivity,
            "unavailable-unit-id",
            mockInterstitialCallback
        )
        assertFalse(interstitialResult)
        verify(exactly = 1) {
            mockInterstitialCallback.onAdFailed(
                "FrogoAdmobPreloader",
                "Interstitial ad is not available in preloader buffer."
            )
        }

        val rewardedResult = FrogoAdmobPreloader.pollAndShowRewarded(
            mockActivity,
            "unavailable-unit-id",
            mockRewardedCallback
        )
        assertFalse(rewardedResult)
        verify(exactly = 1) {
            mockRewardedCallback.onAdFailed(
                "FrogoAdmobPreloader",
                "Rewarded ad is not available in preloader buffer."
            )
        }

        val rewardedInterstitialResult = FrogoAdmobPreloader.pollAndShowRewardedInterstitial(
            mockActivity,
            "unavailable-unit-id",
            mockRewardedCallback
        )
        assertFalse(rewardedInterstitialResult)
        verify(exactly = 1) {
            mockRewardedCallback.onAdFailed(
                "FrogoAdmobPreloader",
                "Rewarded Interstitial ad is not available in preloader buffer."
            )
        }

        val appOpenResult = FrogoAdmobPreloader.pollAndShowAppOpen(
            mockActivity,
            "unavailable-unit-id",
            mockAppOpenCallback
        )
        assertFalse(appOpenResult)
        verify(exactly = 1) {
            mockAppOpenCallback.onAdFailed(
                "FrogoAdmobPreloader",
                "App Open ad is not available in preloader buffer."
            )
        }
    }

    @Test
    fun testStartPreloadMethods_safeExecution() {
        // Ensure starting preloaders with valid and invalid IDs executes safely
        FrogoAdmobPreloader.startInterstitialPreload("", bufferSize = 1)
        FrogoAdmobPreloader.startInterstitialPreload("test-id", bufferSize = 1)

        FrogoAdmobPreloader.startRewardedPreload("", bufferSize = 1)
        FrogoAdmobPreloader.startRewardedPreload("test-id", bufferSize = 1)

        FrogoAdmobPreloader.startRewardedInterstitialPreload("", bufferSize = 1)
        FrogoAdmobPreloader.startRewardedInterstitialPreload("test-id", bufferSize = 1)

        FrogoAdmobPreloader.startAppOpenPreload("", bufferSize = 1)
        FrogoAdmobPreloader.startAppOpenPreload("test-id", bufferSize = 1)
    }
}
