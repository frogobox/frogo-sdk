package com.frogobox.ui.fireworks.modifiers

import com.frogobox.ui.fireworks.Particle

interface ParticleModifier {
    /**
     * modifies the specific value of a particle given the current miliseconds
     *
     * @param particle
     * @param miliseconds
     */
    fun apply(particle: Particle?, miliseconds: Long)
}