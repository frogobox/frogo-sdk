package com.frogobox.ui.loadingindicator

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.test.core.app.ApplicationProvider
import com.frogobox.ui.loadingindicator.indicators.BallPulseIndicator
import com.frogobox.ui.loadingindicator.indicators.BallClipRotateIndicator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class FrogoLoadingIndicatorViewTest {

    private val context: Context get() = ApplicationProvider.getApplicationContext()

    // =============================================================================================
    // UI & FUNCTIONAL TESTS
    // =============================================================================================

    @Test
    fun testDefaultState() {
        val view = FrogoLoadingIndicatorView(context)
        assertNotNull(view.indicator)
        assertTrue(view.indicator is BallPulseIndicator)
    }

    @Test
    fun testSetIndicatorInstance() {
        val view = FrogoLoadingIndicatorView(context)
        val newIndicator = BallClipRotateIndicator()
        view.setIndicator(newIndicator)
        assertEquals(newIndicator, view.indicator)
    }

    @Test
    fun testSetIndicatorByClassName() {
        val view = FrogoLoadingIndicatorView(context)
        // Tests loading indicator via reflection.
        // It should load com.frogobox.ui.loadingindicator.indicators.BallClipRotateIndicator
        view.setIndicator("BallClipRotateIndicator")
        assertNotNull(view.indicator)
        assertTrue("Indicator should be BallClipRotateIndicator", view.indicator is BallClipRotateIndicator)
    }

    @Test
    fun testSetIndicatorColor() {
        val view = FrogoLoadingIndicatorView(context)
        val color = Color.RED
        view.setIndicatorColor(color)
        assertEquals(color, view.indicator.color)
    }

    @Test
    fun testVisibilityAndSmoothShowHide() {
        val view = FrogoLoadingIndicatorView(context)
        
        // Initial visibility
        view.visibility = View.GONE
        assertEquals(View.GONE, view.visibility)

        // Smooth show makes visible
        view.smoothToShow()
        assertEquals(View.VISIBLE, view.visibility)

        // Smooth hide makes gone
        view.smoothToHide()
        assertEquals(View.GONE, view.visibility)
    }

    @Test
    fun testShowAndHideStandard() {
        val view = FrogoLoadingIndicatorView(context)
        
        // Show schedules/posts show
        view.show()
        // Default show posts delayed, let's verify visibility is visible or handled
        // If no delay has elapsed, we can still manually trigger hide or test visibility changes
        view.hide()
        assertEquals(View.GONE, view.visibility)
    }

    // =============================================================================================
    // COMPATIBILITY TESTS (Running on multiple API levels)
    // =============================================================================================

    @Test
    @Config(sdk = [24])
    fun testCompatibility_API24() {
        val view = FrogoLoadingIndicatorView(context)
        assertNotNull(view.indicator)
        view.setIndicatorColor(Color.BLUE)
        assertEquals(Color.BLUE, view.indicator.color)
    }

    @Test
    @Config(sdk = [28])
    fun testCompatibility_API28() {
        val view = FrogoLoadingIndicatorView(context)
        assertNotNull(view.indicator)
        view.setIndicatorColor(Color.GREEN)
        assertEquals(Color.GREEN, view.indicator.color)
    }

    @Test
    @Config(sdk = [33])
    fun testCompatibility_API33() {
        val view = FrogoLoadingIndicatorView(context)
        assertNotNull(view.indicator)
        view.setIndicatorColor(Color.YELLOW)
        assertEquals(Color.YELLOW, view.indicator.color)
    }

    @Test
    @Config(sdk = [34])
    fun testCompatibility_API34() {
        val view = FrogoLoadingIndicatorView(context)
        assertNotNull(view.indicator)
        view.setIndicatorColor(Color.CYAN)
        assertEquals(Color.CYAN, view.indicator.color)
    }
}
