package com.frogobox.sdk.widget.shimmer

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RadialGradient
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.view.animation.LinearInterpolator
import kotlin.math.sqrt
import kotlin.math.tan

class FrogoShimmerDrawable : Drawable() {

    private val mUpdateListener = ValueAnimator.AnimatorUpdateListener {
        invalidateSelf()
    }

    private val mShimmerPaint = Paint().apply {
        isAntiAlias = true
    }
    private val mDrawRect = Rect()
    private val mShaderMatrix = Matrix()

    private var mValueAnimator: ValueAnimator? = null
    private var mFrogoShimmer: FrogoShimmer? = null

    var shimmer: FrogoShimmer?
        get() = mFrogoShimmer
        set(value) {
            mFrogoShimmer = value
            if (value != null) {
                mShimmerPaint.xfermode = PorterDuffXfermode(
                    if (value.alphaShimmer) PorterDuff.Mode.DST_IN else PorterDuff.Mode.SRC_IN
                )
            }
            updateShader()
            updateValueAnimator()
            invalidateSelf()
        }

    /**
     * Starts the shimmer animation.
     */
    fun startShimmer() {
        val animator = mValueAnimator
        if (animator != null && !isShimmerStarted() && callback != null) {
            animator.start()
        }
    }

    /**
     * Stops the shimmer animation.
     */
    fun stopShimmer() {
        val animator = mValueAnimator
        if (animator != null && isShimmerStarted()) {
            animator.cancel()
        }
    }

    /**
     * Return whether the shimmer animation has been started.
     */
    fun isShimmerStarted(): Boolean {
        return mValueAnimator?.isStarted == true
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        mDrawRect.set(bounds)
        updateShader()
        maybeStartShimmer()
    }

    override fun draw(canvas: Canvas) {
        val shimmer = mFrogoShimmer ?: return
        if (mShimmerPaint.shader == null) {
            return
        }

        val tiltTan = tan(Math.toRadians(shimmer.tilt.toDouble())).toFloat()
        val translateHeight = mDrawRect.height() + tiltTan * mDrawRect.width()
        val translateWidth = mDrawRect.width() + tiltTan * mDrawRect.height()
        val animatedValue = (mValueAnimator?.animatedValue as? Float) ?: 0f

        val dx: Float
        val dy: Float
        when (shimmer.direction) {
            FrogoShimmer.Direction.RIGHT_TO_LEFT -> {
                dx = offset(translateWidth, -translateWidth, animatedValue)
                dy = 0f
            }
            FrogoShimmer.Direction.TOP_TO_BOTTOM -> {
                dx = 0f
                dy = offset(-translateHeight, translateHeight, animatedValue)
            }
            FrogoShimmer.Direction.BOTTOM_TO_TOP -> {
                dx = 0f
                dy = offset(translateHeight, -translateHeight, animatedValue)
            }
            else -> { // LEFT_TO_RIGHT
                dx = offset(-translateWidth, translateWidth, animatedValue)
                dy = 0f
            }
        }

        mShaderMatrix.reset()
        mShaderMatrix.setRotate(shimmer.tilt, mDrawRect.width() / 2f, mDrawRect.height() / 2f)
        mShaderMatrix.postTranslate(dx, dy)
        mShimmerPaint.shader?.setLocalMatrix(mShaderMatrix)
        canvas.drawRect(mDrawRect, mShimmerPaint)
    }

    override fun setAlpha(alpha: Int) {
        // No-op, modify the Shimmer object you pass in instead
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        // No-op, modify the Shimmer object you pass in instead
    }

    @Deprecated("Deprecated in Java")
    override fun getOpacity(): Int {
        val shimmer = mFrogoShimmer
        return if (shimmer != null && (shimmer.clipToChildren || shimmer.alphaShimmer)) {
            PixelFormat.TRANSLUCENT
        } else {
            PixelFormat.OPAQUE
        }
    }

    private fun offset(start: Float, end: Float, percent: Float): Float {
        return start + (end - start) * percent
    }

    private fun updateValueAnimator() {
        val shimmer = mFrogoShimmer ?: return

        val started: Boolean
        val animator = mValueAnimator
        if (animator != null) {
            started = animator.isStarted
            animator.cancel()
            animator.removeAllUpdateListeners()
        } else {
            started = false
        }

        val animDuration = maxOf(1L, shimmer.animationDuration)
        val repeatDelayFraction = shimmer.repeatDelay.toFloat() / animDuration.toFloat()
        val newAnimator = ValueAnimator.ofFloat(0f, 1f + repeatDelayFraction).apply {
            interpolator = LinearInterpolator()
            repeatMode = shimmer.repeatMode
            startDelay = shimmer.startDelay
            repeatCount = shimmer.repeatCount
            duration = shimmer.animationDuration + shimmer.repeatDelay
            addUpdateListener(mUpdateListener)
        }
        mValueAnimator = newAnimator
        if (started) {
            newAnimator.start()
        }
    }

    internal fun maybeStartShimmer() {
        val animator = mValueAnimator
        val shimmer = mFrogoShimmer
        if (animator != null &&
            !animator.isStarted &&
            shimmer != null &&
            shimmer.autoStart &&
            callback != null
        ) {
            animator.start()
        }
    }

    private fun updateShader() {
        val currentBounds = bounds
        val boundsWidth = currentBounds.width()
        val boundsHeight = currentBounds.height()
        val shimmer = mFrogoShimmer
        if (boundsWidth == 0 || boundsHeight == 0 || shimmer == null) {
            return
        }
        val width = shimmer.width(boundsWidth)
        val height = shimmer.height(boundsHeight)

        val shader: Shader = when (shimmer.shape) {
            FrogoShimmer.Shape.RADIAL -> {
                RadialGradient(
                    width / 2f,
                    height / 2f,
                    (maxOf(width, height) / sqrt(2.0)).toFloat(),
                    shimmer.colors,
                    shimmer.positions,
                    Shader.TileMode.CLAMP
                )
            }
            else -> {
                val vertical = shimmer.direction == FrogoShimmer.Direction.TOP_TO_BOTTOM ||
                    shimmer.direction == FrogoShimmer.Direction.BOTTOM_TO_TOP
                val endX = if (vertical) 0 else width
                val endY = if (vertical) height else 0
                LinearGradient(
                    0f,
                    0f,
                    endX.toFloat(),
                    endY.toFloat(),
                    shimmer.colors,
                    shimmer.positions,
                    Shader.TileMode.CLAMP
                )
            }
        }

        mShimmerPaint.shader = shader
    }
}
