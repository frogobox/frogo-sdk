package com.frogobox.compose.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FrogoComposeNavActivityTest {

    class TestNavActivity : FrogoComposeNavActivity() {
        var setupNavigationCalled = false
        var passedNavController: NavHostController? = null

        @Composable
        override fun SetupNavigation(navController: NavHostController) {
            setupNavigationCalled = true
            passedNavController = navController
        }
    }

    @Test
    fun testNavActivityLaunch() {
        val controller = Robolectric.buildActivity(TestNavActivity::class.java).setup()
        val activity = controller.get()

        assertNotNull(activity)
        assertTrue(activity.setupNavigationCalled)
        assertNotNull(activity.passedNavController)
    }
}
