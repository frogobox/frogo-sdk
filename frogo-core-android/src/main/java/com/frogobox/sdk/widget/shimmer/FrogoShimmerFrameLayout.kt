package com.frogobox.sdk.widget.shimmer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.frogobox.sdk.R

/**
 * Shimmer is an Android library that provides an easy way to add a shimmer effect to any [View].
 * It is useful as an unobtrusive loading indicator, and was originally developed for Facebook Home.
 *
 * Find more examples and usage instructions over at: facebook.github.io/shimmer-android
 */
open class FrogoShimmerFrameLayout : FrameLayout {

    private val mContentPaint = Paint()
    private val mFrogoShimmerDrawable = FrogoShimmerDrawable()

    private var mShowShimmer: Boolean = true
    private var mStoppedShimmerBecauseVisibility: Boolean = false

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        setWillNotDraw(false)
        mFrogoShimmerDrawable.callback = this

        if (attrs == null) {
            shimmer = FrogoShimmer.AlphaHighlightBuilder().build()
            return
        }

        val a = context.obtainStyledAttributes(attrs, R.styleable.FrogoShimmerFrameLayout, 0, 0)
        try {
            val isColored = a.hasValue(R.styleable.FrogoShimmerFrameLayout_shimmer_colored) &&
                a.getBoolean(R.styleable.FrogoShimmerFrameLayout_shimmer_colored, false)
            val shimmerObj = if (isColored) {
                FrogoShimmer.ColorHighlightBuilder().consumeAttributes(a).build()
            } else {
                FrogoShimmer.AlphaHighlightBuilder().consumeAttributes(a).build()
            }
            shimmer = shimmerObj
        } finally {
            a.recycle()
        }
    }

    var shimmer: FrogoShimmer?
        get() = mFrogoShimmerDrawable.shimmer
        set(value) {
            mFrogoShimmerDrawable.shimmer = value
            if (value != null && value.clipToChildren) {
                setLayerType(LAYER_TYPE_HARDWARE, mContentPaint)
            } else {
                setLayerType(LAYER_TYPE_NONE, null)
            }
        }

    /**
     * Starts the shimmer animation.
     */
    fun startShimmer() {
        mFrogoShimmerDrawable.startShimmer()
    }

    /**
     * Stops the shimmer animation.
     */
    fun stopShimmer() {
        mStoppedShimmerBecauseVisibility = false
        mFrogoShimmerDrawable.stopShimmer()
    }

    /**
     * Return whether the shimmer animation has been started.
     */
    fun isShimmerStarted(): Boolean {
        return mFrogoShimmerDrawable.isShimmerStarted()
    }

    /**
     * Sets the ShimmerDrawable to be visible.
     *
     * @param startShimmer Whether to start the shimmer again.
     */
    fun showShimmer(startShimmer: Boolean) {
        mShowShimmer = true
        if (startShimmer) {
            startShimmer()
        }
        invalidate()
    }

    /**
     * Sets the ShimmerDrawable to be invisible, stopping it in the process.
     */
    fun hideShimmer() {
        stopShimmer()
        mShowShimmer = false
        invalidate()
    }

    /**
     * Return whether the shimmer drawable is visible.
     */
    fun isShimmerVisible(): Boolean {
        return mShowShimmer
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val width = width
        val height = height
        mFrogoShimmerDrawable.setBounds(0, 0, width, height)
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility != VISIBLE) {
            if (isShimmerStarted()) {
                stopShimmer()
                mStoppedShimmerBecauseVisibility = true
            }
        } else if (mStoppedShimmerBecauseVisibility) {
            mFrogoShimmerDrawable.maybeStartShimmer()
            mStoppedShimmerBecauseVisibility = false
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mFrogoShimmerDrawable.maybeStartShimmer()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopShimmer()
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        if (mShowShimmer) {
            mFrogoShimmerDrawable.draw(canvas)
        }
    }

    override fun verifyDrawable(who: Drawable): Boolean {
        return super.verifyDrawable(who) || who === mFrogoShimmerDrawable
    }
}
