package com.frogobox.sdk.piracychecker.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.frogobox.sdk.piracychecker.enums.InstallerID
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LibraryUtilsSecurityTest {

    @Test
    fun testVerifyInstallerIdFailsWhenNoInstallerSet() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // Without setting any installer, verifyInstallerId should return false
        // because the installer package name will be null.
        val isVerified = context.verifyInstallerId(listOf(InstallerID.GOOGLE_PLAY))
        assertFalse("Should fail to verify when no installer is set", isVerified)
    }

    @Test
    fun testVerifyInstallerIdFailsForEmptyList() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // An empty list of valid installer IDs should fail
        val isVerified = context.verifyInstallerId(emptyList())
        assertFalse("Should fail when no valid installer IDs are provided", isVerified)
    }

    @Test
    fun testIsInEmulatorDefaultChecks() {
        // Verify that the emulator check does not throw exceptions
        // and evaluates to a boolean on Robolectric's JVM environment.
        val isEmulator = isInEmulator(deepCheck = false)
        // Robolectric sets Build fields to generic/sdk values, so this likely returns true.
        println("isInEmulator(deepCheck=false) = $isEmulator")
    }

    @Test
    fun testIsInEmulatorDeepCheckDoesNotCrash() {
        // Deep check accesses GLES20 and file system paths.
        // Ensure it does not crash on JVM/Robolectric.
        val isEmulator = isInEmulator(deepCheck = true)
        println("isInEmulator(deepCheck=true) = $isEmulator")
    }

    @Test
    fun testVerifySigningCertificatesWithWrongSignature() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // Provide a fake signature that will never match the test app's actual signature.
        val fakeSignatures = arrayOf("FAKE_SIGNATURE_BASE64==")
        val isVerified = context.verifySigningCertificates(fakeSignatures)
        assertFalse("Should fail to verify with a fake/wrong APK signature", isVerified)
    }

    @Test
    fun testGetPirateAppReturnsNullWhenNoPirateAppsInstalled() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // In a clean Robolectric environment, no pirate apps are installed.
        val pirateApp = context.getPirateApp(
            lpf = true,
            stores = true,
            folders = false,
            apks = false,
            extraApps = arrayListOf()
        )
        assertTrue("Should not detect any pirate app in a clean environment", pirateApp == null)
    }
}
