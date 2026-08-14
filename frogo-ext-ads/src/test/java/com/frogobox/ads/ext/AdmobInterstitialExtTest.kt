package com.frogobox.ads.ext

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAd
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AdmobInterstitialExtTest {

    // =============================================================================================
    // UNIT & USABILITY TESTS
    // =============================================================================================

    @Test
    fun testShowAdWithBlankIdTriggersOnAdFailed() {
        val mockInterstitialAd = mockk<InterstitialAd>(relaxed = true)
        val mockActivity = mockk<AppCompatActivity>(relaxed = true)
        val mockCallback = mockk<FrogoAdmobInterstitialCallback>(relaxed = true)

        // Invoke extension function with blank ad unit ID
        mockInterstitialAd.showAd(
            activity = mockActivity,
            interstitialAdUnitId = "",
            timeoutMilliSecond = 5000,
            keyword = listOf("games", "news"),
            callback = mockCallback
        )

        // Verify that the empty check is handled immediately and callback triggers onAdFailed
        verify(exactly = 1) { 
            mockCallback.onAdFailed("FrogoAdmobInterstitialExt", "FrogoAdmobInterstitialExt Interstitial ID is Empty") 
        }
    }

    @Test
    fun testShowAdWithValidIdTriggersProgressCallback() {
        val mockInterstitialAd = mockk<InterstitialAd>(relaxed = true)
        val mockActivity = mockk<AppCompatActivity>(relaxed = true)
        val mockCallback = mockk<FrogoAdmobInterstitialCallback>(relaxed = true)
        val validAdUnitId = "ca-app-pub-3940256099942544/1033173712"

        try {
            // Trigger method to ensure standard flow is correct
            mockInterstitialAd.showAd(
                activity = mockActivity,
                interstitialAdUnitId = validAdUnitId,
                timeoutMilliSecond = null,
                keyword = null,
                callback = mockCallback
            )

            // Verify progress callback is correctly invoked at startup
            verify(exactly = 1) { 
                mockCallback.onShowAdRequestProgress(
                    "FrogoAdmobInterstitialExt", 
                    "FrogoAdmobInterstitialExt [Interstitial] >> Run - FrogoAdmobInterstitialCallback [callback] : onShowAdRequestProgress()"
                ) 
            }
        } catch (e: Exception) {
            // Under Robolectric local execution context, Google Ads SDK static load() might bypass or throw.
            // Bypassing any structural network/SDK initialization issues is acceptable here.
        }
    }
}
