/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.frogobox.sdk.widget.shimmer

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.RectF
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.IntDef
import androidx.annotation.Px
import com.frogobox.sdk.R
import kotlin.math.roundToInt

/**
 * A Shimmer is an object detailing all of the configuration options available for [FrogoShimmerFrameLayout]
 */
class FrogoShimmer internal constructor() {

    internal val positions: FloatArray = FloatArray(COMPONENT_COUNT)
    internal val colors: IntArray = IntArray(COMPONENT_COUNT)
    internal val bounds: RectF = RectF()

    @Direction
    internal var direction: Int = Direction.LEFT_TO_RIGHT

    @ColorInt
    internal var highlightColor: Int = Color.WHITE

    @ColorInt
    internal var baseColor: Int = 0x4cffffff

    @Shape
    internal var shape: Int = Shape.LINEAR

    internal var fixedWidth: Int = 0
    internal var fixedHeight: Int = 0
    internal var widthRatio: Float = 1f
    internal var heightRatio: Float = 1f
    internal var intensity: Float = 0f
    internal var dropoff: Float = 0.5f
    internal var tilt: Float = 20f
    internal var clipToChildren: Boolean = true
    internal var autoStart: Boolean = true
    internal var alphaShimmer: Boolean = true
    internal var repeatCount: Int = ValueAnimator.INFINITE
    internal var repeatMode: Int = ValueAnimator.RESTART
    internal var animationDuration: Long = 1000L
    internal var repeatDelay: Long = 0L
    internal var startDelay: Long = 0L

    internal fun width(width: Int): Int {
        return if (fixedWidth > 0) fixedWidth else (widthRatio * width).roundToInt()
    }

    internal fun height(height: Int): Int {
        return if (fixedHeight > 0) fixedHeight else (heightRatio * height).roundToInt()
    }

    internal fun updateColors() {
        when (shape) {
            Shape.RADIAL -> {
                colors[0] = highlightColor
                colors[1] = highlightColor
                colors[2] = baseColor
                colors[3] = baseColor
            }
            else -> {
                colors[0] = baseColor
                colors[1] = highlightColor
                colors[2] = highlightColor
                colors[3] = baseColor
            }
        }
    }

    internal fun updatePositions() {
        when (shape) {
            Shape.RADIAL -> {
                positions[0] = 0f
                positions[1] = intensity.coerceAtMost(1f)
                positions[2] = (intensity + dropoff).coerceAtMost(1f)
                positions[3] = 1f
            }
            else -> {
                positions[0] = ((1f - intensity - dropoff) / 2f).coerceAtLeast(0f)
                positions[1] = ((1f - intensity - 0.001f) / 2f).coerceAtLeast(0f)
                positions[2] = ((1f + intensity + 0.001f) / 2f).coerceAtMost(1f)
                positions[3] = ((1f + intensity + dropoff) / 2f).coerceAtMost(1f)
            }
        }
    }

    internal fun updateBounds(viewWidth: Int, viewHeight: Int) {
        val magnitude = maxOf(viewWidth, viewHeight)
        val rad = Math.PI / 2.0 - Math.toRadians((tilt % 90f).toDouble())
        val hyp = magnitude / Math.sin(rad)
        val padding = 3 * ((hyp - magnitude) / 2.0).roundToInt()
        bounds.set(
            -padding.toFloat(),
            -padding.toFloat(),
            (width(viewWidth) + padding).toFloat(),
            (height(viewHeight) + padding).toFloat()
        )
    }

    /**
     * The shape of the shimmer's highlight. By default LINEAR is used.
     */
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(Shape.LINEAR, Shape.RADIAL)
    annotation class Shape {
        companion object {
            const val LINEAR: Int = 0
            const val RADIAL: Int = 1
        }
    }

    /**
     * Direction of the shimmer's sweep.
     */
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(
        Direction.LEFT_TO_RIGHT,
        Direction.TOP_TO_BOTTOM,
        Direction.RIGHT_TO_LEFT,
        Direction.BOTTOM_TO_TOP
    )
    annotation class Direction {
        companion object {
            const val LEFT_TO_RIGHT: Int = 0
            const val TOP_TO_BOTTOM: Int = 1
            const val RIGHT_TO_LEFT: Int = 2
            const val BOTTOM_TO_TOP: Int = 3
        }
    }

    abstract class Builder<T : Builder<T>> {
        val mFrogoShimmer: FrogoShimmer = FrogoShimmer()

        protected abstract fun getThis(): T

        /**
         * Applies all specified options from the [AttributeSet].
         */
        fun consumeAttributes(context: Context, attrs: AttributeSet?): T {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ShimmerFrameLayout, 0, 0)
            return try {
                consumeAttributes(a)
            } finally {
                a.recycle()
            }
        }

        internal open fun consumeAttributes(a: TypedArray): T {
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_clip_to_children)) {
                setClipToChildren(
                    a.getBoolean(
                        R.styleable.ShimmerFrameLayout_shimmer_clip_to_children,
                        mFrogoShimmer.clipToChildren
                    )
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_auto_start)) {
                setAutoStart(
                    a.getBoolean(
                        R.styleable.ShimmerFrameLayout_shimmer_auto_start,
                        mFrogoShimmer.autoStart
                    )
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_base_alpha)) {
                setBaseAlpha(a.getFloat(R.styleable.ShimmerFrameLayout_shimmer_base_alpha, 0.3f))
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_highlight_alpha)) {
                setHighlightAlpha(
                    a.getFloat(R.styleable.ShimmerFrameLayout_shimmer_highlight_alpha, 1f)
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_duration)) {
                setDuration(
                    a.getInt(
                        R.styleable.ShimmerFrameLayout_shimmer_duration,
                        mFrogoShimmer.animationDuration.toInt()
                    ).toLong()
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_repeat_count)) {
                setRepeatCount(
                    a.getInt(
                        R.styleable.ShimmerFrameLayout_shimmer_repeat_count,
                        mFrogoShimmer.repeatCount
                    )
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_repeat_delay)) {
                setRepeatDelay(
                    a.getInt(
                        R.styleable.ShimmerFrameLayout_shimmer_repeat_delay,
                        mFrogoShimmer.repeatDelay.toInt()
                    ).toLong()
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_repeat_mode)) {
                setRepeatMode(
                    a.getInt(
                        R.styleable.ShimmerFrameLayout_shimmer_repeat_mode,
                        mFrogoShimmer.repeatMode
                    )
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_start_delay)) {
                setStartDelay(
                    a.getInt(
                        R.styleable.ShimmerFrameLayout_shimmer_start_delay,
                        mFrogoShimmer.startDelay.toInt()
                    ).toLong()
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_direction)) {
                val direction = a.getInt(
                    R.styleable.ShimmerFrameLayout_shimmer_direction,
                    mFrogoShimmer.direction
                )
                when (direction) {
                    Direction.TOP_TO_BOTTOM -> setDirection(Direction.TOP_TO_BOTTOM)
                    Direction.RIGHT_TO_LEFT -> setDirection(Direction.RIGHT_TO_LEFT)
                    Direction.BOTTOM_TO_TOP -> setDirection(Direction.BOTTOM_TO_TOP)
                    else -> setDirection(Direction.LEFT_TO_RIGHT)
                }
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_shape)) {
                val shape = a.getInt(
                    R.styleable.ShimmerFrameLayout_shimmer_shape,
                    mFrogoShimmer.shape
                )
                when (shape) {
                    Shape.RADIAL -> setShape(Shape.RADIAL)
                    else -> setShape(Shape.LINEAR)
                }
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_dropoff)) {
                setDropoff(
                    a.getFloat(
                        R.styleable.ShimmerFrameLayout_shimmer_dropoff,
                        mFrogoShimmer.dropoff
                    )
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_fixed_width)) {
                setFixedWidth(
                    a.getDimensionPixelSize(
                        R.styleable.ShimmerFrameLayout_shimmer_fixed_width,
                        mFrogoShimmer.fixedWidth
                    )
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_fixed_height)) {
                setFixedHeight(
                    a.getDimensionPixelSize(
                        R.styleable.ShimmerFrameLayout_shimmer_fixed_height,
                        mFrogoShimmer.fixedHeight
                    )
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_intensity)) {
                setIntensity(
                    a.getFloat(
                        R.styleable.ShimmerFrameLayout_shimmer_intensity,
                        mFrogoShimmer.intensity
                    )
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_width_ratio)) {
                setWidthRatio(
                    a.getFloat(
                        R.styleable.ShimmerFrameLayout_shimmer_width_ratio,
                        mFrogoShimmer.widthRatio
                    )
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_height_ratio)) {
                setHeightRatio(
                    a.getFloat(
                        R.styleable.ShimmerFrameLayout_shimmer_height_ratio,
                        mFrogoShimmer.heightRatio
                    )
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_tilt)) {
                setTilt(
                    a.getFloat(
                        R.styleable.ShimmerFrameLayout_shimmer_tilt,
                        mFrogoShimmer.tilt
                    )
                )
            }
            return getThis()
        }

        /**
         * Copies the configuration of an already built Shimmer to this builder
         */
        fun copyFrom(other: FrogoShimmer): T {
            setDirection(other.direction)
            setShape(other.shape)
            setFixedWidth(other.fixedWidth)
            setFixedHeight(other.fixedHeight)
            setWidthRatio(other.widthRatio)
            setHeightRatio(other.heightRatio)
            setIntensity(other.intensity)
            setDropoff(other.dropoff)
            setTilt(other.tilt)
            setClipToChildren(other.clipToChildren)
            setAutoStart(other.autoStart)
            setRepeatCount(other.repeatCount)
            setRepeatMode(other.repeatMode)
            setRepeatDelay(other.repeatDelay)
            setStartDelay(other.startDelay)
            setDuration(other.animationDuration)
            mFrogoShimmer.baseColor = other.baseColor
            mFrogoShimmer.highlightColor = other.highlightColor
            return getThis()
        }

        /**
         * Sets the direction of the shimmer's sweep. See [Direction].
         */
        fun setDirection(@Direction direction: Int): T {
            mFrogoShimmer.direction = direction
            return getThis()
        }

        /**
         * Sets the shape of the shimmer. See [Shape].
         */
        fun setShape(@Shape shape: Int): T {
            mFrogoShimmer.shape = shape
            return getThis()
        }

        /**
         * Sets the fixed width of the shimmer, in pixels.
         */
        fun setFixedWidth(@Px fixedWidth: Int): T {
            require(fixedWidth >= 0) { "Given invalid width: $fixedWidth" }
            mFrogoShimmer.fixedWidth = fixedWidth
            return getThis()
        }

        /**
         * Sets the fixed height of the shimmer, in pixels.
         */
        fun setFixedHeight(@Px fixedHeight: Int): T {
            require(fixedHeight >= 0) { "Given invalid height: $fixedHeight" }
            mFrogoShimmer.fixedHeight = fixedHeight
            return getThis()
        }

        /**
         * Sets the width ratio of the shimmer, multiplied against the total width of the layout.
         */
        fun setWidthRatio(widthRatio: Float): T {
            require(widthRatio >= 0f) { "Given invalid width ratio: $widthRatio" }
            mFrogoShimmer.widthRatio = widthRatio
            return getThis()
        }

        /**
         * Sets the height ratio of the shimmer, multiplied against the total height of the layout.
         */
        fun setHeightRatio(heightRatio: Float): T {
            require(heightRatio >= 0f) { "Given invalid height ratio: $heightRatio" }
            mFrogoShimmer.heightRatio = heightRatio
            return getThis()
        }

        /**
         * Sets the intensity of the shimmer. A larger value causes the shimmer to be larger.
         */
        fun setIntensity(intensity: Float): T {
            require(intensity >= 0f) { "Given invalid intensity value: $intensity" }
            mFrogoShimmer.intensity = intensity
            return getThis()
        }

        /**
         * Sets how quickly the shimmer's gradient drops-off. A larger value causes a sharper drop-off.
         */
        fun setDropoff(dropoff: Float): T {
            require(dropoff >= 0f) { "Given invalid dropoff value: $dropoff" }
            mFrogoShimmer.dropoff = dropoff
            return getThis()
        }

        /**
         * Sets the tilt angle of the shimmer in degrees.
         */
        fun setTilt(tilt: Float): T {
            mFrogoShimmer.tilt = tilt
            return getThis()
        }

        /**
         * Sets the base alpha, which is the alpha of the underlying children, amount in the range [0, 1].
         */
        fun setBaseAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float): T {
            val intAlpha = (alpha.coerceIn(0f, 1f) * 255f).toInt()
            mFrogoShimmer.baseColor = (intAlpha shl 24) or (mFrogoShimmer.baseColor and 0x00FFFFFF)
            return getThis()
        }

        /**
         * Sets the shimmer alpha amount in the range [0, 1].
         */
        fun setHighlightAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float): T {
            val intAlpha = (alpha.coerceIn(0f, 1f) * 255f).toInt()
            mFrogoShimmer.highlightColor = (intAlpha shl 24) or (mFrogoShimmer.highlightColor and 0x00FFFFFF)
            return getThis()
        }

        /**
         * Sets whether the shimmer will clip to the childrens' contents, or if it will opaquely draw on
         * top of the children.
         */
        fun setClipToChildren(status: Boolean): T {
            mFrogoShimmer.clipToChildren = status
            return getThis()
        }

        /**
         * Sets whether the shimmering animation will start automatically.
         */
        fun setAutoStart(status: Boolean): T {
            mFrogoShimmer.autoStart = status
            return getThis()
        }

        /**
         * Sets how often the shimmering animation will repeat. See [ValueAnimator.setRepeatCount].
         */
        fun setRepeatCount(repeatCount: Int): T {
            mFrogoShimmer.repeatCount = repeatCount
            return getThis()
        }

        /**
         * Sets how the shimmering animation will repeat. See [ValueAnimator.setRepeatMode].
         */
        fun setRepeatMode(mode: Int): T {
            mFrogoShimmer.repeatMode = mode
            return getThis()
        }

        /**
         * Sets how long to wait in between repeats of the shimmering animation.
         */
        fun setRepeatDelay(millis: Long): T {
            require(millis >= 0) { "Given a negative repeat delay: $millis" }
            mFrogoShimmer.repeatDelay = millis
            return getThis()
        }

        /**
         * Sets how long to wait for starting the shimmering animation.
         */
        fun setStartDelay(millis: Long): T {
            require(millis >= 0) { "Given a negative start delay: $millis" }
            mFrogoShimmer.startDelay = millis
            return getThis()
        }

        /**
         * Sets how long the shimmering animation takes to do one full sweep.
         */
        fun setDuration(millis: Long): T {
            require(millis >= 0) { "Given a negative duration: $millis" }
            mFrogoShimmer.animationDuration = millis
            return getThis()
        }

        fun build(): FrogoShimmer {
            mFrogoShimmer.updateColors()
            mFrogoShimmer.updatePositions()
            return mFrogoShimmer
        }
    }

    class AlphaHighlightBuilder : Builder<AlphaHighlightBuilder>() {
        init {
            mFrogoShimmer.alphaShimmer = true
        }

        override fun getThis(): AlphaHighlightBuilder = this
    }

    class ColorHighlightBuilder : Builder<ColorHighlightBuilder>() {
        init {
            mFrogoShimmer.alphaShimmer = false
        }

        /**
         * Sets the highlight color for the shimmer.
         */
        fun setHighlightColor(@ColorInt color: Int): ColorHighlightBuilder {
            mFrogoShimmer.highlightColor = color
            return getThis()
        }

        /**
         * Sets the base color for the shimmer.
         */
        fun setBaseColor(@ColorInt color: Int): ColorHighlightBuilder {
            mFrogoShimmer.baseColor = (mFrogoShimmer.baseColor and -0x1000000) or (color and 0x00FFFFFF)
            return getThis()
        }

        override fun consumeAttributes(a: TypedArray): ColorHighlightBuilder {
            super.consumeAttributes(a)
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_base_color)) {
                setBaseColor(
                    a.getColor(
                        R.styleable.ShimmerFrameLayout_shimmer_base_color,
                        mFrogoShimmer.baseColor
                    )
                )
            }
            if (a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_highlight_color)) {
                setHighlightColor(
                    a.getColor(
                        R.styleable.ShimmerFrameLayout_shimmer_highlight_color,
                        mFrogoShimmer.highlightColor
                    )
                )
            }
            return getThis()
        }

        override fun getThis(): ColorHighlightBuilder = this
    }

    companion object {
        private const val COMPONENT_COUNT = 4
    }
}
