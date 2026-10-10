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
class FrogoComposeBottomSheetFragmentTest {

    class TestBottomSheetFragment : FrogoComposeBottomSheetFragment() {
        var setupViewModelCalled = false
        var setupComposeCalled = false

        override fun setupViewModel() {
            super.setupViewModel()
            setupViewModelCalled = true
        }

        @Composable
        override fun SetupCompose() {
            setupComposeCalled = true
            Text(text = "Hello Test BottomSheet Compose")
        }
    }

    @Test
    fun testBottomSheetFragmentLifecycle() {
        val activity = Robolectric.buildActivity(FragmentActivity::class.java).setup().get()
        val fragment = TestBottomSheetFragment()

        fragment.show(activity.supportFragmentManager, "test_bottom_sheet")
        activity.supportFragmentManager.executePendingTransactions()

        assertNotNull(fragment)
        assertTrue(fragment.setupViewModelCalled)
        assertNotNull(fragment.view)
    }
}
