package com.frogobox.ui.fireworks

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

class AnimatedParticle(
    private val mAnimationDrawable: AnimationDrawable,
) : Particle() {

    private var mTotalTime: Int

    init {
        val frameCount = mAnimationDrawable.numberOfFrames
        mImage = if (frameCount > 0) mAnimationDrawable.getFrame(0).extractBitmap() else null
        // If it is a repeating animation, calculate the time
        mTotalTime = 0
        for (i in 0 until frameCount) {
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
                    if (mTotalTime > 0) realMiliseconds % mTotalTime else 0
                }
            }
            for (i in 0 until mAnimationDrawable.numberOfFrames) {
                animationElapsedTime += mAnimationDrawable.getDuration(i).toLong()
                if (animationElapsedTime > realMiliseconds) {
                    mImage = mAnimationDrawable.getFrame(i).extractBitmap()
                    break
                }
            }
        }
        return active
    }

    private fun Drawable?.extractBitmap(): Bitmap? {
        if (this == null) return null
        if (this is BitmapDrawable) return this.bitmap
        val width = if (intrinsicWidth > 0) intrinsicWidth else 1
        val height = if (intrinsicHeight > 0) intrinsicHeight else 1
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        setBounds(0, 0, canvas.width, canvas.height)
        draw(canvas)
        return bitmap
    }
}