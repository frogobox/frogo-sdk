package com.frogobox.ui.fireworks

import android.graphics.Bitmap
import com.frogobox.ui.fireworks.modifiers.ParticleModifier
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ParticleTest {

    @Test
    fun testParticleInitialization() {
        val bitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
        val particle = Particle(bitmap)
        particle.init()
        
        assertEquals(1f, particle.mScale)
        assertEquals(255, particle.mAlpha)
    }

    @Test
    fun testParticleConfigure() {
        val bitmap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
        val particle = Particle(bitmap)
        
        // Emitter at (100, 200). Half width = 10, Half height = 10
        // Initial coordinates should be emitter - half dimension = (90, 190)
        particle.configure(1000, 100f, 200f)

        assertEquals(90f, particle.mCurrentX)
        assertEquals(190f, particle.mCurrentY)
    }

    @Test
    fun testParticleUpdateInsideTimeToLive() {
        val bitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
        val particle = Particle(bitmap)
        
        particle.configure(1000, 100f, 100f)
        particle.mSpeedX = 0.5f
        particle.mSpeedY = 0.2f
        particle.mAccelerationX = 0.01f
        particle.mAccelerationY = 0.02f
        
        // Activate particle at t = 100 ms
        val modifiers = ArrayList<ParticleModifier>()
        particle.activate(100, modifiers)
        
        // Update at t = 200 ms (realMilliseconds = 100)
        // Expected X: InitialX (95) + speedX * 100 + accelerationX * 100 * 100
        // 95 + 0.5 * 100 + 0.01 * 10000 = 95 + 50 + 100 = 245
        // Expected Y: InitialY (95) + speedY * 100 + accelerationY * 100 * 100
        // 95 + 0.2 * 100 + 0.02 * 10000 = 95 + 20 + 200 = 315
        val updated = particle.update(200)
        
        assertTrue(updated)
        assertEquals(245f, particle.mCurrentX, 0.01f)
        assertEquals(315f, particle.mCurrentY, 0.01f)
    }

    @Test
    fun testParticleUpdateOutsideTimeToLive() {
        val bitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
        val particle = Particle(bitmap)
        
        particle.configure(500, 100f, 100f)
        particle.activate(100, ArrayList())
        
        // Update at t = 700 ms (realMilliseconds = 600, which is > timeToLive = 500)
        val updated = particle.update(700)
        assertFalse(updated)
    }
}
