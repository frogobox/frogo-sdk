package com.frogobox.ui.fireworks.initializers

import com.frogobox.ui.fireworks.Particle
import java.util.*

class ScaleInitializer(
    private val mMinScale: Float,
    private val mMaxScale: Float,
) : ParticleInitializer {

    override fun initParticle(p: Particle?, r: Random?) {
        val scale = r!!.nextFloat() * (mMaxScale - mMinScale) + mMinScale
        p!!.mScale = scale
    }

}