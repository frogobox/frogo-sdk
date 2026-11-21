package com.frogobox.app

import android.content.Context
import android.os.Build
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.frogobox.MainActivity
import com.frogobox.appcompose.MainComposeActivity
import com.frogobox.appcomposeui.MainComposeUiActivity
import com.frogobox.appsdk.log.LogActivity
import com.frogobox.appsdk.main.MainSDKActivity
import com.frogobox.appuikit.MainUIActivity
import com.frogobox.sdk.ext.toJson
import com.frogobox.sdk.ext.toModel
import com.frogobox.sdk.util.FrogoFunc
import com.frogobox.sdk.util.FrogoMusic
import com.frogobox.sdk.util.FrogoValidator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import java.io.File

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
    fun testMainActivityUiNavigationFlow() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val btnUi = activity.findViewById<android.view.View>(com.frogobox.R.id.btn_ui)
                assertNotNull("btn_ui should exist", btnUi)

                btnUi.performClick()

                val nextStartedActivity = shadowOf(activity).nextStartedActivity
                assertNotNull("Expected intent for MainUIActivity", nextStartedActivity)
                assertEquals(
                    MainUIActivity::class.java.name,
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
    fun testMainSDKActivityLaunchAndNavigationFlow() {
        ActivityScenario.launch(MainSDKActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                assertNotNull("MainSDKActivity should be created", activity)

                val btnMenuLog = activity.findViewById<android.view.View>(com.frogobox.R.id.btn_menu_log)
                assertNotNull("btn_menu_log should exist", btnMenuLog)

                btnMenuLog.performClick()

                val nextStartedActivity = shadowOf(activity).nextStartedActivity
                assertNotNull("Expected intent for LogActivity", nextStartedActivity)
                assertEquals(
                    LogActivity::class.java.name,
                    nextStartedActivity.component?.className
                )
            }
        }
    }

    @Test
    fun testMainComposeActivityLaunch() {
        ActivityScenario.launch(MainComposeActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                assertNotNull("MainComposeActivity should be created", activity)
                assertFalse("MainComposeActivity should not be finishing", activity.isFinishing)
            }
        }
    }

    @Test
    fun testMainUIActivityLaunch() {
        ActivityScenario.launch(MainUIActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                assertNotNull("MainUIActivity should be created", activity)
                assertFalse("MainUIActivity should not be finishing", activity.isFinishing)
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
    fun testFrogoMusicSafeLifecycle() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val music = FrogoMusic(context, 0)
        // Safe operations without crash even when resource ID is invalid (unsupported)
        music.playMusic()
        music.pauseMusic()
        music.stopMusic()
    }

    @Test
    fun testFrogoFuncAppSpecificStorageE2E() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        FrogoFunc.createAppSpecificFolderPictureVideo(context)
        val filePath = FrogoFunc.getAppSpecificVideoFilePath(context)
        assertNotNull(filePath)
        assertTrue("Path should be inside app-specific files directory", filePath.contains(context.packageName))
        assertTrue("Generated file path should have .mp4 extension", filePath.endsWith(".mp4"))
    }

    @Test
    fun testFrogoValidatorE2E() {
        assertTrue(FrogoValidator.isValidText("Frogo", 3))
        assertFalse(FrogoValidator.isValidText("  ", 2))
        assertTrue(FrogoValidator.isValidEmail("dev@frogobox.com"))
        assertFalse(FrogoValidator.isValidEmail("invalid"))
        assertTrue(FrogoValidator.isValidPassword("StrongPass1!"))
        assertFalse(FrogoValidator.isValidPassword("short"))
    }

}
