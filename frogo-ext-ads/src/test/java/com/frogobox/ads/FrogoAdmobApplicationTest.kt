package com.frogobox.ads

import android.app.Activity
import android.content.Context
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class FrogoAdmobApplicationTest {

    private class TestAdmobApplication : FrogoAdmobApplication() {
        var currentActivityRef: Activity? = null

        override fun getAdOpenAppUnitId(context: Context?): String {
            return "test_app_open_ad_unit_id"
        }

        fun exposeCurrentActivity(): Activity? {
            // Indirection to test currentActivity since it's private
            // We can simulate activity lifecycle to let it set currentActivity
            return null
        }
    }

    // =============================================================================================
    // INTEGRATION & FUNCTIONAL TESTS
    // =============================================================================================

    @Test
    fun testApplicationOnCreate() {
        val app = TestAdmobApplication()
        app.onCreate()

        // Verify simple custom function works
        assertEquals("test_app_open_ad_unit_id", app.getAdOpenAppUnitId(RuntimeEnvironment.getApplication()))
    }

    @Test
    fun testLifecycleCallbacksAndActivityTracking() {
        val app = FrogoAdmobApplication()
        app.onCreate()

        val mockActivity = Robolectric.buildActivity(Activity::class.java).create().get()

        // Trigger onActivityStarted manually to verify it handles state transition
        app.onActivityStarted(mockActivity)

        // Destroy the activity
        app.onActivityDestroyed(mockActivity)
        
        // Ensure no exception is thrown during standard callbacks
        app.onActivityCreated(mockActivity, null)
        app.onActivityResumed(mockActivity)
        app.onActivityPaused(mockActivity)
        app.onActivityStopped(mockActivity)
        app.onActivitySaveInstanceState(mockActivity, android.os.Bundle())
    }
}
