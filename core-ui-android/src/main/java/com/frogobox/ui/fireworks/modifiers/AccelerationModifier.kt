package com.frogobox.ui.fireworks.modifiers

import com.frogobox.ui.fireworks.Particle
import kotlin.math.cos
import kotlin.math.sin

class AccelerationModifier(velocity: Float, angle: Float) : ParticleModifier {
    private val mVelocityX: Float
    private val mVelocityY: Float

    init {
        val velocityAngleInRads = (angle * Math.PI / 180f).toFloat()
        mVelocityX = (velocity * cos(velocityAngleInRads.toDouble())).toFloat()
        mVelocityY = (velocity * sin(velocityAngleInRads.toDouble())).toFloat()
    }

    override fun apply(particle: Particle?, miliseconds: Long) {
        particle!!.mCurrentX += mVelocityX * miliseconds * miliseconds
        particle!!.mCurrentY += mVelocityY * miliseconds * miliseconds
    }
}