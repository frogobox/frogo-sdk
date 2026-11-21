package com.frogobox.ui.loadingindicator

import android.graphics.Rect
import com.frogobox.ui.loadingindicator.indicators.BallPulseIndicator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

@RunWith(RobolectricTestRunner::class)
class IndicatorTest {

    // =============================================================================================
    // UNIT TESTS
    // =============================================================================================

    @Test
    fun testIndicatorDefaultColor() {
        val indicator = BallPulseIndicator()
        // Default color is Color.WHITE = 0xFFFFFFFF = -1
        assertEquals(-1, indicator.color)
    }

    @Test
    fun testIndicatorSetColor() {
        val indicator = BallPulseIndicator()
        indicator.setColor(0xFF0000FF.toInt()) // Blue
        assertEquals(0xFF0000FF.toInt(), indicator.color)
    }

    @Test
    fun testIndicatorAlpha() {
        val indicator = BallPulseIndicator()
        assertEquals(255, indicator.alpha)

        indicator.alpha = 128
        assertEquals(128, indicator.alpha)
    }

    @Test
    fun testIndicatorDrawBounds() {
        val indicator = BallPulseIndicator()
        val bounds = Rect(10, 20, 110, 120)
        indicator.setDrawBounds(bounds)

        val drawBounds = indicator.drawBounds
        assertEquals(10, drawBounds.left)
        assertEquals(20, drawBounds.top)
        assertEquals(110, drawBounds.right)
        assertEquals(120, drawBounds.bottom)
    }

    @Test
    fun testIndicatorDimensions() {
        val indicator = BallPulseIndicator()
        indicator.setDrawBounds(0, 0, 200, 100)

        assertEquals(200, indicator.width)
        assertEquals(100, indicator.height)
        assertEquals(100, indicator.centerX())
        assertEquals(50, indicator.centerY())
        assertEquals(100f, indicator.exactCenterX(), 0.01f)
        assertEquals(50f, indicator.exactCenterY(), 0.01f)
    }

    @Test
    fun testIndicatorSetDrawBoundsOverload() {
        val indicator = BallPulseIndicator()
        indicator.setDrawBounds(5, 10, 205, 110)

        assertEquals(5, indicator.drawBounds.left)
        assertEquals(10, indicator.drawBounds.top)
        assertEquals(205, indicator.drawBounds.right)
        assertEquals(110, indicator.drawBounds.bottom)
    }

    @Test
    fun testIndicatorOnCreateAnimators() {
        val indicator = BallPulseIndicator()
        indicator.setDrawBounds(0, 0, 100, 100)
        val animators = indicator.onCreateAnimators()

        assertNotNull(animators)
        assertTrue("BallPulseIndicator should create animators", animators.size > 0)
    }

    // =============================================================================================
    // PERFORMANCE TEST
    // =============================================================================================

    @Test
    fun testIndicatorCreationPerformance() {
        val count = 10000
        val timeTaken = measureTimeMillis {
            for (i in 0 until count) {
                val indicator = BallPulseIndicator()
                indicator.setDrawBounds(0, 0, 100, 100)
                indicator.setColor(0xFF00FF00.toInt())
            }
        }
        println("Performance: Created $count BallPulseIndicator instances in $timeTaken ms")
        assertTrue("Creation of $count indicators took too long: $timeTaken ms", timeTaken < 3000)
    }

    // =============================================================================================
    // STRESS TEST
    // =============================================================================================

    @Test
    fun testIndicatorConcurrentBoundsModification() {
        val indicator = BallPulseIndicator()
        val numThreads = 10
        val opsPerThread = 500
        val executor = Executors.newFixedThreadPool(numThreads)
        val completedOps = AtomicInteger(0)

        for (i in 0 until numThreads) {
            executor.submit {
                for (j in 0 until opsPerThread) {
                    indicator.setDrawBounds(j, j, j + 100, j + 100)
                    indicator.setColor((i * 1000 + j))
                    indicator.alpha = (j % 256)
                    completedOps.incrementAndGet()
                }
            }
        }

        executor.shutdown()
        executor.awaitTermination(10, TimeUnit.SECONDS)

        assertEquals(numThreads * opsPerThread, completedOps.get())
        // The indicator should still have valid bounds
        assertNotNull(indicator.drawBounds)
    }
}
