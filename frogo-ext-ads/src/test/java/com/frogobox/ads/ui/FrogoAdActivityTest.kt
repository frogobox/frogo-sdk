package com.frogobox.ads.ui

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import com.frogobox.ads.ext.showHybridInterstitial

@RunWith(RobolectricTestRunner::class)
class FrogoAdActivityTest {

    // Concrete implementations of abstract activities for testing
    class TestAdmobActivity : FrogoAdmobActivity()
    class TestUnityAdActivity : FrogoUnityAdActivity()
    class TestConcreteAdActivity : AdActivity()
    class TestFrogoAdActivity : FrogoAdActivity()
    class TestConcreteAdComposeActivity : com.frogobox.ads.ui.compose.AdComposeActivity() {
        @androidx.compose.runtime.Composable
        override fun SetupCompose() {}
    }

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

    @Test
    fun testAdActivityDelegatesInitialization() {
        val controller = Robolectric.buildActivity(TestConcreteAdActivity::class.java).setup()
        val activity = controller.get()
        assertNotNull(activity)
        // Verify Unity and Frogo delegates are properly initialized without throwing UninitializedPropertyAccessException
        val callback = io.mockk.mockk<com.frogobox.ads.callback.FrogoAdInterstitialCallback>(relaxed = true)
        activity.showAdmobXUnityAdInterstitial("", "", callback)
        io.mockk.verify(exactly = 1) { callback.onAdFailed(any(), any()) }
    }

    @Test
    fun testAdComposeActivityDelegatesInitialization() {
        val controller = Robolectric.buildActivity(TestConcreteAdComposeActivity::class.java).setup()
        val activity = controller.get()
        assertNotNull(activity)
        val callback = io.mockk.mockk<com.frogobox.ads.callback.FrogoAdInterstitialCallback>(relaxed = true)
        activity.showAdmobXUnityAdInterstitial("", "", callback)
        io.mockk.verify(exactly = 1) { callback.onAdFailed(any(), any()) }
    }

    @Test
    fun testFrogoAdActivityLaunch() {
        val controller = Robolectric.buildActivity(TestFrogoAdActivity::class.java).setup()
        val activity = controller.get()
        assertNotNull(activity)
    }

    @Test
    fun testShowHybridInterstitialExtension() {
        val controller = Robolectric.buildActivity(androidx.appcompat.app.AppCompatActivity::class.java).setup()
        val activity = controller.get()
        assertNotNull(activity)
        val callback = io.mockk.mockk<com.frogobox.ads.callback.FrogoAdInterstitialCallback>(relaxed = true)
        activity.showHybridInterstitial(
            admobId = "",
            unityId = "",
            prioritizeAdmob = true,
            callback = callback
        )
        io.mockk.verify(exactly = 1) { callback.onAdFailed(any(), any()) }
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
