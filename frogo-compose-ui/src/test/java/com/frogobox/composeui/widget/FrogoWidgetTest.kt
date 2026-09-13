package com.frogobox.composeui.widget

import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
import com.frogobox.composeui.fireworks.FrogoFireworksStateCompose
import com.frogobox.composeui.template.empty.FrogoEmptyState
import com.frogobox.composeui.theme.FrogoTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class FrogoWidgetTest {

    // =============================================================================================
    // FIREWORKS PHYSICS & SIMULATION TESTS
    // =============================================================================================

    @Test
    fun testFireworksStateExplodeAndPhysicsUpdate() {
        val state = FrogoFireworksStateCompose()
        assertEquals(0, state.particles.size)

        // Explode with 20 particles
        state.explode(100f, 200f, count = 20)
        assertEquals(20, state.particles.size)

        val firstParticle = state.particles[0]
        val initialAge = firstParticle.age

        // Simulate 16ms elapsed time (1 frame at 60fps)
        state.update(16L)

        // Verify that elapsed time updates particle age and alpha decays
        assertTrue("Particle age should advance", firstParticle.age > initialAge)
        assertTrue("Particle alpha should be <= 1f", firstParticle.alpha <= 1f)
    }

    @Test
    fun testFireworksParticleLifetimeExpiration() {
        val state = FrogoFireworksStateCompose()
        state.explode(50f, 50f, count = 5)
        assertEquals(5, state.particles.size)

        // Simulate advancing time past particle maximum lifetime (2000ms)
        state.update(3000L)

        // All expired particles should be cleaned up
        assertEquals(0, state.particles.size)
    }

    @Test
    fun testFireworksEmissionControl() {
        val state = FrogoFireworksStateCompose()
        state.startEmit(50f, 50f, rateMillis = 5L)

        // Allow time to pass for emission
        Thread.sleep(15)
        state.update(15L)
        assertTrue("Particles should be emitted continuously", state.particles.isNotEmpty())

        state.stopEmit()
    }

    // =============================================================================================
    // COMPOSE WIDGETS INTEGRATION & LIFECYCLE TESTS (Robolectric)
    // =============================================================================================

    @Test
    fun testComposeWidgetsCompositionInRobolectric() {
        val activity = Robolectric.buildActivity(ComponentActivity::class.java).setup().get()
        val composeView = ComposeView(activity)
        var buttonClicked = false
        var checkboxChecked = false

        composeView.setContent {
            FrogoTheme {
                FrogoButton(
                    text = "Test Button",
                    onClick = { buttonClicked = true }
                )
                FrogoCheckbox(
                    checked = checkboxChecked,
                    onCheckedChange = { checkboxChecked = it },
                    label = "Test Checkbox"
                )
                FrogoRadioButton(
                    selected = true,
                    onClick = {},
                    label = "Test Radio"
                )
                FrogoEmptyState(
                    title = "Empty State",
                    subtitle = "No data here"
                )
                FrogoSpacerHeight()
            }
        }

        activity.setContentView(composeView)
        // Force layout pass to trigger composition
        composeView.measure(
            View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(1920, View.MeasureSpec.EXACTLY)
        )
        composeView.layout(0, 0, 1080, 1920)

        assertNotNull(composeView)
    }

    // =============================================================================================
    // COMPATIBILITY TESTS (Multi-API Level)
    // =============================================================================================

    @Test
    @Config(sdk = [24])
    fun testFireworksCompatibility_API24() {
        val state = FrogoFireworksStateCompose()
        state.explode(0f, 0f, count = 10)
        state.update(16L)
        assertEquals(10, state.particles.size)
    }

    @Test
    @Config(sdk = [34])
    fun testFireworksCompatibility_API34() {
        val state = FrogoFireworksStateCompose()
        state.explode(0f, 0f, count = 10)
        state.update(16L)
        assertEquals(10, state.particles.size)
    }
}
