package com.frogobox.compose.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class FrogoComponentActivityTest {

    class ConcreteComponentActivity : FrogoComponentActivity() {
        var setupViewModelCalled = false
        var setupDelegatesCalled = false
        var setupComposeCalled = false
        val callOrder = ArrayList<String>()

        override fun setupViewModel() {
            super.setupViewModel()
            setupViewModelCalled = true
            callOrder.add("setupViewModel")
        }

        override fun setupDelegates() {
            super.setupDelegates()
            setupDelegatesCalled = true
            callOrder.add("setupDelegates")
        }

        @Composable
        override fun SetupCompose() {
            setupComposeCalled = true
            callOrder.add("SetupCompose")
            Text(text = "Hello ComponentActivity")
        }
    }

    @Test
    fun testActivityLaunchAndLifecycleOrder() {
        val controller = Robolectric.buildActivity(ConcreteComponentActivity::class.java).setup()
        val activity = controller.get()

        assertNotNull(activity)
        assertTrue(activity.setupViewModelCalled)
        assertTrue(activity.setupDelegatesCalled)
        assertTrue(activity.setupComposeCalled)

        assertEquals(3, activity.callOrder.size)
        assertEquals("setupDelegates", activity.callOrder[0])
        assertEquals("setupViewModel", activity.callOrder[1])
        assertEquals("SetupCompose", activity.callOrder[2])
    }

    @Test
    @Config(sdk = [34])
    fun testLaunchCompatibility_API34() {
        val controller = Robolectric.buildActivity(ConcreteComponentActivity::class.java).setup()
        assertNotNull(controller.get())
    }
}
