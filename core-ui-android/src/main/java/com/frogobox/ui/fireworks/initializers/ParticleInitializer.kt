package com.frogobox.ui.fireworks.initializers

import com.frogobox.ui.fireworks.Particle
import java.util.*

interface ParticleInitializer {
    fun initParticle(p: Particle?, r: Random?)
}