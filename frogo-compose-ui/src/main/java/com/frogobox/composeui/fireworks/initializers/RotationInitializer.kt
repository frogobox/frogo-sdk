package com.frogobox.composeui.fireworks.initializers

import com.frogobox.composeui.fireworks.Particle
import java.util.Random

class RotationInitializer(
    private val mMinAngle: Int,
    private val mMaxAngle: Int,
) : ParticleInitializer {

    override fun initParticle(p: Particle?, r: Random?) {
        if (p == null || r == null) return
        p.mInitialRotation =
            if (mMinAngle == mMaxAngle) mMinAngle.toFloat() else (r.nextInt(mMaxAngle - mMinAngle) + mMinAngle).toFloat()
    }

}