package com.frogobox.ads.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class FrogoAdConstantTest {

    // =============================================================================================
    // UNIT TESTS
    // =============================================================================================

    @Test
    fun testConstantsValues() {
        assertEquals("https://raw.githubusercontent.com/", FrogoAdConstant.BASE_URL_SERVER)
        assertEquals("https://github.com/amirisback", FrogoAdConstant.GITHUB_ACCOUNT)
        assertEquals("com.google.android.libraries.ads.mobile.sdk.MobileAds", FrogoAdConstant.ADMOB_MOBILE_ADS_KEY)
    }

    // =============================================================================================
    // USABILITY TESTS
    // =============================================================================================

    @Test
    fun testConstantsUsabilityNamingConvention() {
        // Assert that the divider line is not empty and has descriptive length
        assertNotNull(FrogoAdConstant.LINE)
        assertTrue(FrogoAdConstant.LINE.length > 50)

        // Naming pattern check for usability and maintainability
        assertTrue(FrogoAdConstant.BASE_URL_SERVER.startsWith("https://"))
        assertTrue(FrogoAdConstant.GITHUB_ACCOUNT.startsWith("https://"))
    }
}
