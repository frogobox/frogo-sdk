package com.frogobox.sdk.piracychecker

import androidx.test.core.app.ActivityScenario
import com.frogobox.sdk.ui.FrogoWebViewActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PiracyCheckerDialogTest {

    @Test
    fun testNewInstanceSetsArgumentsProperly() {
        val title = "Unlicensed App"
        val content = "Please buy app from Play Store"

        val dialog = PiracyCheckerDialog.newInstance(title, content)

        assertNotNull(dialog.arguments)
        assertEquals(title, dialog.arguments?.getString("arg_dialog_title"))
        assertEquals(content, dialog.arguments?.getString("arg_dialog_content"))
    }

    @Test
    fun testShowWithAppCompatActivity() {
        val title = "Piracy Warning"
        val content = "Non-genuine build detected."

        ActivityScenario.launch(FrogoWebViewActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val dialog = PiracyCheckerDialog.newInstance(title, content)
                dialog.show(activity)

                activity.supportFragmentManager.executePendingTransactions()
                val foundFragment = activity.supportFragmentManager.findFragmentByTag("[LICENSE_DIALOG]")
                assertNotNull("DialogFragment should be attached to supportFragmentManager", foundFragment)
            }
        }
    }
}
