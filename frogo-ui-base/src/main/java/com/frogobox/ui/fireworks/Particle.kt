package com.frogobox.ui.fireworks

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import com.frogobox.ui.fireworks.modifiers.ParticleModifier

open class Particle protected constructor() {

    protected var mImage: Bitmap? = null
    var mCurrentX = 0f
    var mCurrentY = 0f
    var mScale = 1f
    var mAlpha = 255
    var mInitialRotation = 0f
    var mRotationSpeed = 0f
    var mSpeedX = 0f
    var mSpeedY = 0f
    var mAccelerationX = 0f
    var mAccelerationY = 0f
    private val mMatrix: Matrix
    private val mPaint: Paint
    private var mInitialX = 0f
    private var mInitialY = 0f
    private var mRotation = 0f
    private var mTimeToLive: Long = 0
    protected var mStartingMilisecond: Long = 0
    private var mBitmapHalfWidth = 0
    private var mBitmapHalfHeight = 0
    private var mModifiers: List<ParticleModifier>? = null

    init {
        mMatrix = Matrix()
        mPaint = Paint()
    }

    constructor(bitmap: Bitmap?) : this() {
        mImage = bitmap
    }

    fun init() {
        mScale = 1f
        mAlpha = 255
    }

    fun configure(timeToLive: Long, emiterX: Float, emiterY: Float) {
        mBitmapHalfWidth = mImage!!.width / 2
        mBitmapHalfHeight = mImage!!.height / 2
        mInitialX = emiterX - mBitmapHalfWidth
        mInitialY = emiterY - mBitmapHalfHeight
        mCurrentX = mInitialX
        mCurrentY = mInitialY
        mTimeToLive = timeToLive
    }

    open fun update(miliseconds: Long): Boolean {
        val realMiliseconds = miliseconds - mStartingMilisecond
        if (realMiliseconds > mTimeToLive) {
            return false
        }
        mCurrentX =
            mInitialX + mSpeedX * realMiliseconds + mAccelerationX * realMiliseconds * realMiliseconds
        mCurrentY =
            mInitialY + mSpeedY * realMiliseconds + mAccelerationY * realMiliseconds * realMiliseconds
        mRotation = mInitialRotation + mRotationSpeed * realMiliseconds / 1000
        for (i in mModifiers!!.indices) {
            mModifiers!![i].apply(this, realMiliseconds)
        }
        return true
    }

    fun draw(c: Canvas) {
        mMatrix.reset()
        mMatrix.postRotate(mRotation, mBitmapHalfWidth.toFloat(), mBitmapHalfHeight.toFloat())
        mMatrix.postScale(mScale, mScale, mBitmapHalfWidth.toFloat(), mBitmapHalfHeight.toFloat())
        mMatrix.postTranslate(mCurrentX, mCurrentY)
        mPaint.alpha = mAlpha
        c.drawBitmap(mImage!!, mMatrix, mPaint)
    }

    fun activate(startingMilisecond: Long, modifiers: List<ParticleModifier>?): Particle {
        mStartingMilisecond = startingMilisecond
        // We do store a reference to the list, there is no need to copy, since the modifiers do not carte about states
        mModifiers = modifiers
        return this
    }
}