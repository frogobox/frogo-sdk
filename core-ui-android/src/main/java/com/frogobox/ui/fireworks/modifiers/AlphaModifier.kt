package com.frogobox.ui.fireworks.modifiers

import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.frogobox.ui.fireworks.Particle

class AlphaModifier @JvmOverloads constructor(
    private val mInitialValue: Int,
    private val mFinalValue: Int,
    private val mStartTime: Long,
    private val mEndTime: Long,
    interpolator: Interpolator = LinearInterpolator()
) : ParticleModifier {

    private val mDuration: Float = (mEndTime - mStartTime).toFloat()
    private val mValueIncrement: Float = (mFinalValue - mInitialValue).toFloat()
    private val mInterpolator: Interpolator

    init {
        mInterpolator = interpolator
    }

    override fun apply(particle: Particle?, miliseconds: Long) {
        if (miliseconds < mStartTime) {
            particle!!.mAlpha = mInitialValue
        } else if (miliseconds > mEndTime) {
            particle!!.mAlpha = mFinalValue
        } else {
            val interpolaterdValue = mInterpolator.getInterpolation((miliseconds - mStartTime) * 1f / mDuration)
            val newAlphaValue = (mInitialValue + mValueIncrement * interpolaterdValue).toInt()
            particle!!.mAlpha = newAlphaValue
        }
    }
}