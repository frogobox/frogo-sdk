package com.frogobox.app

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import com.frogobox.R
import com.frogobox.appadmob.mvvm.appopenad.AppOpenAdActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.TIRAMISU])
class AppOpenAdActivityTest {

    @Test
    fun testAppOpenAdActivityLaunchAndInitialState() {
        ActivityScenario.launch(AppOpenAdActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                assertNotNull("Activity should launch successfully", activity)

                val chipStatus = activity.findViewById<TextView>(R.id.chip_ad_status)
                assertNotNull("chip_ad_status must exist in layout", chipStatus)
                assertEquals("NOT LOADED", chipStatus.text.toString())

                val btnPreload = activity.findViewById<MaterialButton>(R.id.btn_preload_ad)
                assertNotNull("btn_preload_ad must exist", btnPreload)
                assertEquals(true, btnPreload.isEnabled)

                val btnShow = activity.findViewById<MaterialButton>(R.id.btn_show_ad)
                assertNotNull("btn_show_ad must exist", btnShow)
                assertFalse("btn_show_ad should be disabled initially", btnShow.isEnabled)

                val btnLoadAndShow = activity.findViewById<MaterialButton>(R.id.btn_load_and_show)
                assertNotNull("btn_load_and_show must exist", btnLoadAndShow)

                val btnClearAd = activity.findViewById<MaterialButton>(R.id.btn_clear_ad)
                assertNotNull("btn_clear_ad must exist", btnClearAd)

                val progress = activity.findViewById<LinearProgressIndicator>(R.id.progress_ad_loading)
                assertNotNull("progress_ad_loading must exist", progress)
                assertEquals(View.INVISIBLE, progress.visibility)

                val tvLog = activity.findViewById<TextView>(R.id.tv_event_log)
                assertNotNull("tv_event_log must exist", tvLog)

                // Verify clear button triggers without crashing
                btnClearAd.performClick()
                assertEquals("NOT LOADED", chipStatus.text.toString())
            }
        }
    }
}
