package com.frogobox.sdk

import android.content.Context
import android.net.ConnectivityManager
import androidx.test.core.app.ApplicationProvider
import com.frogobox.sdk.util.FrogoFunc
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
class CompatibilityTest {

    private fun setNetworkDisconnected(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val shadowConnectivityManager = shadowOf(connectivityManager)
        shadowConnectivityManager.setActiveNetworkInfo(null)
    }

    @Test
    @Config(sdk = [24]) // Compatibility check on API 24 (Android N - uses NetworkCapabilities branch)
    fun testIsNetworkConnected_API24_Disconnected() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        setNetworkDisconnected(context)

        // On API 24+ with no active network set, should return false
        val isConnected = FrogoFunc.isNetworkConnected(context)
        assertFalse("Should return false when no active network on API 24", isConnected)
    }

    @Test
    @Config(sdk = [28]) // Compatibility check on API 28 (Android Pie)
    fun testIsNetworkConnected_API28_Disconnected() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        setNetworkDisconnected(context)

        val isConnected = FrogoFunc.isNetworkConnected(context)
        assertFalse("Should return false when no active network on API 28", isConnected)
    }

    @Test
    @Config(sdk = [33]) // Compatibility check on API 33 (Android Tiramisu)
    fun testIsNetworkConnected_API33_Disconnected() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        setNetworkDisconnected(context)

        val isConnected = FrogoFunc.isNetworkConnected(context)
        assertFalse("Should return false when no active network on API 33", isConnected)
    }

    @Test
    @Config(sdk = [34]) // Compatibility check on API 34 (Android UpsideDownCake)
    fun testIsNetworkConnected_API34_Disconnected() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        setNetworkDisconnected(context)

        val isConnected = FrogoFunc.isNetworkConnected(context)
        assertFalse("Should return false when no active network on API 34", isConnected)
    }

    @Test
    fun testRandomNumberUtility() {
        // Simple range test for utility function across default SDK
        val start = 10
        val end = 20
        val randomNum = FrogoFunc.randomNumber(start, end)
        assertTrue("Random number $randomNum should be in range [$start, $end]", randomNum in start..end)
    }
}
