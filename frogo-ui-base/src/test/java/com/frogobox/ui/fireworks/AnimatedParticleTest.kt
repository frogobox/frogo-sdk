package com.frogobox.ui.fireworks

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class AnimatedParticleTest {

    @Test
    fun testEmptyAnimationDrawable_doesNotCrash() {
        val emptyAnim = AnimationDrawable()
        val particle = AnimatedParticle(emptyAnim)

        // Must not crash and image is null
        assertNull(particle.image)
        assertFalse(particle.update(100))
    }

    @Test
    fun testNonBitmapDrawableFrames_extractsBitmapWithoutCrash() {
        val anim = AnimationDrawable().apply {
            addFrame(ColorDrawable(Color.RED), 100)
            addFrame(ColorDrawable(Color.BLUE), 100)
        }
        val particle = AnimatedParticle(anim)

        // Frame 0 bitmap extracted
        assertNotNull(particle.image)
    }
}
