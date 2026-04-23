package com.frogobox.ui.fireworks.modifiers

import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.frogobox.ui.fireworks.Particle

class ScaleModifier @JvmOverloads constructor(
    private val mInitialValue: Float,
    private val mFinalValue: Float,
    private val mStartTime: Long,
    private val mEndTime: Long,
    interpolator: Interpolator = LinearInterpolator()
) : ParticleModifier {

    private val mDuration: Long = mEndTime - mStartTime
    private val mValueIncrement: Float = mFinalValue - mInitialValue
    private val mInterpolator: Interpolator

    init {
        mInterpolator = interpolator
    }

    override fun apply(particle: Particle?, miliseconds: Long) {
        if (miliseconds < mStartTime) {
            particle!!.mScale = mInitialValue
        } else if (miliseconds > mEndTime) {
            particle!!.mScale = mFinalValue
        } else {
            val interpolaterdValue =
                mInterpolator.getInterpolation((miliseconds - mStartTime) * 1f / mDuration)
            val newScale = mInitialValue + mValueIncrement * interpolaterdValue
            particle!!.mScale = newScale
        }
    }
}