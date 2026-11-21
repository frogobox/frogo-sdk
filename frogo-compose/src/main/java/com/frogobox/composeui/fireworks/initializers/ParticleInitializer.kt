package com.frogobox.composeui.fireworks.initializers

import com.frogobox.composeui.fireworks.Particle
import java.util.Random

interface ParticleInitializer {
    fun initParticle(p: Particle?, r: Random?)
}