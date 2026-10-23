package com.frogobox.ui.animation.core

import android.content.Context
import android.view.View
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class SlideAndZoomSafeParentTest {

    @Test
    fun testSlideWithoutParentDoesNotCrash() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val standaloneView = View(context)

        // View has no parent; must not throw ClassCastException or NullPointerException
        assertNotNull(Slide.InLeft(standaloneView))
        assertNotNull(Slide.InRight(standaloneView))
        assertNotNull(Slide.InUp(standaloneView))
        assertNotNull(Slide.OutDown(standaloneView))
        assertNotNull(Slide.OutRight(standaloneView))
    }

    @Test
    fun testZoomWithoutParentDoesNotCrash() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val standaloneView = View(context)

        // View has no parent; must not throw ClassCastException or NullPointerException
        assertNotNull(Zoom.InUp(standaloneView))
        assertNotNull(Zoom.OutDown(standaloneView))
        assertNotNull(Zoom.OutRight(standaloneView))
    }
}
