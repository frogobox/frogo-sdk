package com.frogobox.ui.fireworks

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import java.util.ArrayList

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
        synchronized(mParticles!!) {
            for (i in mParticles!!.indices) {
                mParticles!![i].draw(canvas)
            }
        }
    }
}