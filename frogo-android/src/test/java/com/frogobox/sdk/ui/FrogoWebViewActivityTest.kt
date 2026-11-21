package com.frogobox.sdk.ui

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class FrogoWebViewActivityTest {

    @Test
    fun testActivityLaunchesWithCorrectExtras() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val url = "https://www.frogobox.com"
        val title = "Frogobox Website"

        // Creating intent with extras
        val intent = Intent(context, FrogoWebViewActivity::class.java).apply {
            putExtra(FrogoWebViewActivity.EXTRA_URL, url)
            putExtra(FrogoWebViewActivity.EXTRA_TITLE, title)
        }

        // Launch the activity using ActivityScenario
        ActivityScenario.launch<FrogoWebViewActivity>(intent).use { scenario ->
            scenario.onActivity { activity ->
                assertNotNull(activity)

                // Assert that UI widgets can be referenced using correct snake_case IDs
                assertNotNull(activity.findViewById(com.frogobox.sdk.R.id.web_view))
                assertNotNull(activity.findViewById(com.frogobox.sdk.R.id.progress_bar))
            }
        }
    }

    @Test
    fun testStartActivityExtHelper() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val url = "https://github.com/amirisback"
        val title = "Developer Github"

        // Call the static helper function
        val application = ApplicationProvider.getApplicationContext<android.app.Application>()

        FrogoWebViewActivity.startActivityExt(application, url, title)

        val expectedIntent = shadowOf(application).nextStartedActivity
        assertNotNull("Intent should not be null", expectedIntent)
        assertEquals(FrogoWebViewActivity::class.java.name, expectedIntent.component?.className)
        assertEquals(url, expectedIntent.getStringExtra(FrogoWebViewActivity.EXTRA_URL))
        assertEquals(title, expectedIntent.getStringExtra(FrogoWebViewActivity.EXTRA_TITLE))
    }
}
