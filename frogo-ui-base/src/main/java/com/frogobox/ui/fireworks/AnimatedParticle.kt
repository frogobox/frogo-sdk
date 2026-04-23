package com.frogobox.ui.fireworks

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable

class AnimatedParticle(
    private val mAnimationDrawable: AnimationDrawable,
) : Particle() {

    private var mTotalTime: Int

    init {
        mImage = (mAnimationDrawable.getFrame(0) as BitmapDrawable).bitmap
        // If it is a repeating animation, calculate the time
        mTotalTime = 0
        for (i in 0 until mAnimationDrawable.numberOfFrames) {
            mTotalTime += mAnimationDrawable.getDuration(i)
        }
    }

    override fun update(miliseconds: Long): Boolean {
        val active = super.update(miliseconds)
        if (active) {
            var animationElapsedTime: Long = 0
            var realMiliseconds = miliseconds - mStartingMilisecond
            if (realMiliseconds > mTotalTime) {
                realMiliseconds = if (mAnimationDrawable.isOneShot) {
                    return false
                } else {
                    realMiliseconds % mTotalTime
                }
            }
            for (i in 0 until mAnimationDrawable.numberOfFrames) {
                animationElapsedTime += mAnimationDrawable.getDuration(i).toLong()
                if (animationElapsedTime > realMiliseconds) {
                    mImage = (mAnimationDrawable.getFrame(i) as BitmapDrawable).bitmap
                    break
                }
            }
        }
        return active
    }
}