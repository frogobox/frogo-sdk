package com.frogobox.compose.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FrogoComposeFragmentTest {

    class TestFragment : FrogoComposeFragment() {
        var setupViewModelCalled = false
        var setupComposeCalled = false

        override fun setupViewModel() {
            super.setupViewModel()
            setupViewModelCalled = true
        }

        @Composable
        override fun setupCompose() {
            setupComposeCalled = true
            Text(text = "Hello Test Fragment Compose")
        }
    }

    @Test
    fun testFragmentLifecycle() {
        val activity = Robolectric.buildActivity(FragmentActivity::class.java).setup().get()
        
        // Create a container and add it to the activity's view hierarchy
        val container = android.widget.FrameLayout(activity).apply {
            id = android.view.View.generateViewId()
        }
        activity.setContentView(container)

        val fragment = TestFragment()

        activity.supportFragmentManager.beginTransaction()
            .replace(container.id, fragment, "test_fragment")
            .commitNow()

        // Force a layout pass to trigger the ComposeView composition
        container.measure(
            android.view.View.MeasureSpec.makeMeasureSpec(1080, android.view.View.MeasureSpec.EXACTLY),
            android.view.View.MeasureSpec.makeMeasureSpec(1920, android.view.View.MeasureSpec.EXACTLY)
        )
        container.layout(0, 0, 1080, 1920)

        assertNotNull(fragment)
        assertTrue(fragment.setupViewModelCalled)
        assertTrue(fragment.setupComposeCalled)
    }
}
