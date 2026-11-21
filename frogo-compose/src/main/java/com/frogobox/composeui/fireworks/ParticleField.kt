package com.frogobox.composeui.fireworks

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

internal class ParticleField : View {
    private var mParticles: ArrayList<Particle>? = null

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context,
        attrs,
        defStyle) {
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?) : super(context) {}

    fun setParticles(particles: ArrayList<Particle>?) {
        mParticles = particles
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw all the particles
        val particles = mParticles ?: return
        synchronized(particles) {
            for (i in particles.indices) {
                particles[i].draw(canvas)
            }
        }
    }
}