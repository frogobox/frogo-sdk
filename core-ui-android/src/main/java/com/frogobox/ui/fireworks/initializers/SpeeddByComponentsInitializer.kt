package com.frogobox.ui.fireworks.initializers

import com.frogobox.ui.fireworks.Particle
import java.util.*

class SpeeddByComponentsInitializer(
    private val mMinSpeedX: Float,
    private val mMaxSpeedX: Float,
    private val mMinSpeedY: Float,
    private val mMaxSpeedY: Float
) : ParticleInitializer {

    override fun initParticle(p: Particle?, r: Random?) {
        p!!.mSpeedX = r!!.nextFloat() * (mMaxSpeedX - mMinSpeedX) + mMinSpeedX
        p.mSpeedY = r.nextFloat() * (mMaxSpeedY - mMinSpeedY) + mMinSpeedY
    }

}