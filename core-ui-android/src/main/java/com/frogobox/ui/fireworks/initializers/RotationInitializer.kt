package com.frogobox.ui.fireworks.initializers

import com.frogobox.ui.fireworks.Particle
import java.util.*

class RotationInitializer(
    private val mMinAngle: Int,
    private val mMaxAngle: Int,
) : ParticleInitializer {

    override fun initParticle(p: Particle?, r: Random?) {
        p!!.mInitialRotation =
            if (mMinAngle == mMaxAngle) mMinAngle.toFloat() else r!!.nextInt(mMaxAngle - mMinAngle) + mMinAngle.toFloat()
    }

}