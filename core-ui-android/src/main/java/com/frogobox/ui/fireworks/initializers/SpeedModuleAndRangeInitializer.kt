package com.frogobox.ui.fireworks.initializers

import com.frogobox.ui.fireworks.Particle
import java.util.*

class SpeedModuleAndRangeInitializer(
    private val mSpeedMin: Float,
    private val mSpeedMax: Float,
    private var mMinAngle: Int,
    private var mMaxAngle: Int
) : ParticleInitializer {

    init {
        // Make sure the angles are in the [0-360) range
        while (mMinAngle < 0) {
            mMinAngle += 360
        }
        while (mMaxAngle < 0) {
            mMaxAngle += 360
        }
        // Also make sure that mMinAngle is the smaller
        if (mMinAngle > mMaxAngle) {
            val tmp = mMinAngle
            mMinAngle = mMaxAngle
            mMaxAngle = tmp
        }
    }

    override fun initParticle(p: Particle?, r: Random?) {
        val speed = r!!.nextFloat() * (mSpeedMax - mSpeedMin) + mSpeedMin
        val angle: Int = if (mMaxAngle == mMinAngle) {
            mMinAngle
        } else {
            r.nextInt(mMaxAngle - mMinAngle) + mMinAngle
        }
        val angleInRads = Math.toRadians(angle.toDouble())
        p!!.mSpeedX = (speed * Math.cos(angleInRads)).toFloat()
        p.mSpeedY = (speed * Math.sin(angleInRads)).toFloat()
        p.mInitialRotation = (angle + 90).toFloat()
    }
}