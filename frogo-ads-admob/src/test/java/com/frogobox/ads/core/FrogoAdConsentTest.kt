package com.frogobox.ads.core

import android.app.Activity
import android.content.Context
import android.telephony.TelephonyManager
import com.google.android.ump.FormError
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FrogoAdConsentTest {

    private class TestAdConsent(
        private val activity: Activity,
        private val isUnderAge: Boolean,
        private val isDebug: Boolean
    ) : IFrogoAdConsent {
        var onConsentSuccessCalled = false
        var onConsentErrorCalled = false
        var onNotUsingAdConsentCalled = false

        override fun activity(): Activity = activity
        override fun isUnderAgeAd(): Boolean = isUnderAge
        override fun isDebug(): Boolean = isDebug

        override fun onConsentSuccess() {
            onConsentSuccessCalled = true
        }

        override fun onConsentError(formError: FormError) {
            onConsentErrorCalled = true
        }

        override fun onNotUsingAdConsent() {
            onNotUsingAdConsentCalled = true
        }
    }

    // =============================================================================================
    // UNIT & USABILITY TESTS
    // =============================================================================================

    @Test
    fun testCountryLists() {
        val eeaCountries = FrogoAdConsent.listEEACountry()
        val ukCountries = FrogoAdConsent.listUKCountry()
        val allCountries = FrogoAdConsent.listAdConsentCountry()

        // Verify counts
        assertTrue(eeaCountries.size > 20)
        assertEquals(4, ukCountries.size)
        assertEquals(eeaCountries.size + ukCountries.size, allCountries.size)

        // Verify standard ISO 2-letter uppercase format
        eeaCountries.forEach { code ->
            assertEquals(2, code.length)
            assertEquals(code.uppercase(), code)
        }

        ukCountries.forEach { code ->
            assertEquals(2, code.length)
            assertEquals(code.uppercase(), code)
        }
    }

    @Test
    fun testIsAdConsentCountryLogic() {
        val mockContext = mockk<Context>()
        val mockTelephonyManager = mockk<TelephonyManager>()

        every { mockContext.getSystemService(Context.TELEPHONY_SERVICE) } returns mockTelephonyManager

        // Test non-EEA country (e.g. Indonesia ID)
        every { mockTelephonyManager.networkCountryIso } returns "id"
        assertEquals("ID", FrogoAdConsent.getCountryCode(mockContext))
        assertFalse(FrogoAdConsent.isAdConsentCountry(mockContext))

        // Test EEA country (e.g. Germany DE)
        every { mockTelephonyManager.networkCountryIso } returns "de"
        assertEquals("DE", FrogoAdConsent.getCountryCode(mockContext))
        assertTrue(FrogoAdConsent.isAdConsentCountry(mockContext))

        // Test UK country (e.g. United Kingdom GB)
        every { mockTelephonyManager.networkCountryIso } returns "gb"
        assertEquals("GB", FrogoAdConsent.getCountryCode(mockContext))
        assertTrue(FrogoAdConsent.isAdConsentCountry(mockContext))
    }

    @Test
    fun testCountryCodeFallbackWhenTelephonyEmpty() {
        val mockContext = mockk<Context>(relaxed = true)
        val mockTelephonyManager = mockk<TelephonyManager>(relaxed = true)
        val previousLocale = java.util.Locale.getDefault()
        try {
            java.util.Locale.setDefault(java.util.Locale.FRANCE)
            every { mockContext.getSystemService(Context.TELEPHONY_SERVICE) } returns mockTelephonyManager
            every { mockTelephonyManager.networkCountryIso } returns ""
            every { mockTelephonyManager.simCountryIso } returns ""

            assertEquals("FR", FrogoAdConsent.getCountryCode(mockContext))
            assertTrue(FrogoAdConsent.isAdConsentCountry(mockContext))
        } finally {
            java.util.Locale.setDefault(previousLocale)
        }
    }

    // =============================================================================================
    // SECURITY TESTS
    // =============================================================================================

    @Test
    fun testConsentShowOutsideConsentCountries() {
        // Outside consent countries (e.g., US or ID), consent flows should bypass smoothly 
        // to avoid locking the application lifecycle or requesting unnecessary consent
        val mockContext = mockk<Context>()
        val mockTelephonyManager = mockk<TelephonyManager>()
        val mockActivity = mockk<Activity>()

        every { mockContext.getSystemService(Context.TELEPHONY_SERVICE) } returns mockTelephonyManager
        every { mockTelephonyManager.networkCountryIso } returns "us"
        
        // Mock activity context lookup
        every { mockActivity.getSystemService(Context.TELEPHONY_SERVICE) } returns mockTelephonyManager

        val consentCallback = TestAdConsent(mockActivity, isUnderAge = false, isDebug = false)

        FrogoAdConsent.showConsent(consentCallback)

        // Ensure "onNotUsingAdConsent" is safely triggered as expected, avoiding deadlocks or permission leakages
        assertTrue(consentCallback.onNotUsingAdConsentCalled)
        assertFalse(consentCallback.onConsentSuccessCalled)
        assertFalse(consentCallback.onConsentErrorCalled)
    }

    @Test
    fun testCanRequestAdsAndPrivacyOptionsRequiredWithMockedUmp() {
        io.mockk.mockkStatic(com.google.android.ump.UserMessagingPlatform::class)
        try {
            val mockContext = mockk<Context>(relaxed = true)
            val mockConsentInfo = mockk<com.google.android.ump.ConsentInformation>()

            every { com.google.android.ump.UserMessagingPlatform.getConsentInformation(mockContext) } returns mockConsentInfo
            every { mockConsentInfo.canRequestAds() } returns true
            every { mockConsentInfo.privacyOptionsRequirementStatus } returns
                com.google.android.ump.ConsentInformation.PrivacyOptionsRequirementStatus.REQUIRED

            assertTrue(FrogoAdConsent.canRequestAds(mockContext))
            assertTrue(FrogoAdConsent.isPrivacyOptionsRequired(mockContext))

            every { mockConsentInfo.canRequestAds() } returns false
            every { mockConsentInfo.privacyOptionsRequirementStatus } returns
                com.google.android.ump.ConsentInformation.PrivacyOptionsRequirementStatus.NOT_REQUIRED

            assertFalse(FrogoAdConsent.canRequestAds(mockContext))
            assertFalse(FrogoAdConsent.isPrivacyOptionsRequired(mockContext))
        } finally {
            io.mockk.unmockkStatic(com.google.android.ump.UserMessagingPlatform::class)
        }
    }

    @Test
    fun testEmptyConfigLocalesDoesNotThrowIndexOutOfBoundsException() {
        val mockContext = mockk<Context>(relaxed = true)
        val mockResources = mockk<android.content.res.Resources>(relaxed = true)
        val config = android.content.res.Configuration()
        // Empty locales
        every { mockContext.resources } returns mockResources
        every { mockResources.configuration } returns config

        // Must return default locale or empty without crashing with IndexOutOfBoundsException
        val country = FrogoAdConsent.getCountryCode(mockContext)
        assertTrue(country.isNotEmpty())
    }

    @Test
    fun testGatherConsentOnFinishingActivityAbortsGracefully() {
        val mockActivity = mockk<Activity>()
        every { mockActivity.isFinishing } returns true
        every { mockActivity.isDestroyed } returns false

        var callbackCalled = false
        FrogoAdConsent.gatherConsent(mockActivity) { error ->
            callbackCalled = true
            org.junit.Assert.assertNull(error)
        }
        assertTrue(callbackCalled)
    }

    @Test
    fun testShowPrivacyOptionsOnFinishingActivityAbortsGracefully() {
        val mockActivity = mockk<Activity>()
        every { mockActivity.isFinishing } returns false
        every { mockActivity.isDestroyed } returns true

        var callbackCalled = false
        FrogoAdConsent.showPrivacyOptionsForm(mockActivity) { error ->
            callbackCalled = true
            org.junit.Assert.assertNull(error)
        }
        assertTrue(callbackCalled)
    }
}
