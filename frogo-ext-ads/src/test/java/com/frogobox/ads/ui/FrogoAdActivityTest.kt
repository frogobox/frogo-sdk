package com.frogobox.ads.ui

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class FrogoAdActivityTest {

    // Concrete implementations of abstract activities for testing
    class TestAdmobActivity : FrogoAdmobActivity()
    class TestUnityAdActivity : FrogoUnityAdActivity()

    // =============================================================================================
    // UI, INTEGRATION & FUNCTIONAL TESTS
    // =============================================================================================

    @Test
    fun testAdmobActivityLaunch() {
        val controller = Robolectric.buildActivity(TestAdmobActivity::class.java).setup()
        val activity = controller.get()
        assertNotNull(activity)
    }

    @Test
    fun testUnityAdActivityLaunch() {
        val controller = Robolectric.buildActivity(TestUnityAdActivity::class.java).setup()
        val activity = controller.get()
        assertNotNull(activity)
    }

    // =============================================================================================
    // COMPATIBILITY TESTS (Running on multiple API levels)
    // =============================================================================================

    @Test
    @Config(sdk = [24])
    fun testActivityCompatibility_API24() {
        val admobController = Robolectric.buildActivity(TestAdmobActivity::class.java).setup()
        assertNotNull(admobController.get())
        
        val unityController = Robolectric.buildActivity(TestUnityAdActivity::class.java).setup()
        assertNotNull(unityController.get())
    }

    @Test
    @Config(sdk = [28])
    fun testActivityCompatibility_API28() {
        val admobController = Robolectric.buildActivity(TestAdmobActivity::class.java).setup()
        assertNotNull(admobController.get())
        
        val unityController = Robolectric.buildActivity(TestUnityAdActivity::class.java).setup()
        assertNotNull(unityController.get())
    }

    @Test
    @Config(sdk = [33])
    fun testActivityCompatibility_API33() {
        val admobController = Robolectric.buildActivity(TestAdmobActivity::class.java).setup()
        assertNotNull(admobController.get())
        
        val unityController = Robolectric.buildActivity(TestUnityAdActivity::class.java).setup()
        assertNotNull(unityController.get())
    }

    @Test
    @Config(sdk = [34])
    fun testActivityCompatibility_API34() {
        val admobController = Robolectric.buildActivity(TestAdmobActivity::class.java).setup()
        assertNotNull(admobController.get())
        
        val unityController = Robolectric.buildActivity(TestUnityAdActivity::class.java).setup()
        assertNotNull(unityController.get())
    }
}
