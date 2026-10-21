package com.frogobox.sdk.ext

import android.app.Application
import android.os.Looper
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class FrogoWebViewExtTest {

    @Test
    fun testPrewarmWebView_executesSafely() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        prewarmWebView(app)

        // Drain the main looper idle handlers
        shadowOf(Looper.getMainLooper()).idle()
        assertNotNull(app)
    }

    @Test
    fun testPrewarmWebViewExt_extensionExecutesSafely() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        app.prewarmWebViewExt()

        shadowOf(Looper.getMainLooper()).idle()
        assertNotNull(app)
    }
}
