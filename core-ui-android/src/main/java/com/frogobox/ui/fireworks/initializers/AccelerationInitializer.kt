package com.frogobox.ui.fireworks.initializers

import com.frogobox.ui.fireworks.initializers.ParticleInitializer
import com.frogobox.ui.fireworks.Particle
import java.util.*

class AccelerationInitializer(
    private val mMinValue: Float,
    private val mMaxValue: Float,
    private val mMinAngle: Int,
    private val mMaxAngle: Int
) : ParticleInitializer {

    override fun initParticle(p: Particle?, r: Random?) {
        var angle = mMinAngle.toFloat()
        if (mMaxAngle != mMinAngle) {
            angle = (r!!.nextInt(mMaxAngle - mMinAngle) + mMinAngle).toFloat()
        }
        val angleInRads = (angle * Math.PI / 180f).toFloat()
        val value = r!!.nextFloat() * (mMaxValue - mMinValue) + mMinValue
        p!!.mAccelerationX = (value * Math.cos(angleInRads.toDouble())).toFloat()
        p.mAccelerationY = (value * Math.sin(angleInRads.toDouble())).toFloat()
    }

}