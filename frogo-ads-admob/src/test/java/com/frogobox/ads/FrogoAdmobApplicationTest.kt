package com.frogobox.ads

import android.app.Activity
import android.content.Context
import com.frogobox.ads.core.IFrogoAppOpenAdOptOut
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class FrogoAdmobApplicationTest {

    private class TestAdmobApplication : FrogoAdmobApplication() {
        var currentActivityRef: Activity? = null
        var customAppOpenEnabled: Boolean = true
        var customAutoShowEnabled: Boolean = true

        override fun getAdOpenAppUnitId(context: Context?): String {
            return "test_app_open_ad_unit_id"
        }

        override fun isAppOpenAdEnabled(): Boolean = customAppOpenEnabled
        override fun isAutoShowAppOpenAdOnForegroundEnabled(): Boolean = customAutoShowEnabled

        fun exposeCurrentActivity(): Activity? = getCurrentActivity()
    }

    private class OptOutActivity : Activity(), IFrogoAppOpenAdOptOut

    private class DynamicOptOutActivity(var optOut: Boolean) : Activity(), IFrogoAppOpenAdOptOut {
        override fun isAppOpenAdOptedOut(): Boolean = optOut
    }

    // =============================================================================================
    // INTEGRATION & FUNCTIONAL TESTS
    // =============================================================================================

    @Test
    fun testApplicationOnCreate() {
        val app = TestAdmobApplication()
        app.onCreate()

        assertEquals("test_app_open_ad_unit_id", app.getAdOpenAppUnitId(RuntimeEnvironment.getApplication()))
        assertNotNull(app.getAppOpenAdManager())
        assertFalse(app.isAppOpenAdAvailable())
    }

    @Test
    fun testPrewarmWebView_executesSafely() {
        val app = FrogoAdmobApplication()
        app.onCreate()
        org.robolectric.Shadows.shadowOf(android.os.Looper.getMainLooper()).idle()
    }

    @Test
    fun testLifecycleCallbacksAndActivityTracking() {
        val app = TestAdmobApplication()
        app.onCreate()

        val mockActivity = Robolectric.buildActivity(Activity::class.java).create().get()

        // Trigger onActivityStarted manually to verify it handles state transition
        app.onActivityStarted(mockActivity)
        assertEquals(mockActivity, app.exposeCurrentActivity())

        // Destroy the activity
        app.onActivityDestroyed(mockActivity)
        assertEquals(null, app.exposeCurrentActivity())

        // Ensure no exception is thrown during standard callbacks
        app.onActivityCreated(mockActivity, null)
        app.onActivityResumed(mockActivity)
        app.onActivityPaused(mockActivity)
        app.onActivityStopped(mockActivity)
        app.onActivitySaveInstanceState(mockActivity, android.os.Bundle())
    }

    @Test
    fun testShouldShowAppOpenAd_normalActivityReturnsTrue() {
        val app = TestAdmobApplication()
        app.onCreate()

        val controller = Robolectric.buildActivity(Activity::class.java).setup()
        val activity = controller.get()

        assertTrue(app.shouldShowAppOpenAd(activity))
    }

    @Test
    fun testShouldShowAppOpenAd_optOutActivityReturnsFalse() {
        val app = TestAdmobApplication()
        app.onCreate()

        val controller = Robolectric.buildActivity(OptOutActivity::class.java).setup()
        val activity = controller.get()

        assertFalse(app.shouldShowAppOpenAd(activity))
    }

    @Test
    fun testShouldShowAppOpenAd_dynamicOptOutActivity() {
        val app = TestAdmobApplication()
        app.onCreate()

        val activeOptOut = DynamicOptOutActivity(optOut = true)
        assertFalse(app.shouldShowAppOpenAd(activeOptOut))

        val disabledOptOut = DynamicOptOutActivity(optOut = false)
        assertTrue(app.shouldShowAppOpenAd(disabledOptOut))
    }

    @Test
    fun testShouldShowAppOpenAd_disabledGlobalToggleReturnsFalse() {
        val app = TestAdmobApplication()
        app.onCreate()
        app.customAppOpenEnabled = false

        val controller = Robolectric.buildActivity(Activity::class.java).setup()
        val activity = controller.get()

        assertFalse(app.shouldShowAppOpenAd(activity))
    }

    @Test
    fun testShouldShowAppOpenAd_disabledAutoShowReturnsFalse() {
        val app = TestAdmobApplication()
        app.onCreate()
        app.customAutoShowEnabled = false

        val controller = Robolectric.buildActivity(Activity::class.java).setup()
        val activity = controller.get()

        assertFalse(app.shouldShowAppOpenAd(activity))
    }

    @Test
    fun testShouldShowAppOpenAd_finishingOrDestroyedActivityReturnsFalse() {
        val app = TestAdmobApplication()
        app.onCreate()

        val controller = Robolectric.buildActivity(Activity::class.java).setup()
        val activity = controller.get()

        activity.finish()
        assertFalse(app.shouldShowAppOpenAd(activity))

        val controller2 = Robolectric.buildActivity(Activity::class.java).setup()
        val activity2 = controller2.get()
        controller2.destroy()
        assertFalse(app.shouldShowAppOpenAd(activity2))
    }

    @Test
    fun testClearAppOpenAd_delegatesToManager() {
        val app = TestAdmobApplication()
        app.onCreate()

        app.clearAppOpenAd()
        assertFalse(app.isAppOpenAdAvailable())
    }
}
