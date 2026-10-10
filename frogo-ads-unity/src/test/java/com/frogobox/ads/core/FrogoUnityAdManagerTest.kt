package com.frogobox.ads.core

import androidx.appcompat.app.AppCompatActivity
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FrogoUnityAdManagerTest {

    @Test
    fun testLifecycleAutoCleanup() {
        val controller = Robolectric.buildActivity(AppCompatActivity::class.java).setup()
        val activity = controller.get()
        val manager = FrogoUnityAdManager(activity)

        assertNotNull(manager)
        controller.destroy()
        manager.clear()
        assertNotNull(manager)
    }
}
