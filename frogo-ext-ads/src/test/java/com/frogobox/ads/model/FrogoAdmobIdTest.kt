package com.frogobox.ads.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class FrogoAdmobIdTest {

    // =============================================================================================
    // UNIT TESTS
    // =============================================================================================

    @Test
    fun testAdmobIdDefaultValues() {
        val admobId = FrogoAdmobId()

        // Check Admob standard test IDs
        assertEquals("ca-app-pub-3940256099942544~3347511713", admobId.testAdmobAppId)
        assertEquals("ca-app-pub-3940256099942544/6300978111", admobId.testAdmobBanner)
        assertEquals("ca-app-pub-3940256099942544/1033173712", admobId.testAdmobInterstitial)
        assertEquals("ca-app-pub-3940256099942544/5224354917", admobId.testAdmobRewarded)

        // Check production IDs defaults
        assertEquals("", admobId.admobAppId)
        assertNotNull(admobId.admobBannerID)
        assertEquals(3, admobId.admobBannerID.size)
        assertEquals(3, admobId.admobInterstitialID.size)
        assertEquals(3, admobId.admobRewardedID.size)
    }

    @Test
    fun testAdmobIdCustomInitialization() {
        val customBannerList = listOf("banner_1", "banner_2")
        val customInterstitialList = listOf("interstitial_1")
        val customRewardedList = listOf("rewarded_1", "rewarded_2", "rewarded_3")

        val admobId = FrogoAdmobId(
            testAdmobAppId = "custom_app_id",
            testAdmobBanner = "custom_banner",
            testAdmobInterstitial = "custom_interstitial",
            testAdmobRewarded = "custom_rewarded",
            admobAppId = "prod_app_id",
            admobBannerID = customBannerList,
            admobInterstitialID = customInterstitialList,
            admobRewardedID = customRewardedList
        )

        assertEquals("custom_app_id", admobId.testAdmobAppId)
        assertEquals("custom_banner", admobId.testAdmobBanner)
        assertEquals("custom_interstitial", admobId.testAdmobInterstitial)
        assertEquals("custom_rewarded", admobId.testAdmobRewarded)
        assertEquals("prod_app_id", admobId.admobAppId)
        assertEquals(customBannerList, admobId.admobBannerID)
        assertEquals(customInterstitialList, admobId.admobInterstitialID)
        assertEquals(customRewardedList, admobId.admobRewardedID)
    }
}
