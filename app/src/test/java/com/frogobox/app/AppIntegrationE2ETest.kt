package com.frogobox.app

import android.content.Context
import android.os.Build
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.frogobox.MainActivity
import com.frogobox.ads.core.FrogoAdmob
import com.frogobox.appcompose.MainComposeActivity
import com.frogobox.appcomposeui.MainComposeUiActivity
import com.frogobox.appsdk.main.MainSDKActivity
import com.frogobox.sdk.ext.toJson
import com.frogobox.sdk.ext.toModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.TIRAMISU])
class AppIntegrationE2ETest {

    @Test
    fun testMainActivityLaunchAndNavigationFlow() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                assertNotNull("MainActivity should be created", activity)

                val btnSdk = activity.findViewById<android.view.View>(com.frogobox.R.id.btn_sdk)
                assertNotNull("btn_sdk should exist", btnSdk)

                // Trigger click to navigate to MainSDKActivity
                btnSdk.performClick()

                val nextStartedActivity = shadowOf(activity).nextStartedActivity
                assertNotNull("Expected intent for MainSDKActivity", nextStartedActivity)
                assertEquals(
                    MainSDKActivity::class.java.name,
                    nextStartedActivity.component?.className
                )
            }
        }
    }

    @Test
    fun testMainActivityComposeNavigationFlow() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val btnCompose = activity.findViewById<android.view.View>(com.frogobox.R.id.btn_compose)
                assertNotNull("btn_compose should exist", btnCompose)

                btnCompose.performClick()

                val nextStartedActivity = shadowOf(activity).nextStartedActivity
                assertNotNull("Expected intent for MainComposeActivity", nextStartedActivity)
                assertEquals(
                    MainComposeActivity::class.java.name,
                    nextStartedActivity.component?.className
                )
            }
        }
    }

    @Test
    fun testMainActivityComposeUiNavigationFlow() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val btnComposeUi = activity.findViewById<android.view.View>(com.frogobox.R.id.btn_compose_ui)
                assertNotNull("btn_compose_ui should exist", btnComposeUi)

                btnComposeUi.performClick()

                val nextStartedActivity = shadowOf(activity).nextStartedActivity
                assertNotNull("Expected intent for MainComposeUiActivity", nextStartedActivity)
                assertEquals(
                    MainComposeUiActivity::class.java.name,
                    nextStartedActivity.component?.className
                )
            }
        }
    }

    @Test
    fun testSerializationEndToEnd() {
        data class SamplePayload(val id: Int, val name: String, val active: Boolean)

        val original = SamplePayload(id = 101, name = "FrogoSDK E2E", active = true)
        val json = original.toJson()
        assertTrue("JSON should contain key name", json.contains("FrogoSDK E2E"))

        val deserialized = json.toModel<SamplePayload>()
        assertEquals(original, deserialized)
    }

    @Test
    fun testFrogoAdmobManagerResilienceWithoutGms() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Verify that setupAdmobApp handles missing Google Play Services safely without uncaught exceptions
        FrogoAdmob.setupAdmobApp(context)
        // If execution reaches here without crashing, the isolation and safety fix is effective
        assertTrue(true)
    }
}
